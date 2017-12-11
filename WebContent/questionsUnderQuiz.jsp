<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.java.beans.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/Courses.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>
	<script src="js/quizPage.js"></script>
<style>
.test {
	border: 2px solid red;
}

.title {
	text-align: center;
	padding: 4px;
}

.pane-decoration {
	border-radius: 4px;
	background-color: #d9dde2;
}

#select_box, #F-text {
	display: inline;
}

#cor-tit-tb {
	height: 20px;
}

.necessary_sign {
	color: red;
	font-size: 20px;
	display: inline;
}

.btn {
	background-position: top;
	width: 55px;
}

.btn:active {
	background-position: top;
	width: 50px;
}
</style>
</head>

<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid ">
			<div class="container text-center">
				<%
					List<QuesQuizBean> myQuesQuizBeanList = (List<QuesQuizBean>) request.getAttribute("quesQuizBeanList");
					List<QuestionBean> myQuestionsBeanList = (List<QuestionBean>) request.getAttribute("questionsBeanList");
					String alreadyQuestionsIdListString = "";
					int quesId = 0;
					String quesContent = "";
					String quesKeyWord = "";
					String quesInstrId = "";
					int currQuizId = 0;
					if (!myQuesQuizBeanList.isEmpty()) {
						currQuizId = myQuesQuizBeanList.get(0).getQuizid();
					}

					for (int i = 0; i < myQuesQuizBeanList.size(); i++) {

						String tempStr = String.valueOf(myQuesQuizBeanList.get(i).getQuestid());

						if (i == (myQuesQuizBeanList.size() - 1)) {
							alreadyQuestionsIdListString = alreadyQuestionsIdListString + tempStr;
						} else {
							alreadyQuestionsIdListString = alreadyQuestionsIdListString + tempStr + "$";
						}

					}
				%>


				<div class="container-fluid">
					<div class="row">
						<div class="title">
							<h1>Questions under this quiz</h1>
						</div>
					</div>

					<div class="row">
						<div class='col'>
						<button type='button' class='btn btn-link' id='addMoreQues'  
							onclick="addMoreQues(<%=currQuizId%>, '<%=alreadyQuestionsIdListString%>' )" >
							<span class="glyphicon glyphicon-plus"></span> Add More Questions</button></div>
					</div>


					<div class="row"></div>
					<br>
					<hr>
					<br>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Question ID</th>
								<th>Question Content</th>
								<th>Question key word</th>
								<th>Instructor Id</th>
								<th>Remove</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (QuesQuizBean qqb : myQuesQuizBeanList) {
									int questid = qqb.getQuestid();
									for (QuestionBean qb : myQuestionsBeanList) {
										if (qb.getId() == qqb.getQuestid()) {
											quesId = qb.getId();
											quesContent = qb.getContent();
											quesKeyWord = qb.getKywd();
											quesInstrId = qb.getInsID();
										}
									}
							%>
							<tr>
								<td id="quesId<%=qqb.getRelnid()%>"><%=quesId%></td>
								<td id="quesContent<%=qqb.getRelnid()%>"><%=quesContent%></td>
								<td id="quesKeyWord<%=qqb.getRelnid()%>"><%=quesKeyWord%></td>
								<td id="quesInstrId<%=qqb.getRelnid()%>"><%=quesInstrId%></td>
								<td><input id="remove<%=qqb.getRelnid()%>" type="image"
									src="images/icons8-delete-100.png" class="btn"
									onclick="removeRelationship(<%=qqb.getRelnid()%>)" /></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>


			</div>
			<div class="container"></div>
		</div>

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>



</body>
</html>

