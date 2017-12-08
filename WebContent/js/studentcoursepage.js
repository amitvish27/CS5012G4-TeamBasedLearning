
function load(){
	$.ajax({
		url: "StudentCourse",
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

//get student working 
$(document).on("change", "#s_course", function() {
	var val = $('#s_course option:selected').val();
	$("#selectedCourse").val(val);
	$("#studentlist").html("");
	// retrieve students from database and populate
	$.ajax({
		url: "StudentCourse",
		type: "POST",
		dataType: "json",
		data: {
			task:"getStudentsInCourse",
			courseid:val
		},
		success: function(data) {
			var htmlData = getStudentListBody(data.studentList);
			$('#studentlist').html(htmlData);
		}
	});
});

//student modals 
$(document).on("click", ".clear-StudentsModal", function () { 
	$("#addStudentModalbody").html('');
	$("#addStudentModal").modal('hide');
});

$(document).on("click", "#addStudentModalBtn", function () {
	var courseId = $("#selectedCourse").val();
	if (  courseId === undefined || courseId ==="") 
	{
		var modalClass = $('#msgModal').children().first().attr('class');
		$('#msgModal').children().first().attr('class', modalClass + ' alert-danger');
		$('#msgModalContent').html("Please select the Course to add students");
		$('#msgModal').modal('show')
		return;
	}
	// load all the students not in current course selected
	$.ajax({
		url: "StudentCourse",
		type: "POST",
		dataType: "json",
		data: {
			task:"getStudentsNotInCourse",
			courseid:courseId
		},
		success: function(data) {
			var htmlData = getStudentNotInCourseListBody(data.studentList);
			$("#addStudentModalbody").html(htmlData);
			$("#addStudentModal").modal('show'); // toggle modal 
		}
	});
});

function getStudentNotInCourseListBody(list) {
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

//DONE: add all selected students to course in database
$(document).on("click", "#addStudentBtn", function () {
	var courseid = $("#selectedCourse").val();
	var studentList=[];
	$(".chckstdnt:checkbox:checked").each(function () {
		studentList.push($(this).val());
	});
	console.log(studentList);
	$.ajax({
		url: "StudentCourse",
		type: "POST",
		dataType: "json",
		data: {
			task:"addStudentsInCourse",
			courseid:courseid,
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
					"		<button type='button' class='btn btn-link btn-xs removeStudentFromCourse'>" + 
					"			<span class='glyphicon glyphicon-trash trashButtonIcon'></span>" + 
					"		</button>" + 
					" 	</div>"+
					"</div>" + 
					bodyHtml;
	});
	return bodyHtml;
}


//DONE: removing students from course
$(document).on("click", ".removeStudentFromCourse", function() {
	var ans = confirm("Are you sure you want to delete the selected record?");
	if(ans){
		var s_relnid = $(this).parents()[1].id.replace("stdnt","");
		$(this).parents()[1].remove();
		$.ajax({
			url: "StudentCourse",
			type: "POST",
			dataType: "json",
			data: {
				task:"delStudentsInCourse",
				s_relnid:s_relnid
			}
		});
		
	}	
});


