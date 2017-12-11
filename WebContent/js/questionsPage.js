function loadQuestionsAtPage(u) {
	var pgnum = parseInt(document.getElementById("pcrtpg").value);

	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}

	document.location.href = "Question?pg=" + pgnum;
}

function goToQuestionsAtPage() {
	var pgn = document.getElementById("questionpage").value;

	document.location.href = "Question?pg=" + pgn;
}

function delQuestionByID(id) {
	var ans = confirm("Are you sure you want to delete the selected item?");
	if (ans) {
		document.location.href = "DelQuestion?id=" + id;
	}
}

function editThisQuestionAjax(v) {
	$("#editbtn" + v).html(
			"<span class=\"glyphicon glyphicon-save editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "updateThisQuestionAjax(" + v + ")");

	var ContentVal = $("#Content" + v).text();
	var OptAVal = $("#OptA" + v).text();
	var OptBVal = $("#OptB" + v).text();
	var OptCVal = $("#OptC" + v).text();
	var OptDVal = $("#OptD" + v).text();
	var AnswerVal = $("#Answer" + v).text();
	var KywdVal = $("#Kywd" + v).text();
	var ContentHtml = "<input class='form-control' type='text' value='"
			+ ContentVal + "'></input>";
	var OptAHtml = "<input class='form-control' type='text' value='" + OptAVal
			+ "'></input>";
	var OptBHtml = "<input class='form-control' type='text' value='" + OptBVal
			+ "'></input>";
	var OptCHtml = "<input class='form-control' type='text' value='" + OptCVal
			+ "'></input>";
	var OptDHtml = "<input class='form-control' type='text' value='" + OptDVal
			+ "'></input>";
	var AnswerHtml = "<input class='form-control' type='text' value='"
			+ AnswerVal + "'></input>";
	var KywdHtml = "<input class='form-control' type='text' value='" + KywdVal
			+ "'></input>";
	$("#Content" + v).html(ContentHtml);
	$("#OptA" + v).html(OptAHtml);
	$("#OptB" + v).html(OptBHtml);
	$("#OptC" + v).html(OptCHtml);
	$("#OptD" + v).html(OptDHtml);
	$("#Answer" + v).html(AnswerHtml);
	$("#Kywd" + v).html(KywdHtml);

}

function updateThisQuestionAjax(v) {
	var ContentVal = $("#Content" + v + " input").val();
	var OptAVal = $("#OptA" + v + " input").val();
	var OptBVal = $("#OptB" + v + " input").val();
	var OptCVal = $("#OptC" + v + " input").val();
	var OptDVal = $("#OptD" + v + " input").val();
	var AnswerVal = $("#Answer" + v + " input").val();
	var KywdVal = $("#Kywd" + v + " input").val();

	$("#Content" + v).html(ContentVal);
	$("#OptA" + v).html(OptAVal);
	$("#OptB" + v).html(OptBVal);
	$("#OptC" + v).html(OptCVal);
	$("#OptD" + v).html(OptDVal);
	$("#Answer" + v).html(AnswerVal);
	$("#Kywd" + v).html(KywdVal);

	// var math = document.getElementById("title" + v);

	$("#editbtn" + v).html(
			"<span class=\"glyphicon glyphicon-edit editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "editThisQuestionAjax(" + v + ")");

	$.get("SaveQuestion", {
		id : v,
		content : ContentVal,
		opta : OptAVal,
		optb : OptBVal,
		optc : OptCVal,
		optd : OptDVal,
		answer : AnswerVal,
		kywd : KywdVal
	}, function(data, status) {
		clearCreateNewModal();
	});
}

function clearCreateNewModal() {
	$("#questionCont").val('');
	$("#questionOptA").val('');
	$("#questionOptB").val('');
	$("#questionOptC").val('');
	$("#questionOptD").val('');
	$("#questionAnswer").val('');
	$("#questionKeyword").val('');
	$("#createNewModal").modal('hide');
}

function onSearch() {
	var searchText = $("#idsearchtext").val();
	document.location.href = "Question" + "?search=" + searchText;
}
function onRefresh() {
	$("#idsearchtext").val('');
	document.location.href = "Question";
}
function showEntries(){
	var entries = $("#selShowEntries").val();
	var crtpg = parseInt(document.getElementById("pcrtpg").value);
	document.location.href = "Question?pg=" + crtpg + "&ent=" + entries;
}
