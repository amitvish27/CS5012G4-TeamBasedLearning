function loadTopicsAtPage(u) {
	var pgnum = parseInt(document.getElementById("crtpg").value);

	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}

	document.location.href = "Topic?pg=" + pgnum;
}

function goToTopicsAtPage() {
	var pgn = document.getElementById("topicpage").value;

	if (pgn.length == 0) {
		pgn = document.getElementById("topicpage2").value;
	}

	document.location.href = "Topic?pg=" + pgn;
}

function delTopicByID(id) {
	var ans = confirm("Are you sure you want to delete the selected item?");
	if(ans){
		document.location.href = "DelTopic?id=" + id;
	}
}

function editThisTopicAjax(v) {
	$("#editbtn" + v).html("<span class=\"glyphicon glyphicon-save editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "updateThisTopicAjax(" + v + ")");
	
	var titleVal = $("#title" + v).text();
	var titleHtml = "<textarea id=\"titlearea" + v + "\" rows=\"2\" class=\"form-control\">" + titleVal + "</textarea>"; 
	$("#title" + v).html(titleHtml);
}

function updateThisTopicAjax(v) {
	var title = $("#titlearea" + v).val();

	$("#title" + v).html(title);
	
	$("#editbtn" + v).html("<span class=\"glyphicon glyphicon-edit editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "editThisTopicAjax(" + v + ")");

	$.get("SaveTopic", {
		id : v,
		title : title
	}, function(data, status) {
		clearCreateNewModal();
	});
}

function clearCreateNewModal() {
	$("#mod_course").val('');
	$("#mod_topicCont").val('');
	$("#createNewModal").modal('hide');
}

function searchTopics()
{
	
}
