function moveCourOrder(u,v) 
{
    document.location.href = "switchorder?dir=" +u + "&pid=" + v;
}

function loadCoursesAtPage(u)
{
	var pgnum = parseInt(document.getElementById("crtpg").value);
	
	if (u == 0) 
	{
		pgnum--;
	} 
	else 
	{
		pgnum++;
	}

	document.location.href = "Course?pg=" + pgnum;
}

function goToCoursesAtPage()
{
    var pgn = document.getElementById("courpage").value;

	document.location.href = "Course?pg=" + pgn;
}

function delThisCourse(u, v)
{
	var ans = confirm("Are you sure you want to delete the selected item?");
	if(ans){
		document.location.href = "DelCourse?cid=" + u + "&curpg=" + v;
	}
}

function editThisCourseAjax(v) {
	$("#editbtn" + v).html("<span class=\"glyphicon glyphicon-save editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "updateThisCourseAjax(" + v + ")");
	var codeVal = $("#code" + v).text();
	var titleVal = $("#title" + v).text();
	var yearVal = $("#year" + v).text();
	var semVal = $("#semester" + v).text();
	console.log("codeval " + codeVal);
	var codeHtml = "<input class='form-control' type='text' value='"+codeVal+"'></input>";
	var titleHtml = "<input class='form-control' type='text' value='"+titleVal+"'></input>";
	
	var yearHtml =`<select id="course_year" name="course_year"
						class="form-control">
						<option value="2015">2015</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
					</select>`;
	var semHtml =`<select id="course_year" name="course_year"
						class="form-control">
						<option value="Fall">Fall</option>
						<option value="Spring">Spring</option>
						<option value="Summer">Summer</option>
						<option value="Winter">Winter</option>
					</select>`;
	

	$("#code" + v).html(codeHtml);
	$("#title" + v).html(titleHtml);
	$("#year" + v).html(yearHtml);
	$("#semester" + v).html(semHtml);
	$("#year" + v+" select").val(yearVal);
	$("#semester" + v+" select").val(semVal);
	
}

function updateThisCourseAjax(v) {
	var codeVal = $("#code" + v + " input").val();
	var titleVal = $("#title" + v + " input").val();
	var yearVal = $("#year" + v + " select").val();
	var semVal = $("#semester" + v + " select").val();

	$("#code" + v).html(codeVal);
	$("#title" + v).html(titleVal);
	$("#year" + v).html(yearVal);
	$("#semester" + v).html(semVal);
	
	$("#editbtn" + v).html("<span class=\"glyphicon glyphicon-edit editButtonIcon\"></span>");
	$("#editbtn" + v).attr("class", "btn btn-link");
	$("#editbtn" + v).attr("onclick", "editThisCourseAjax(" + v + ")");

	$.get("SaveCourse", {
		id : v,
		code: codeVal,
		title: titleVal,
		year: yearVal,
		semester: semVal
	}, function(data, status) {
		clearCreateNewModal();
	});
}


function clearCreateNewModal() {
	$("#course_code").val('');
	$("#course_title").val('');
	$("#course_year").val('2017');
	$("#course_semester").val('Fall');
	$("#instructor_id").val('');
	$("#createNewModal").modal('hide');
}
