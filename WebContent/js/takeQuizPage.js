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
			var htmlData = getActiveQuizBody(data.activeQuizList);
			$('.quizBody').html("");
			$('.timerCountdown').hide();
			$('.quizBody').html(htmlData);
		}
	});
});


function getActiveQuizBody(data) {
	var htmlData = "";
	htmlData += "<h4>Available Quizes: </h4>";
	$.each(data, function(index, value) {
		htmlData += "<div id='quiz."+value.quizid+"' class='top-buffer form-horizontal'><div class='form-group'>" 
				+"		<label class='col-sm-2' >Quiz - "+value.quiznumber+"</label>" 
				+		"<div class='col-sm-2'>" 
				+		"<input type='text' class='form-control' placeholder='Enter Token'></div>" 
				+		"<div class='col-sm-2'>" 
				+			"<button type='button' class='btn btn-link btn-sm startQuiz'>"
				+				"<span class='glyphicon glyphicon-new-window'></span> Start Quiz</button></div>"
				+"</div></div>";
	});
	
	return htmlData;
}


$(document).on("click",".startQuiz",  function(){
	
	var quizid = $(this).parents()[2].id.replace('quiz.','');
	var token=$(this).parents()[2].children[0].children[1].firstChild.value;
	
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "getQuizDetails",
			quizid:quizid,
			token:token
		},
		success : function(data) {
			if(data.error){
				alert(data.error);
				location.reload();
			}
			else {
				$('.timerCountdown').show();
				var time_limit = data.quiz.time_limit;
				var htmlData = getQuestionBody(data.quiz);	
				countdown(time_limit, 0);
				$('.quizBody').html(htmlData);
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
	$.ajax({
		url : "TakeQuiz",
		type : "POST",
		dataType : "json",
		data : {
			action : "getQuestion",
			quizid:quizid,
			quizQuestNumber:quizQuestNumber
		},
		success : function(data) {
			if(data.error){
				alert(data.error);
				location.reload();
			}
			else {
				var htmlData = getQuestionBody(data.quiz);	
				$('.quizBody').html(htmlData);
			}
		}
	});
}


function getQuestionBody(data) {
	var htmlData = "";
	htmlData += "<div class='quizBodyHeader'>"
			+ "		<input type='hidden' id='quizid' value='"+data.quizid+"'>"
			+ "		<input type='hidden' id='quizQuestNumber' value='"+data.currQuest+"'>"
			+ "		<input type='hidden' id='totalQuizQuest' value='"+data.totalQuest+"'>"
			+ "		<div class='questionNumber'> Question "+data.currQuest+" of "+data.totalQuest+"</div>"	
			+ "		<div class='question h4'>" + data.quest
			+ "		</div>"
			+ "		<div class='question_options'>";
	
	$.each(data.options, function(index,value) {
		htmlData += "<div class='radio'>"
			  + "		<label><input type='radio' name='option'>"+value+"</label>"
			  + "		</div>";
	});
	htmlData += "</div></div>";
	htmlData += "<div class='quizBodyFooter float-right'>";
	if (data.currQuest<=1)
	{
		htmlData += "<button type='button' class='btn btn-link btn-sm disabled prevQuestionQuiz'>"
				+"<span class='glyphicon glyphicon-triangle-left'></span> Previous Question</button>"
	}
	else {
		htmlData +="<button type='button' class='btn btn-link btn-sm prevQuestionQuiz'>"
		+"<span class='glyphicon glyphicon-triangle-left'></span> Previous Question</button>"
	}
	if (data.currQuest>=data.totalQuest)
	{
		htmlData+="<button type='button' class='btn btn-link btn-sm disabled nextQuestionQuiz'>Next Question "
				+"<span class='glyphicon glyphicon-triangle-right'></span></button>"
		
	} else {
		htmlData+="<button type='button' class='btn btn-link btn-sm nextQuestionQuiz'>Next Question "
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
	$('.quizBody').html("");
	var htmlData = "";
	
	htmlData += "<div class='h4'>Quiz Finished. </div>";
	htmlData+="<div><button type='button' class='btn btn-link btn-sm' onclick='location.reload();'>"
			+" <span class='glyphicon glyphicon-refresh'></span> Go Home</button><div>"
	$('.quizBody').html(htmlData);
	$('.timerCountdown').hide();
	$('.countdown').html('');
	clearInterval(interval);
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