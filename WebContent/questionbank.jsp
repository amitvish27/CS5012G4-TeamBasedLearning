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

<link href="css/template.css" rel="stylesheet">

<script src="js/questionsPage.js"></script>

</head>

<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid">
			<input id="pcrtpg" type="hidden" value="${pcrtpg}" />

			<div class="container ">
				<div class="panel panel-default top-buffer">
					<div class="panel-body ">
						<div class="row top-buffer">
							<div class="col-md-offset-2 col-sm-4 col-md-6 text-center">
								<span id="proberrmsg" class="errorFont"> <c:if
										test="${param.err > 0}">Please set all the required content of your Question..</c:if>
								</span>
							</div>
						</div>
						<fieldset>
							<legend style="text-align: center;"> Question Bank
								Management </legend>

							<table class="table table-striped table-hover table-condensed">
								<thead>
									<tr>
										<th style="padding-left: 5em" colspan=2>Questions list</th>
										<th colspan=2><div class="input-group">
												<input id="idsearchtext" type="text"
													class="form-control input-sm" placeholder="Search" /> <span
													class="input-group-btn">
													<button class="btn btn-info btn-sm" type="button"
														onclick="onSearch()">
														<i class="glyphicon glyphicon-search"></i>
													</button>
													<button class="btn btn-link btn-sm" type="button"
														onclick="onRefresh()">
														<i class="glyphicon glyphicon-refresh"></i>
													</button>
												</span>
											</div></th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th><button type="button" class="btn btn-link btn-sm"
												data-toggle="modal" data-target="#createNewModal">
												<span class="glyphicon glyphicon-plus"></span> Add new
												record
											</button></th>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<th>Content</th>
										<th>Option A</th>
										<th>Option B</th>
										<th>Option C</th>
										<th>Option D</th>
										<th>Answer</th>
										<th>Keyword</th>
										<th class="text-center">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="question" items="${questionList}">
										<tr>
											<td>&nbsp;</td>
											<td id="Content${question.getId()}">${question.getContent()}</td>
											<td id="OptA${question.getId()}">${question.getOptA()}</td>
											<td id="OptB${question.getId()}">${question.getOptB()}</td>
											<td id="OptC${question.getId()}">${question.getOptC()}</td>
											<td id="OptD${question.getId()}">${question.getOptD()}</td>
											<td id="Answer${question.getId()}"><c:choose>
													<c:when test="${question.getAnswer() eq 1}">A
														</c:when>
													<c:when test="${question.getAnswer() eq 2}">B
														</c:when>
													<c:when test="${question.getAnswer() eq 3}">C
														</c:when>
													<c:otherwise>D</c:otherwise>
												</c:choose></td>
											<td id="Kywd${question.getId()}">${question.getKywd()}</td>

											<td class="text-center">
												<div class="input-group-btn">
													<button id="editbtn${question.getId()}" type="button"
														onclick="editThisQuestionAjax(${question.getId()})"
														style="height: 2.4em" class="btn btn-link">
														<span class="glyphicon glyphicon-edit editButtonIcon"></span>
													</button>
													<button type="button"
														onclick="delQuestionByID(${question.getId()})"
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
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th colspan=3>
											<div class="form-inline">
												<div class="form-group">
													Show <select class="form-control" id="selShowEntries"
														onchange="showEntries()">
														<option value=10
															<c:if test="${recPerPgStr eq 10}"> selected</c:if>>10</option>
														<option value=25
															<c:if test="${recPerPgStr eq 25}"> selected</c:if>>25</option>
														<option value=50
															<c:if test="${recPerPgStr eq 50}"> selected</c:if>>50</option>
														<option value=100
															<c:if test="${recPerPgStr eq 100}"> selected</c:if>>100</option>
													</select> Entries
												</div>
											</div>
										</th>

										<th>
											<table class="input-group">
												<tr>
													<td>
														<button type="button" onclick="goToQuestionsAtPage(0)"
															class="btn btn-link"
															<c:if test="${pcrtpg <= 1}">disabled="disabled"</c:if>>
															<span class="glyphicon glyphicon-triangle-left"></span>
														</button>
													</td>
													<td><input id="questionpage" name="questionpage"
														type="text" style="width: 4em;" class="form-control "
														placeholder="${pcrtpg}/${pmaxpg}"
														onfocusout="goToQuestionsAtPage()" /></td>
													<td>
														<button type="button" onclick="goToQuestionsAtPage(1)"
															class="btn btn-link"
															<c:if test="${pcrtpg >= pmaxpg}">disabled="disabled"</c:if>>
															<span class="glyphicon glyphicon-triangle-right"></span>
														</button>
													</td>
												</tr>
											</table>
										</th>
									</tr>
								</tfoot>
							</table>
						</fieldset>
					</div>
				</div>
			</div>
		</div>

		<!-- Create new question -->
		<div class="modal fade" id="createNewModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form class="form-horizontal" action="AddQuestion" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Create New Question</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="q_Course" class="control-label col-md-2 col-sm-2">Course:</label>
								<div class="col-md-4 col-sm-4">
									<select id="q_course" name="q_course" class="form-control">
										<option value="">NONE</option>
										<c:forEach var="course" items="${courseListByInstructor}">
											<option value="${course.getId()}">${course.getCode()}
												- ${course.getTitle()}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="q_Topic" class="control-label col-md-2 col-sm-2">Topic:</label>
								<div class="col-md-4 col-sm-4">
									<select id="q_topic" name="q_topic" class="form-control">
										<option value="">NONE</option>
										<c:forEach var="topic" items="${TopicById}">
											<option value="${course.getId()}">${course.getCode()}
												- ${course.getTitle()}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label for="questionCont"
									class="control-label col-md-2 col-sm-2">Question
									Content:</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="2" class="form-control" id="questionCont"
										name="questionCont"
										placeholder="Enter your question content here"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="questionOptA"
									class="control-label col-md-2 col-sm-2">A</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="1" class="form-control" id="questionOptA"
										name="questionOptA" placeholder="Enter option A"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="questionOptB"
									class="control-label col-md-2 col-sm-2">B</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="1" class="form-control" id="questionOptB"
										name="questionOptB" placeholder="Enter option B"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="questionOptC"
									class="control-label col-md-2 col-sm-2">C</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="1" class="form-control" id="questionOptC"
										name="questionOptC" placeholder="Enter option C"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="questionOptD"
									class="control-label col-md-2 col-sm-2">D</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="1" class="form-control" id="questionOptB"
										name="questionOptD" placeholder="Enter option D"></textarea>
								</div>
							</div>

							<div class="form-group">
								<label for="questionAnswer"
									class="control-label col-md-2 col-sm-2">Answer</label>
								<div class="col-md-2 col-sm-2">
									<select id="questionAnswer" class="form-control"
										name="questionAnswer">
										<option value=""></option>
										<option value="1">A</option>
										<option value="2">B</option>
										<option value="3">C</option>
										<option value="4">D</option>
									</select>


								</div>
							</div>

							<div class="form-group">
								<label for="questionKeyword"
									class="control-label col-md-2 col-sm-2">Keyword:</label>
								<div class="col-md-10 col-sm-10">
									<textarea rows="1" class="form-control" id="questionKeyword"
										name="questionKeyword"
										placeholder="Enter keyword and seperate them by comma"></textarea>
								</div>
							</div>



						</div>
						<div class="modal-footer">
							<button id="createQuestion" type="submit" class="btn btn-default">Create</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
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
