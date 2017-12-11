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
<script src="js/quizPage.js"></script>
<link href="css/template.css" rel="stylesheet">

</head>
<body>
		<%
			List<QuizSetupBean> myQuizBeanList =(List<QuizSetupBean>) request.getAttribute("quizBeansetupList");
			
		%>

	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid">
			<input id="crtpg" type="hidden" value="${crtpg}" />

			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body ">
						<div class="row top-buffer">
							<div class="col-md-offset-2 col-sm-4 col-md-6 text-center">
								<span id="proberrmsg" class="errorFont"> <c:if
										test="${param.err > 0}">Please select the required content of your course.</c:if>
								</span>
							</div>
						</div>
						<fieldset>
							<legend style="text-align: center;"> Quiz Management </legend>
							
							<input type='hidden' id="idSortBy" value="${sortBy}" /> <input
								type='hidden' id="idSortDir" value="${sortDir}" />
							
							<table class="table table-striped table-hover table-condensed">
								<thead>
									<tr>
										<% /*
										<th style="padding-left: 5em" colspan=2>Course list</th>
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
										*/%>
										<th>&nbsp;</th>
										<th><button type="button" class="btn btn-link btn-sm"
												data-toggle="modal" data-target="#createNewModal">
												<span class="glyphicon glyphicon-plus"></span> Add new
												quiz
											</button></th>
									</tr>
									<tr>
										<th>Course_id</th>
										<th>Quiz_Number</th>
										<th>Time_Limit</th>
										<th>Start_Time</th>
										<th>End_Time</th>
										<th>Create_Time</th>
										
										
										<% /*<th>Code<input type="hidden" id="codeSortDir" value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('code')">
												<span id="codeSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
										<th>Title<input type="hidden" id="titleSortDir" value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('title')">
												<span id="titleSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
										<th>Year<input type="hidden" id="yearSortDir" value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('year')">
												<span id="yearSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
										<th>Semester<input type="hidden" id="semesterSortDir"
											value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('semester')">
												<span id="semesterSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>*/%>
											
										<th class="text-center">Action
										</th>
									</tr>
								</thead>
								<tbody>
								<% 
                	for(QuizSetupBean qb : myQuizBeanList)
                	{
                	%>
                		<tr>	
	                        <td id="cid<%= qb.getQuiz_ID() %>"><%= qb.getCourse_ID() %></td>
	                        <td id="qNum<%= qb.getQuiz_ID() %>"><%= qb.getQuiz_number() %></td>
	                        <td id="timLim<%= qb.getQuiz_ID() %>"><%= qb.getTime_limit() %></td>
	                        <td id="startTime<%= qb.getQuiz_ID() %>"><%= qb.getStart_time() %></td>
	                        <td id="endTime<%= qb.getQuiz_ID() %>"><%= qb.getEnd_time() %></td>
	                        <td id="createdTime<%= qb.getQuiz_ID() %>"><%= qb.getCreated_time() %></td>
	                        <td class="text-center">
												<div class="input-group-btn">
													<button id="editbtn<%= qb.getQuiz_ID() %>" type="button"
														onclick="editThisQuizAjax(<%= qb.getQuiz_ID() %>)"
														style="height: 2.4em" class="btn btn-link">
														<span class="glyphicon glyphicon-edit editButtonIcon"></span>
													</button>
													<button type="button"
														onclick="delThisQuiz(<%= qb.getQuiz_ID() %>)"
														style="height: 2.4em" class="btn btn-link">
														<span class="glyphicon glyphicon-trash trashButtonIcon"></span>
													</button>
													<button type="button"
														onclick="seeQuestionsUnderQuiz(<%= qb.getQuiz_ID() %>)"
														style="height: 2.4em" class="btn btn-link">
														<span class="glyphicon glyphicon-import otherButtonIcon"></span>
													</button>
												</div>
							</td>                        
	                    </tr>
	               	<%
                	}
                	%>

								</tbody>
								<tfoot>
									<tr>
										<td>&nbsp;</td>
										<% /*<td>
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
										</td>*/%>
										
										
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<% /*<td>
											<table class="input-group">
												<tr>
													<td>
														<button type="button" onclick="loadCoursesAtPage(0)"
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
														<button type="button" onclick="loadCoursesAtPage(1)"
															style="height: 2.4em" class="btn btn-link"
															<c:if test="${crtpg >= maxpg}">disabled="disabled"</c:if>>
															<span class="glyphicon glyphicon-triangle-right"></span>
														</button>
													</td>
												</tr>
											</table>
										</td>
										*/ %>
									</tr>
								</tfoot>
							</table>
						</fieldset>
					</div>
				</div>
			</div>
		</div>

		<!-- Create new modal -->
		<div class="modal fade" id="createNewModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form class="form-horizontal" action="AddQuizServlet" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								onclick="clearCreateNewModal()">&times;</button>
							<h4 class="modal-title">Create New Quiz</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="course_id" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Course Id:</label>
								<div class="col-md-4 col-sm-4">
									<input id="course_id" type="text" name="course_id"
										class="form-control" required placeholder="(E.g. 2)" />
								</div>
							</div>
							<div class="form-group">
								<label for="time_limit" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Time Limit:</label>
								<div class="col-md-4 col-sm-4">
									<input id="time_limit" type="text" name="time_limit"
										class="form-control" required placeholder="(E.g. 30)" />
								</div>
							</div>

							<div class="form-group">
								<label for="start_time"
									class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Start Time:</label>
								<div class="col-md-4 col-sm-4">
									<input id="start_time" type="text" name="start_time"
										class="form-control" required
										placeholder="(E.g. yyyy/MM/dd HH:mm:ss )" />
								</div>
							</div>
							<div class="form-group">
								<label for="end_time" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>End Time:</label>
								<div class="col-md-4 col-sm-4">
									<input id="end_time" type="text" name="end_time"
										class="form-control" required
										placeholder="(E.g. yyyy/MM/dd HH:mm:ss)" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="quiz_number" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Quiz Number:</label>
								<div class="col-md-4 col-sm-4">
									<input id="quiz_number" type="text" name="quiz_number"
										class="form-control" required
										placeholder="(E.g. 1)" />
								</div>
							</div>
							<% /*
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
							*/%>
						</div>
						<div class="modal-footer">
							<button id="createQuiz" type="submit" class="btn btn-default">Create</button>
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
