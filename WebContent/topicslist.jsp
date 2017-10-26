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
<link href="css/template.css" rel="stylesheet">
<script type="text/javascript" src="js/tbla.js"></script>
<link href="css/tbla.css" rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index.html">Home</a></li>
					<li><a href="Course">Course</a></li>
					<li><a href="Topic">Topic</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<input id="crtpg" type="hidden" value="${crtpg}" />
		<div class="container">
			<div class="panel panel-default top-buffer">
				<div class="panel-body form-group">

					<div class="row">
						<div class="col-md-offset-2 col-md-8 h2 text-center">Topic</div>
					</div>
					<div class="row top-buffer">
						<div class="col-md-offset-2 col-sm-1 col-md-1">Year:</div>
						<div class="col-sm-3 col-md-4">
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
						<div class="col-sm-3 col-md-4">
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
						<div class="col-sm-3 col-md-4">
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
									test="${err > 0}">Please provide the content of your topic.</c:if>
							</span>
							<p></p>
							<textarea rows="2" class="form-control" id="topicCont"
								name="topicCont" placeholder="Enter your topic title here"></textarea>
						</div>
						<div class="col-sm-1 col-md-1 text-center">
							<p></p>
							<button id="createTopic" class="btn btn-link" type="submit"
								onclick="createNewTopic()">
								<span class="glyphicon glyphicon-plus"></span>Create New
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-offset-2 col-md-8">
					<table width="100%" class="table table-bordered table-striped">
						<tr>
							<td colspan="4">
								<table width="100%">
									<tr>
										<td width="70%" />
										<td width="30%">

											<div class="input-group">
												<c:if test="${crtpg > 1}">
													<button type="button" onclick="loadTopicsAtPage(0)"
														style="height: 2.4em" class="btn btn-link">
														<span class="glyphicon glyphicon-triangle-left"></span>
													</button>
												</c:if>
												<input id="topicpage" name="topicpage" type="text"
													style="width: 4em; height: 2.4em" class="form-control"
													placeholder="${crtpg}/${maxpg}" />
												<button class="btn btn-link" type="button"
													onclick="goToTopicsAtPage()">
													<span class="glyphicon glyphicon-share-alt"></span>
												</button>
												<c:if test="${crtpg < maxpg}">
													<button type="button" onclick="loadTopicsAtPage(1)"
														style="height: 2.4em" class="btn btn-link">
														<span class="glyphicon glyphicon-triangle-right"></span>
													</button>
												</c:if>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="text-center">Title</td>
						</tr>
						<c:forEach var="topic" items="${topicList}">
							<tr>
								<td>${topic.getTitle()}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>


	<footer class="container-fluid text-center">
		<p></p>
	</footer>

</body>
</html>
