function load(){
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"getCourse"
		},
		success: function(data) {
			var htmlData = getCourseListBody(data.courseList);
			$('#s_course').html(htmlData);
		}
	});
}

$(document).ready(load);

$(document).on('hidden.bs.modal', '#msgModal' , function() { 
	//on modal close reset for next msg
	$('#msgModal').children().first().attr('class', 'modal-dialog modal-sm alert');
	$('#msgModalContent').html('');
});

function getCourseListBody(list) {
	var bodyHtml="<option selected value=''></option>";
	$.each(list, function(index, value) {
		bodyHtml+="<option value='"+value.id+"'>"+value.code+" - "+value.title+"</option>";
	});
	
	return bodyHtml;
}

//get group working 
$(document).on("change", "#s_course", function() {
	var val = $('#s_course option:selected').val();
	$("#selectedCourse").val(val);
	var groupID = $("#selectedGroup").val("");
	$("#studentlist").html("");
	$("#grouplist").html("");
	// retrieve groups from database and populate
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"getGroupsInCourse",
			courseid:val
		},
		success: function(data) {
			var htmlData = getGroupListBody(data.groupList);
			$('#grouplist').html(htmlData);
		}
	});
});

function getGroupListBody(list) {
	var bodyHtml="";
	$.each(list, function(index, value) {
		bodyHtml += "<div class='row' id='grp"+value.groupid+"'>" +
		"	<div class='col-xs-8'><button type='button' " +
		"		class='button-no-style btn btn-default btn-block show-StudentsInGroup' " +
		"		>"+value.groupnumber+"</button></div>"+
		" 	<div class='col-xs-4'> " +
		"		<div class='input-group-btn'>" + 
		"			<button type='button' class='btn btn-link editGroupName'>" + 
		"				<span class='glyphicon glyphicon-edit editButtonIcon'></span>" + 
		"			</button>" + 
		"			<button type='button' class='btn btn-link removeGroupFromCourse'>" + 
		"				<span class='glyphicon glyphicon-trash trashButtonIcon'></span>" + 
		"			</button>" + 
		"		</div>" + 
		"	</div>"+
		"</div>"
	});
	
	return bodyHtml;
}

//add group working 
$(document).on("click", ".addGroup", function () {
	var groupID = $("#selectedCourse").val();
	if (  groupID === undefined || groupID ==="") 
	{
		var modalClass = $('#msgModal').children().first().attr('class');
		$('#msgModal').children().first().attr('class', modalClass + ' alert-danger');
		$('#msgModalContent').html("Please select the Course to add group");
		$('#msgModal').modal('show')
		return;
	}
	
	var listBody="";
	listBody = $("#grouplist").html();
	listBody = 	"<div class='row' id='newGroupAdd'>" +
				"	<div class='col-xs-8' ><input type='text' class='form-control' value=''> </div>"+
				" 	<div class='col-xs-4'> " +
				"		<div class='input-group-btn'>" + 
				"			<button type='button' class='btn btn-link addNewGroupName'>" + 
				"				<span class='glyphicon glyphicon-save editButtonIcon'></span>" + 
				"			</button>" + 
				"			<button type='button' class='btn btn-link removeGroupDraft'>" + 
				"				<span class='glyphicon glyphicon-trash trashButtonIcon'></span>" + 
				"			</button>" + 
				"		</div>" + 
				"	</div>"+
				"</div>" +
				listBody;
	$("#grouplist").html(listBody);
});


$(document).on("click", ".removeGroupDraft", function() {
	$(this).parents()[2].remove();
});
//add group working
$(document).on("click", ".addNewGroupName", function() {
	var val = $(this).parents()[2].children[0].children[0].value;
	var courseid = $("#selectedCourse").val();
	//add group to database
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"addGroupsInCourse",
			courseid:courseid,
			groupNumber:val, 
		},
		success: function(data) {
			var htmlData = "<div class='row' id='grp"+data.groupId+"'>" +
			"	<div class='col-xs-8' ><button type='button' " +
			"		class='button-no-style btn btn-default btn-block show-StudentsInGroup'>" +
			"		"+data.groupNumber+"</button>" +
			"	</div>"+
			" 	<div class='col-xs-4'> " +
			"		<div class='input-group-btn'>" + 
			"			<button type='button' class='btn btn-link editGroupName'>" + 
			"				<span class='glyphicon glyphicon-edit editButtonIcon'></span>" + 
			"			</button>" + 
			"			<button type='button' class='btn btn-link removeGroupDraft'>" + 
			"				<span class='glyphicon glyphicon-trash trashButtonIcon'></span>" + 
			"			</button>" + 
			"		</div>" + 
			"	</div>"+
			"</div>"
			
			$("#newGroupAdd").html(htmlData);
		}
	});
});

//rename group working
$(document).on("click", ".editGroupName", function() {
	
	var val = $(this).parents()[2].children[0].children[0].innerHTML;
	var htmlData = "<input type='text' class='form-control' value='"+val+"'> ";
	
	$(this).attr('class','btn btn-link saveGroupName');
	$(this).html("<span class='glyphicon glyphicon-save editButtonIcon'></span>");
	
	$(this).parents()[2].children[0].innerHTML = htmlData;
});
//rename group working
$(document).on("click", ".saveGroupName", function() {
	var groupId = $(this).parents()[2].id.replace("grp","");
	var courseid = $("#selectedCourse").val(); 
	var groupNumber = $(this).parents()[2].children[0].children[0].value;
	var htmlData = "<button type='button' " +
				"		class='button-no-style btn btn-default btn-block show-StudentsInGroup'>" +
				groupNumber+"</button>";
	
	$(this).attr('class','btn btn-link editGroupName');
	$(this).html("<span class='glyphicon glyphicon-edit editButtonIcon'></span>");
	
	$(this).parents()[2].children[0].innerHTML = htmlData;
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"updateGroupsInCourse",
			courseid:courseid,
			groupNumber:groupNumber,
			groupId:groupId,
		}
	});
});

