var interval;

$(document).ready(function() {
	var dt = new Date($.now());
	var timestamp = dt.getFullYear()+"-"+(dt.getMonth()+1)+"-"+ dt.getDate() +" " 
					+ dt.getHours()+":"+dt.getMinutes()+":"+dt.getSeconds();
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "getActiveQuizList",
			timestamp:timestamp
		},
		success : function(data) {
			var htmlData = getActiveQuizBody(data);
			$('.quizBody').html("");
			$('.timerCountdown').hide();
			$('.quizBody').html(htmlData);
		}
	});
});


function getActiveQuizBody(data) {
	var htmlData = "";
	htmlData += "<h4>Available Quizes: </h4>";
	$.each(data.activeQuizList, function(index, value) {
		htmlData += quizlisthtml(index, value);
	});
	
	if( Object.keys(data.activeGroupQuizList).length>0 ) 
		//get length of dictionary to check if we need to display group quiz details
	{
		htmlData += "<h4>Available Group Quizes: </h4>";
		$.each(data.activeGroupQuizList, function(index, value) {
			htmlData += quizlisthtml(index, value);
		});
	}
	return htmlData;
}

function quizlisthtml (index, value) {
	var htmlData = "";
	htmlData += "<div id='quiz."+value.quizid+"' class='top-buffer form-horizontal'><div class='form-group'>" 
	+"		<input type='hidden' id='relnid' value='"+value.relnid+"'>"
	+"		<input type='hidden' id='groupid' value='"+value.groupid+"'>";
	htmlData+="<input type='hidden' id='isgroupquiz' value='";
	htmlData+=(value.groupnumber)?"1":"0";
	htmlData+="'>";

	htmlData+="<label class='col-sm-2' >" ;
	
	if(value.groupnumber){
		htmlData+="Group - " + value.groupnumber +", ";
	}
	
	htmlData+="Quiz - "+value.quiznumber+"</label>"; 

	if( value.token==='CMPLTD'){
		htmlData +="<label class='col-sm-2'>COMPLETED</label>"; 
		htmlData+="<div class='col-sm-2'>" 
			+		"<button type='button' class='btn btn-link btn-sm' disabled>"
			+			"<span class='glyphicon glyphicon-new-window'></span> Start Quiz</button></div>"
			+"</div></div>";
	}
	else if( value.token==='UNAVBL' ) {
		htmlData +="<label class='col-sm-2'>NOT AVAILABLE</label>"; 
		htmlData+="<div class='col-sm-2'>" 
			+		"<button type='button' class='btn btn-link btn-sm' disabled>"
			+			"<span class='glyphicon glyphicon-new-window'></span> Start Quiz</button></div>";
		htmlData+= "<div class='col-sm-2'> <a rel='tooltip' data-placement='right'"
				+ " title='"+value.stdNotCmpltdIds+"'>Hover to see Not Complete Status</a></div>"
				+"</div></div>";
	}
	else {
		htmlData +="<div class='col-sm-2'><input type='text' class='form-control' placeholder='Enter Token'></div>";
		htmlData+="<div class='col-sm-2'>" 
			+		"<button type='button' class='btn btn-link btn-sm startQuiz'>"
			+			"<span class='glyphicon glyphicon-new-window'></span> Start Quiz</button></div>"
			+"</div></div>";
	}
	return htmlData;
}

$(document).on("click",".startQuiz",  function(){
	
	var quizid = $(this).parents()[2].id.replace('quiz.','');
	var relnid = $(this).parents()[2].children[0].children[0].value; 
	var groupid = $(this).parents()[2].children[0].children[1].value;
	var isgroupquiz = $(this).parents()[2].children[0].children[2].value;
	var token = $(this).parents()[2].children[0].children[4].firstChild.value;
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "getQuizDetails",
			quizid:quizid,
			relnid:relnid,
			token:token,
			groupid:groupid,
			isgroupquiz:isgroupquiz
		},
		success : function(data) {
			if(data.error){
				alert(data.error);
				//location.reload();
			}
			else {
				$('.timerCountdown').show();
				var time_limit = data.quiz.time_limit;
				var htmlData = getQuestionBody(data.quiz, data.relnid, data.groupid, data.isgroupquiz);	
				countdown(time_limit, 0);
				$('.quizBody').html(htmlData);
				window.onbeforeunload = function() {
				    return "Refresh is Disabled currently for your quiz. " +
				    		"Are you sure you want to leave? " +
				    		"You may lose any unsafe changes.";
				}

			}
		}
	});
	
});


