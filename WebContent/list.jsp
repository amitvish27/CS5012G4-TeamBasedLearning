<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.beans.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Topics</title>
</head>
<body>
	<%
		List<Topic> topicList = (List<Topic>) request.getAttribute("topicList");
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<table width="100%" class="table table-bordered table-striped">
					<tr>
						<td>Id</td>
						<td>Title</td>
						<td>Course</td>
						<td>Year</td>
						<td>Semester</td>
					</tr>
					<c:forEach var="topic" items="${topicList}">
						<tr>
							<td class="text-center"> ${topic.getId()} </td>
							<td class="text-center"> ${topic.getTitle()}</td>
							<td class="text-center"> ${topic.getCourse()}</td>
							<td class="text-center"> course.getYear</td>
							<td class="text-center"> course.getSemester</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>