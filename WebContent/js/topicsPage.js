//$( document ).ready( searchTopics );

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

	if (pgn.length != 0) {
		document.location.href = "Topic?pg=" + pgn;
	}
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
	var currPage = $("#crtpg").val();
	var showOnlyByMe = ($("#s_createdbyme:checked").val()!=undefined)?true:false;
	var year = $("#s_course_year").val();
	var sem = $("#s_course_semester").val();
	var course = $("#s_course").val();
	
	$.ajax({
		type: "POST",
		url: "Topic",
		dataType: "json",
		data: {
			pg: currPage,
			pgSize:10,
			s_createdbyme: showOnlyByMe,
			s_course_year: year,
			s_course_semester: sem,
			s_course: course,
			sortColName: "title",
			sortDir: "ASC"
		},
		success: function(data) {
			var topicBody = getTableBody(data.topics);
			$("#topic_table_body").html(topicBody);
			var courseData = getCourseBody(data.course);
			$("#s_course").html(courseData);
			$("#mod_course").html(courseData);
		}
	});

}

function getTableBody(list) {
	var bodyHtml="";
	$.each(list, function(index, value) {
		bodyHtml+="<tr>" +
					"<td id='title"+value.id+"' style='padding-left: 5em'>"+value.title+"</td>" +
					"<td class='text-center'>" + 
						"<div class='input-group-btn'>" + 
							"<button id='editbtn"+value.id+"' type='button'" + 
								"onclick='editThisTopicAjax("+value.id+")'" + 
								"style='height: 2.4em' class='btn btn-link'>" + 
								"<span class='glyphicon glyphicon-edit editButtonIcon'></span>" + 
							"</button>" + 
							"<button type='button'" + 
								"onclick='delTopicByID("+value.id+")'" + 
								"style='height: 2.4em' class='btn btn-link'>" + 
								"<span class='glyphicon glyphicon-trash trashButtonIcon'></span>" + 
							"</button>" + 
						"</div>" + 
					"</td>" +
				"</tr>";
	});
	return bodyHtml;
}
function getCourseBody(list){
	var bodyHtml="<option selected value=''></option>";
	$.each(list, function(index, value) {
		bodyHtml+="<option value='"+value.id+"'>"+value.code+" - "+value.title+"</option>";
	});
	
	return bodyHtml;
}
