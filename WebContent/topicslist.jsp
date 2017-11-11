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
<script src="js/topicsPage.js">
	
</script>
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
							<div class="col-md-offset-2 col-md-8 h2 text-center">Topic
								Creation and Management</div>
						</div>
						<div class="form-inline top-buffer">
							<div class="form-group">
								<label for="s_course_year">Year:</label> <select
									id="s_course_year" name="course_year" class="form-control">
									<option selected value=""></option>
									<option value="2015">2015</option>
									<option value="2016">2016</option>
									<option value="2017">2017</option>
									<option value="2018">2018</option>
									<option value="2019">2019</option>
									<option value="2020">2020</option>
								</select>
							</div>
							<div class="form-group">
								<label for="pwd">Semester:</label> <select
									id="s_course_semester" name="s_course_semester"
									class="form-control">
									<option selected value=""></option>
									<option value="Fall">Fall</option>
									<option value="Spring">Spring</option>
									<option value="Summer">Summer</option>
									<option value="Winter">Winter</option>
								</select>
							</div>
							<div class="form-group">
								<label for="s_course">Course:</label> <select id="s_course"
									name="s_course" class="form-control">
									<option selected value=""></option>
									<c:forEach var="course" items="${courseList}">
										<option value="${course.getId()}">${course.getCode()}
											- ${course.getTitle()}</option>
									</c:forEach>
								</select>
							</div>
							<br> <br>
							<div class="form-group">
								<div class="checkbox col-md-8 col-sm-8">
									<label><input type="checkbox" id="s_createdbyme" name="s_createdbyme">
										Show only Created By Me</label>
								</div>
								<div class="col-md-2 col-sm-2">
									<button type="button" class="btn btn-default" onclick="searchTopics()">Search</button>
								</div>
							</div>
						</div>

						<div class="row top-buffer ">
							<div class="col-md-offset-2 col-sm-4 col-md-6 text-center">
								<span id="proberrmsg" class="errorFont"> <c:if
										test="${param.err > 0}">Please select course & provide the content of your topic to create new.</c:if>
								</span>
							</div>
							<div class="col-sm-1 col-md-1 text-center">
								<button type="button" class="btn btn-link" data-toggle="modal"
									data-target="#createNewModal">
									<span class="glyphicon glyphicon-plus"></span>Create New
								</button>
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
									<td width="70%" class="text-center h4">Title</td>
									<td width="30%" class="text-center h4">Action</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="topic" items="${topicList}">
									<tr>
										<td id="title${topic.getId()}" style="padding-left: 5em">${topic.getTitle()}</td>
										<td class="text-center">
											<div class="input-group-btn">
												<button id="editbtn${topic.getId()}" type="button"
													onclick="editThisTopicAjax(${topic.getId()})"
													style="height: 2.4em" class="btn btn-link">
													<span class="glyphicon glyphicon-edit editButtonIcon"></span>
												</button>
												<button type="button"
													onclick="delTopicByID(${topic.getId()})"
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
									<td width="70%">&nbsp;</td>
									<td width="30%">
										<table class="input-group">
											<tr>
												<td>
													<button type="button" onclick="loadTopicsAtPage(0)"
														style="height: 2.4em" class="btn btn-link"
														<c:if test="${crtpg <= 1}">disabled="disabled"</c:if>>
														<span class="glyphicon glyphicon-triangle-left"></span>
													</button>
												</td>
												<td><input id="topicpage" name="topicpage" type="text"
													style="width: 4em; height: 2.4em" class="form-control "
													placeholder="${crtpg}/${maxpg}"
													onfocusout="goToTopicsAtPage()" /></td>
												<td>
													<button type="button" onclick="loadTopicsAtPage(1)"
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
					<form class="form-horizontal" action="AddTopic" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								onclick="clearCreateNewModal()">&times;</button>
							<h4 class="modal-title">Create New Topic</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="mod_course" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Course:</label>
								<div class="col-md-4 col-sm-4">
									<select id="mod_course" name="mod_course" class="form-control"
										required>
										<option value=""></option>
										<c:forEach var="course" items="${courseList}">
											<option value="${course.getId()}">${course.getCode()}
												- ${course.getTitle()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="mod_topicCont"
									class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Title:</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="2" class="form-control" id="mod_topicCont"
										required name="mod_topicCont"
										placeholder="Enter your topic title here"></textarea>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="createTopic" type="submit" class="btn btn-default">Create</button>
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