$(document).on("click",".prevQuestionQuiz",  function(){
	var quizid = $('#quizid').val();
	var quizQuestNumber = parseInt($('#quizQuestNumber').val())-1;
	if(quizQuestNumber>=1)
	{
		getquizquestion(quizid, quizQuestNumber);
	}
});


$(document).on("click",".nextQuestionQuiz",  function(){
	var quizid = $('#quizid').val();
	var quizQuestNumber = parseInt($('#quizQuestNumber').val())+1;
	var totalQuizQuest = parseInt($('#totalQuizQuest').val());
	if(quizQuestNumber<=totalQuizQuest )
	{
		getquizquestion(quizid, quizQuestNumber);
	}
});

function getquizquestion (quizid, quizQuestNumber) {
	var relnid = $("#relnid").val(); 
	var questid = $("#questid").val();
	var groupid = $("#groupid").val();
	var isgroupquiz = $("#isgroupquiz").val();
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "getQuestion",
			quizid:quizid,
			quizQuestNumber:quizQuestNumber,
			questid:questid,
			relnid:relnid,
			groupid:groupid,
			isgroupquiz:isgroupquiz
		},
		success : function(data) {
			if(data.error){
				alert(data.error);
				//location.reload();
			}
			else {
				var htmlData = getQuestionBody(data.quiz, data.relnid, data.groupid, data.isgroupquiz);	
				$('.quizBody').html(htmlData);
			}
		}
	});
}


$(document).on('change', 'input[type=radio][name=option]', function (){
	$(".submitAnswer").removeAttr('disabled')
});

$(document).on('click', '.submitAnswer', function() {
	var selectedOption = $('input[type=radio][name=option]:checked').val();
	var relnid = $("#relnid").val(); 
	var questid = $("#questid").val();
	var isgroupquiz = $("#isgroupquiz").val();
	var questattemptcount = $("#questattemptcount").val();
	
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "submitAnswer",
			selectedOption:selectedOption,
			questid:questid,
			relnid:relnid,
			isgroupquiz:isgroupquiz,
			questattemptcount:questattemptcount
		},
		success : function(data) {
			var modalClass = $('#msgModal').children().first().attr('class');
			$('#msgModal').children().first().attr('class', modalClass + data.classalert);
			$('#msgModalContent').html(data.submitresultstr);
			$('#msgModal').modal('show');
			$(".submitAnswer").attr('disabled','disabled');
			if ( data.isgroupquiz ){
				if(data.submitresult==='incorrect'){ 
					var atcount = parseInt($("#questattemptcount").val());
					atcount = (atcount+1);
					$("#questattemptcount").val(atcount);
				} else if(data.submitresult==='fail' || data.submitresult==='correct'){ 
					$(".nextQuestionQuiz").removeAttr('disabled');
				}	
			}
		}
	});
	
});

