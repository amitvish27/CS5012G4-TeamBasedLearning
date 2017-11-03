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

	$.get("GetTopic", {
		id : v
	}, function(data, status) {
		$("#title" + v).html(data);
	});
}

function updateThisTopicAjax(v) {
	var title = $("#titlearea" + v).val();

	$("#title" + v).html(title);

	//var math = document.getElementById("title" + v);
	
	$("#editbtn" + v).html("<span class=\"glyphicon glyphicon-edit editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "editThisTopicAjax(" + v + ")");

	$.get("SaveTopic", {
		id : v,
		title : title
	}, function(data, status) {
	});
}