<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.java.beans.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Topics</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/tbla.js">
	
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
				<form class="form-horizontal" action="AddTopic" method="post">
					<div class="panel panel-default top-buffer">
						<div class="panel-body form-group">
							<div class="row">
								<div class="col-md-offset-2 col-md-8 h2 text-center">Topic</div>
							</div>
							<div class="row top-buffer">
								<div class="col-md-offset-2 col-sm-1 col-md-1">Year:</div>
								<div class="col-sm-2 col-md-2">
									<select id="s_course_year" name="s_course_year"
										class="form-control">
										<option value="">NONE</option>
										<c:forEach var="course" items="${courseListByInstructor}">
											<option value="${course.getYear()}">${course.getYear()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row top-buffer">
								<div class="col-md-offset-2 col-sm-1 col-md-1">Semester:</div>
								<div class="col-sm-2 col-md-2">
									<select id="s_course_sem" name="s_course_sem"
										class="form-control">
										<option value="">NONE</option>
										<c:forEach var="course" items="${courseListByInstructor}">
											<option value="${course.getSemester()}">${course.getSemester()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row top-buffer">
								<div class="col-md-offset-2 col-sm-1 col-md-1">Course:</div>
								<div class="col-sm-2 col-md-2">
									<select id="s_course" name="s_course" class="form-control">
										<option value="">NONE</option>
										<c:forEach var="course" items="${courseListByInstructor}">
											<option value="${course.getId()}">${course.getName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="row top-buffer ">
								<div class="col-md-offset-2 col-sm-4 col-md-6 text-center">
									<span id="proberrmsg" class="errorFont"> <c:if
											test="${param.err > 0}">Please select course & provide the content of your topic.</c:if>
									</span>
									<p></p>
									<textarea rows="2" class="form-control" id="topicCont"
										name="topicCont" placeholder="Enter your topic title here"></textarea>
								</div>
								<div class="col-sm-1 col-md-1 text-center">
									<p></p>
									<button id="createTopic" class="btn btn-link" type="submit">
										<span class="glyphicon glyphicon-plus"></span>Create New
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body ">
						<table class="table table-striped">
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

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>
