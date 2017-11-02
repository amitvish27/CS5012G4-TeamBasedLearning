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

	document.location.href = "CoursesServlet?pg=" + pgnum;
}

function goToCoursesAtPage()
{
    var pgn = document.getElementById("courpage").value;

	document.location.href = "CoursesServlet?pg=" + pgn;
}

function delThisCourse(u, v)
{
	console.log(u);
	console.log(u);
	console.log(v);
	document.location.href = "DelCourServlet?cid=" + u + "&curpg=" + v;
}