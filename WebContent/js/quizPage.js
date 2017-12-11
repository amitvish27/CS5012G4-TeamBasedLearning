function showEntries() {
	var entries = $("#selShowEntries").val();
	var crtpg = parseInt(document.getElementById("crtpg").value);
	document.location.href = "LandingQuizServlet?pg=" + crtpg + "&ent="
			+ entries;

}

function loadCoursesAtPage(u) {
	var pgnum = parseInt(document.getElementById("crtpg").value);

	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}

	document.location.href = "LandingQuizServlet?pg=" + pgnum;
}

function goToCoursesAtPage() {
	var pgn = document.getElementById("courpage").value;

	document.location.href = "LandingQuizServlet?pg=" + pgn;
}

function clearCreateNewModal() {
	$("#quiz_number").val('');
	$("#time_limit").val('');
	$("#start_time").val('');
	$("#end_time").val('');
	$("#creat_time").val('');
	$("#course_id").val('');
	$("#createNewModal").modal('hide');
}

function showQuizs(cid) {
	document.location.href = "QuizServlet?cid=" + cid;
}

function seeQuestionsUnderQuiz(qid) {
	document.location.href = "QuestionsUnderQuizServlet?qid=" + qid;
}

function removeRelationship(relnid) {
	document.location.href = "DelRelationshipServlet?relnid=" + relnid;
}

function addMoreQues(currQuizId, alreadyQuestionsIdListString) {
	document.location.href = "QuestionsServlet?currQuizId=" + currQuizId
			+ "&alreadyQuestionsIdListString=" + alreadyQuestionsIdListString;
}

function editThisQuizAjax(v) {
	$("#editbtn" + v).attr("src", "images/icons8-save.png");
	$("#editbtn" + v).attr("onclick", "updateThisQuizAjax(" + v + ")");
	$.get("GetQuizServlet", {
		qid : v
	}, function(data, status) {

		var splitStringArr = data.split("$HA$");
		var theCorIdArea = "<textarea id=\"corIdArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[0]
				+ "</textarea>";
		$("#cid" + v).html(theCorIdArea);
		var theQuizNumArea = "<textarea id=\"quizNumArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[1]
				+ "</textarea>";
		$("#qNum" + v).html(theQuizNumArea);
		var theTimeLimArea = "<textarea id=\"timeLimArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[2]
				+ "</textarea>";
		$("#timLim" + v).html(theTimeLimArea);
		var theStarTimeArea = "<textarea id=\"starTimeArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[3]
				+ "</textarea>";
		$("#startTime" + v).html(theStarTimeArea);
		var theEndTimeArea = "<textarea id=\"endTimeArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[4]
				+ "</textarea>";
		$("#endTime" + v).html(theEndTimeArea);
		var theCreaTimeArea = "<textarea id=\"creaTimeArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[5]
				+ "</textarea>";
		$("#createdTime" + v).html(theCreaTimeArea);
		var theInstrIdArea = "<textarea id=\"instrIdArea" + v
				+ "\" rows=\"1\" class=\"form-control\">" + splitStringArr[6]
				+ "</textarea>";
		$("#instrId" + v).html(theInstrIdArea);
	});
}

function updateThisQuizAjax(v) {
	var corIdVal = $("#corIdArea" + v).val();
	var quizNumVal = $("#quizNumArea" + v).val();
	var timeLimVal = $("#timeLimArea" + v).val();
	var starTimeVal = $("#starTimeArea" + v).val();
	var endTimeVal = $("#endTimeArea" + v).val();
	var creaTimeVal = $("#creaTimeArea" + v).val();
	var instrIdVal = $("#instrIdArea" + v).val();

	$("#cid" + v).html(corIdVal);
	$("#qNum" + v).html(quizNumVal);
	$("#timLim" + v).html(timeLimVal);
	$("#startTime" + v).html(starTimeVal);
	$("#endTime" + v).html(endTimeVal);
	$("#createdTime" + v).html(creaTimeVal);
	$("#instrId" + v).html(instrIdVal);

	$("#editbtn" + v).attr("src", "images/icons8-edit.png");
	$("#editbtn" + v).attr("onclick", "editThisQuizAjax(" + v + ")");

	$.get("saveEditedQuizServlet", {
		qid : v,
		corIdVal : corIdVal,
		quizNumVal : quizNumVal,
		timeLimVal : timeLimVal,
		starTimeVal : starTimeVal,
		endTimeVal : endTimeVal,
		creaTimeVal : creaTimeVal,
		instrIdVal : instrIdVal
	}, function(data, status) {
	});

}
function delThisQuiz (relnid){
	document.location.href = "DelQuizServlet?pid=" + relnid;
		
}
function clickToAdd(questionId, currQuizId) {
	document.location.href = "CreateRelationshipServlet?questionId="
			+ questionId + "&currQuizId=" + currQuizId;
}