function getQuestionBody(data, relnid, groupid, isgroupquiz) {
	var htmlData = "";
	htmlData += "<div class='quizBodyHeader'>"
		+ "<div class='col-xs-8'>"
			+ "		<input type='hidden' id='relnid' value='"+relnid+"'>"
			+ "		<input type='hidden' id='groupid' value='"+groupid+"'>"
			+ "		<input type='hidden' id='isgroupquiz' value='"+isgroupquiz+"'>"
			+ "		<input type='hidden' id='quizid' value='"+data.quizid+"'>"
			+ "		<input type='hidden' id='questid' value='"+data.questid+"'>"
			+ "		<input type='hidden' id='quizQuestNumber' value='"+data.currQuest+"'>"
			+ "		<input type='hidden' id='totalQuizQuest' value='"+data.totalQuest+"'>"
			+ "		<input type='hidden' id='questattemptcount' value='1'>"
			+ "		<div class='questionNumber'> Question "+data.currQuest+" of "+data.totalQuest+"</div>"	
			+ "		<div class='question h4'>" + data.quest + " </div>"
			+ "		<div class='question_options'>";
	
		$.each(data.options, function(index,value) {
			htmlData += "<div class='radio'>"
				  	+ "		<label><input type='radio' name='option' value='"+(index+1)+"'";
			if( data.answer == (index+1))
			{
				htmlData+=" checked>"+value+"</label>";
			}
			else {
				htmlData+=">"+value+"</label>";
			}
			htmlData+= "</div>";
		});
	
	htmlData += "</div></div>";
	//if groupquiz true show answers from other group members
	if(isgroupquiz){
		htmlData+="<div class='col-xs-4'> Answers by GroupMembers: <div class='groupAnswers'>";
		htmlData+="<div class='row h5'>";
		htmlData+="<div class='col-xs-6'><strong>Student ID</strong></div>";
		htmlData+="<div class='col-xs-6'><strong>Answer</strong></div>";
		htmlData+="</div>"
		$.each(data.groupanswerslist, function(index,value) {
			htmlData+="<div class='row'>";
			htmlData+="<div class='col-xs-6'>"+value.studentid+"</div>";
			htmlData+="<div class='col-xs-6'>"+value.answer+"</div>";
			htmlData+="</div>"; // end row div
		});
		htmlData+="</div></div>";//end of group answer col-xs-4
	}
	
	htmlData +=	"</div>"; //end of quizbodyheader
	htmlData += "<div class='quizBodyFooter float-right'>";
	htmlData += "<button type='button' class='btn btn-link btn-sm submitAnswer' disabled>" //enabled only when radio box is changed
		+"<span class='glyphicon glyphicon-floppy-disk'></span> SubmitAnswer</button>"; 
	//for group quiz hide prev, else show prev button
	if(!isgroupquiz){
		if (data.currQuest<=1)
		{
			htmlData += "<button type='button' class='btn btn-link btn-sm prevQuestionQuiz' disabled>"
					+"<span class='glyphicon glyphicon-triangle-left'></span> PreviousQuestion</button>"
		}
		else {
			htmlData +="<button type='button' class='btn btn-link btn-sm prevQuestionQuiz'>"
			+"<span class='glyphicon glyphicon-triangle-left'></span> Previous Question</button>"
		}
	}
	if (data.currQuest>=data.totalQuest || isgroupquiz)
	{
		htmlData+="<button type='button' class='btn btn-link btn-sm nextQuestionQuiz' disabled>Next Question "
				+"<span class='glyphicon glyphicon-triangle-right'></span></button>"
		
	} else {
		htmlData+="<button type='button' class='btn btn-link btn-sm nextQuestionQuiz'>NextQuestion "
			+"<span class='glyphicon glyphicon-triangle-right'></span></button>"
	}

	
	htmlData+="<button type='button' class='btn btn-link btn-sm finishQuiz'>"
			+" <span class='glyphicon glyphicon-off'></span> Finish Quiz</button></div>";
	
	return htmlData;
}


$(document).on("click",".finishQuiz",  function(){
	var ans = confirm("Finish test?");
	if(ans){
		finishClicked();
	}	
});

function finishClicked(){
	var relnid = $("#relnid").val(); 
	var groupid = $("#groupid").val(); 
	var quizid = $('#quizid').val();
	
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "finishQuiz",
			quizid:quizid,
			groupid:groupid,
			relnid:relnid
		},
		success : function(data) {
			$('.quizBody').html("");
			$('.timerCountdown').hide();
			$('.countdown').html('');
			clearInterval(interval);
			var htmlData = "";
			htmlData += "<div class='h4'>Quiz Finished. </div>";
			htmlData+="<div><button type='button' class='btn btn-link btn-sm' onclick='location.reload();'>"
					+" <span class='glyphicon glyphicon-refresh'></span> Go Home</button><div>"
			$('.quizBody').html(htmlData);
			window.onbeforeunload = null;
		}
	});
}

function countdown(minutes, seconds) {
	interval = setInterval(function() {
		--seconds;
		minutes = (seconds < 0) ? --minutes : minutes;
		if (minutes < 0) clearInterval(interval);
		seconds = (seconds < 0) ? 59 : seconds;
		seconds = (seconds < 10) ? '0' + seconds : seconds;
		$('.countdown').html(minutes + ':' + seconds);
		if(minutes == -1){
			alert("Your time is up!!!");
			finishClicked();
		}
	}, 1000);
}

$(document).on('hidden.bs.modal', '#msgModal' , function() { 
	$('#msgModal').children().first().attr('class', 'modal-dialog modal-sm alert ');
	$('#msgModalContent').html('');
});
