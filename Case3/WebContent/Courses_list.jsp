<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.java.bean.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Courses Creation and Management</title>
		<link href="XXX.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="js/Courses.js"></script>
		
		<!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        
        <style>
           .test
		   {
				border: 2px solid red;
		   }
		   .title
		   {
				text-align: center;
				padding: 4px;
		   }
            .pane-decoration
            {
                border-radius: 4px;
                background-color: #d9dde2;
                
            }
            #select_box, #F-text
            {
                display: inline;
            }
            #cor-tit-tb
            {
                height: 20px;
            }
            .necessary_sign
            {
                color: red;
                font-size: 20px;
                display: inline;
            }
            .btn
            {
            	background-position: top;
            	width: 55px;
            }
            .btn:active
            {
            	background-position: top;
            	width: 50px;
            }
        </style>
	</head>
	
	<body>
        <input id="crtpg" type="hidden" value="${crtpg}" />
        <%
            List<Courses_bean> myCoursesList = (List<Courses_bean>) request.getAttribute("courses_List");
        %>
        
        <div class="container-fluid">
            <div class="row">
                <div class="title">
                    <h1>Courses creation and management</h1>
				</div>
			</div>
			<br>
            <div class="row">
                <div class="pane-decoration col-lg-offset-1 col-lg-3">
                    <br>
                    <div class="col-lg-offset-1">
                        <h4 id="F-text">View By:&nbsp;</h4>
                        <select id="select_box">
                            <option value="all" selected>All</option>
                            <option value="year">Years</option>
                            <option value="semester">Semester</option>
                        </select>
                        <button>Show</button>
                    </div>
                    <br>
                </div>
                
                <div class="pane-decoration col-lg-offset-2 col-lg-5">
					<br>
                    <div class="col-lg-offset-1">
                        <form method="post" action="addCourse">
                            Course Code: &nbsp; <input type="text" name="course_code" placeholder="Such as SCI 5012" /> <p class="necessary_sign">*</p> <br>
                            Course Title: &nbsp; <input type="text" name="course_title" placeholder="Such as Web Development"/> <p class="necessary_sign">*</p> <br>      
                            Course Semester: &nbsp; 
                            <select name="addCourseSeletor">
                                <option selected value="Spring">Spring</option>
                                <option value="Summer">Summer</option>
                                <option value="Fall">Fall</option>
                            </select>
                            <br>
                            instructor_ID: &nbsp; <input type="text" name="instructor_id" placeholder="Such as 231777"/> <p class="necessary_sign">*</p>
                             &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;
                            <button>Create</button>
                        </form>
                    </div>
                    <br>
				</div>
			</div>
            <hr>
            
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Course Code</th>
                        <th>Course Title</th>
                        <th>Course Year</th>
                        <th>Semester</th>
                        <th>Create Time</th>
                        <th>Instructor ID</th>
                        <th>EDIT</th>
                        <th>DELETE</th>
                    </tr>
                </thead>
                <tbody>
                	<% 
                	for(Courses_bean cb : myCoursesList)
                	{
                	%>
						<tr>
	                        <td><%= cb.getCourse_ID() %></td>
	                        <td><%= cb.getCourse_code() %></td>
	                        <td><%= cb.getCourse_title() %></td>
	                        <td><%= cb.getCourse_year() %></td>
	                        <td><%= cb.getSemester() %></td>
	                        <td><%= cb.getCreated_time() %></td>
	                        <td><%= cb.getInstructor_ID() %></td>
	                        <td><input type="image" src="images/icons8-edit.png" class="btn" onclick=""/></td>
	                        <td><input type="image" src="images/icons8-delete-bin.png" class="btn" onclick="delThisCourse(<%= cb.getCourse_ID() %>,${crtpg})"/></td>
	                    </tr>
                	<%
                	}
                	%>
                </tbody>
            </table>
            <div class="input-group">
				<input id="courpage" type="text" class="form-control" style="width:5em" placeholder="${crtpg}/${maxpg}">
				<button type="button" onclick="goToCoursesAtPage()" style="height:2.4em" class="btn btn-default btn-md">
					<span class="glyphicon glyphicon-share-alt"></span>
				</button>
			</div>
			<div class="input-group-btn">
				<c:if test="${crtpg > 1}">
					<button type="button" onclick="loadCoursesAtPage(0)" style="height:2.4em" class="btn btn-default">
						<span class="glyphicon glyphicon-triangle-left" aria-label="Left"></span>
					</button>
				</c:if>
				<c:if test="${crtpg < maxpg}">
					<button type="button" onclick="loadCoursesAtPage(1)" style="height:2.4em" class="btn btn-default">
						<span class="glyphicon glyphicon-triangle-right" aria-label="Right"></span>
					</button>
				</c:if>
			</div>
			
		</div>
		
		
        <script
        src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
        
    
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>