//group in course delete working
$(document).on("click", ".removeGroupFromCourse", function() {
	var ans = confirm("Are you sure you want to delete the selected record?");
	if(ans){
		var groupId = $(this).parents()[2].id.replace("grp","");
		$("#selectedGroup").val("");
		$("#studentlist").html("");
		$(this).parents()[2].remove();
		$.ajax({
			url: "Group",
			type: "POST",
			dataType: "json",
			data: {
				task:"delGroupsInCourse",
				groupId:groupId,
			}
		});
	}	
});

//student modals 
$(document).on("click", ".clear-StudentsModal", function () { 
	$("#addStudentModalbody").html('');
	$("#addStudentModal").modal('hide');
});

$(document).on("click", "#addStudentModalBtn", function () {
	var groupId = $("#selectedGroup").val();
	if (  groupId === undefined || groupId ==="") 
	{
		var modalClass = $('#msgModal').children().first().attr('class');
		$('#msgModal').children().first().attr('class', modalClass + ' alert-danger');
		$('#msgModalContent').html("Please select the Group to add students");
		$('#msgModal').modal('show')
		return;
	}
	// load all the students not in current group selected
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"getStudentsNotInGroups",
			groupId:groupId
		},
		success: function(data) {
			var htmlData = getStudentNotInGroupListBody(data.studentList);
			$("#addStudentModalbody").html(htmlData);
			$("#addStudentModal").modal('show'); // toggle modal 
		}
	});
});

function getStudentNotInGroupListBody(list) {
	var bodyHtml="";
	$.each(list, function(index, value) {
		bodyHtml += "<div class='row' id='addStdnt"+value.studentid+"'>" +			
					"	<div class='col-xs-2'>" +
					"		<input type='checkbox' name='chckstdnt' class='chckstdnt' value='"+value.studentid+"'> "+value.studentid+"</div>"+
					"	<div class='col-xs-6'>"+value.fname+" "+value.lname+"</div>"+
					"</div>" ;
	});
	return bodyHtml;
}

//DONE: add all selected students to Group in database
$(document).on("click", "#addStudentBtn", function () {
	var groupId = $("#selectedGroup").val();
	var studentList=[];
	$(".chckstdnt:checkbox:checked").each(function () {
		studentList.push($(this).val());
	});
	console.log(studentList);
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"addStudentsInGroup",
			groupId:groupId,
			studentList:studentList
		},
		success: function(data) {
			var htmlData = getStudentListBody(data.studentList);
			$('#studentlist').html(htmlData);
			$("#addStudentModalbody").html('');
			$("#addStudentModal").modal('hide'); // toggle modal hide 
		}
	});
});

function getStudentListBody(list){
	var bodyHtml="";
	$.each(list, function(index, value) {
		bodyHtml = "<div class='row' id='stdnt"+value.relnid+"'>" +
					"	<div class='col-xs-4'>"+value.studentid+"</div>"+
					"	<div class='col-xs-6'>"+value.fname+" "+value.lname+"</div>"+
					" 	<div class='col-xs-2'> " +
					"		<button type='button' class='btn btn-link btn-xs removeStudentFromGroup'>" + 
					"			<span class='glyphicon glyphicon-trash trashButtonIcon'></span>" + 
					"		</button>" + 
					" 	</div>"+
					"</div>" + 
					bodyHtml;
	});
	return bodyHtml;
}

//DONE: show students in selected group working
$(document).on("click", ".show-StudentsInGroup", function () {
	var courseId = $("#selectedCourse").val();
	if ( courseId === undefined || courseId ==="") 
	{
		var modalClass = $('#msgModal').children().first().attr('class');
		$('#msgModal').children().first().attr('class', modalClass + ' alert-danger');
		$('#msgModalContent').html("Please select the Course to add group");
		$('#msgModal').modal('show')
		return;
	}	
	
	var groupId = $(this).parents()[1].id.replace("grp","");
	$("#selectedGroup").val(groupId);
	
	// retrieve students from database and populate
	$.ajax({
		url: "Group",
		type: "POST",
		dataType: "json",
		data: {
			task:"getStudentsInGroups",
			groupId:groupId
		},
		success: function(data) {
			var htmlData = getStudentListBody(data.studentList);
			$('#studentlist').html(htmlData);
		}
	});
});

//DONE: removing students from group
$(document).on("click", ".removeStudentFromGroup", function() {
	var ans = confirm("Are you sure you want to delete the selected record?");
	if(ans){
		var s_relnid = $(this).parents()[1].id.replace("stdnt","");
		$(this).parents()[1].remove();
		$.ajax({
			url: "Group",
			type: "POST",
			dataType: "json",
			data: {
				task:"delStudentsInGroup",
				s_relnid:s_relnid
			}
		});
		
	}	
});


