$( document ).ready( searchTopics );

function loadTopicsAtPage(u) {
	var pgnum = parseInt(document.getElementById("crtpg").value);

	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}
	var newpg = $("#crtpg").val(pgnum);
	var maxpg = $("#maxpg").val();
	searchTopics();
}

function goToTopicsAtPage() {
	var pgn = document.getElementById("topicpage").value;
	$("#topicpage").val('');
	if (pgn.length != 0) {
		$("#crtpg").val(pgn);
		searchTopics();
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
	var sortColName = "title";
	var sortDir = $("#titleSortDir").val();
	var pgSize = $("#selShowEntries").val();
	console.log("<<<>>>" + pgSize);
	$.ajax({
		type: "POST",
		url: "Topic",
		dataType: "json",
		data: {
			pg: currPage,
			pgSize:pgSize,
			s_createdbyme: showOnlyByMe,
			s_course_year: year,
			s_course_semester: sem,
			s_course: course,
			sortColName: sortColName,
			sortDir: sortDir
		},
		success: function(data) {
			var topicBody = getTableBody(data.topics);
			$("#topic_table_body").html(topicBody);
			if(!data.flagCourseChanged)
			{
				var courseData = getCourseBody(data.course);
				$("#s_course").html(courseData);
			}
			$("#mod_course").html(courseData);
			$("#maxpg").val(data.maxpg);
			$("#crtpg").val(data.pg);
			handlePagination(data.pg,data.maxpg);
		}
	});
}

function sortTitle() {
	var iconClass = $("#titleSortIcon").attr('class');
	if( iconClass === "glyphicon glyphicon-sort")
	{
		$("#titleSortIcon").attr('class', 'glyphicon glyphicon-sort-by-attributes');
		$("#titleSortDir").val('ASC');
	} 
	else if (iconClass === "glyphicon glyphicon-sort-by-attributes")
	{
		$("#titleSortIcon").attr('class', 'glyphicon glyphicon-sort-by-attributes-alt');
		$("#titleSortDir").val('DESC');
	}
	else if(iconClass === "glyphicon glyphicon-sort-by-attributes-alt")
	{	
		$("#titleSortIcon").attr('class', 'glyphicon glyphicon-sort');
		$("#titleSortDir").val('');
	}
	searchTopics();
}

function handlePagination(newpg,maxpg ) {
	if(newpg<=1) {
		$("#page_prevBtn").attr("disabled", "disabled");
	} else {
		$("#page_prevBtn").removeAttr("disabled");
	}

	if(newpg>=maxpg) {
		$("#page_nextBtn").attr("disabled", "disabled");
	} else {
		$("#page_nextBtn").removeAttr("disabled");
	}
	$("#topicpage").attr("placeholder", ""+newpg+"/"+maxpg);
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
