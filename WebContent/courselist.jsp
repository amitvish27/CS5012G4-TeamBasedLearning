<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.java.beans.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>UMSL Team-Based Learning</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/coursePage.js"></script>
<link href="css/template.css" rel="stylesheet">

</head>
<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid">
			<input id="crtpg" type="hidden" value="${crtpg}" />
			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body form-group">
						<div class="row">
							<div class="col-md-offset-2 col-md-8 h2 text-center">Course
								creation and management</div>
						</div>
						<div class="row top-buffer">
							<div class="col-md-offset-1">
								<h4 id="F-text">View By:&nbsp;</h4>
								<select id="select_box">
									<option value="all" selected>All</option>
									<option value="year">Years</option>
									<option value="semester">Semester</option>
								</select>
								<button>Show</button>
							</div>
						</div>
						<div class="row top-buffer">
							<div class="col-md-offset-2 col-sm-4 col-md-6 text-center">
								<span id="proberrmsg" class="errorFont"> <c:if
										test="${param.err > 0}">Please select the required content of your course.</c:if>
								</span>
							</div>
							
						</div>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body ">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
								<th style="padding-left: 5em">Course list</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th><button type="button" class="btn btn-link" data-toggle="modal"
									data-target="#createNewModal">
									<span class="glyphicon glyphicon-plus"></span> Add new record
								</button></th>
								</tr>
								<tr>
									<th>Code</th>
									<th>Title</th>
									<th>Year</th>
									<th>Semester</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="course" items="${courses_List}">
									<tr>
										<td id="code${course.getId()}">${course.getCode()}</td>
										<td id="title${course.getId()}">${course.getTitle()}</td>
										<td id="year${course.getId()}">${course.getYear()}</td>
										<td id="semester${course.getId()}">${course.getSemester()}</td>
										<td class="text-center">
											<div class="input-group-btn">
												<button id="editbtn${course.getId()}" type="button"
													onclick="editThisCourseAjax(${course.getId()})"
													style="height: 2.4em" class="btn btn-link">
													<span class="glyphicon glyphicon-edit editButtonIcon"></span>
												</button>
												<button type="button"
													onclick="delThisCourse(${course.getId()}, ${crtpg})"
													style="height: 2.4em" class="btn btn-link">
													<span class="glyphicon glyphicon-trash trashButtonIcon"></span>
												</button>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>
										<table class="input-group">
											<tr>
												<td>
													<button type="button" onclick="goToCoursesAtPage(0)"
														style="height: 2.4em" class="btn btn-link"
														<c:if test="${crtpg <= 1}">disabled="disabled"</c:if>>
														<span class="glyphicon glyphicon-triangle-left"></span>
													</button>
												</td>
												<td><input id="courpage" name="courpage" type="text"
													style="width: 4em; height: 2.4em" class="form-control "
													placeholder="${crtpg}/${maxpg}"
													onfocusout="goToCoursesAtPage()" /></td>
												<td>
													<button type="button" onclick="goToCoursesAtPage(1)"
														style="height: 2.4em" class="btn btn-link"
														<c:if test="${crtpg >= maxpg}">disabled="disabled"</c:if>>
														<span class="glyphicon glyphicon-triangle-right"></span>
													</button>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>

		<!-- Create new modal -->
		<div class="modal fade" id="createNewModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form class="form-horizontal" action="AddCourse" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								onclick="clearCreateNewModal()">&times;</button>
							<h4 class="modal-title">Create New Course</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="course_code" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Code:</label>
								<div class="col-md-4 col-sm-4">
									<input id="course_code" type="text" name="course_code"
										class="form-control" required placeholder="(E.g. CS5012)" />
								</div>
							</div>
							<div class="form-group">
								<label for="course_title"
									class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Title:</label>
								<div class="col-md-4 col-sm-4">
									<input id="course_title" type="text" name="course_title"
										class="form-control" required
										placeholder="(E.g. Web Development)" />
								</div>
							</div>
							<div class="form-group">
								<label for="course_year" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Year:</label>
								<div class="col-md-4 col-sm-4">
									<select id="course_year" name="course_year"
										class="form-control" required>
										<option selected value=""></option>
										<option value="2017">2017</option>
										<option value="2018">2018</option>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="course_semester"
									class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Semester:</label>
								<div class="col-md-4 col-sm-4">
									<select id="course_semester" name="course_semester"
										class="form-control" required>
										<option selected value=""></option>
										<option value="Fall">Fall</option>
										<option value="Spring">Spring</option>
										<option value="Summer">Summer</option>
										<option value="Winter">Winter</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="instructor_id"
									class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Instructor:</label>
								<div class="col-md-4 col-sm-4">
									<input id="instructor_id" type="text" name="instructor_id"
										class="form-control" required placeholder="(E.g. SSOID)" />
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="createCourse" type="submit" class="btn btn-default">Create</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="clearCreateNewModal()">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>
