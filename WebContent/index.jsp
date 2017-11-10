<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link href="css/login.css" rel="stylesheet">
</head>

<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid ">
			<div class="container text-center">
				
				<c:choose>
					<c:when test="${username!=null}">
						<h1>Welcome!!! ${username}</h1>
						<p>Team-Based Learning App</p>
						<hr>
					</c:when>
					<c:otherwise>
						<h1>Welcome!!!</h1>
						<p>Team-Based Learning App</p>
						<hr>
						<h4>Please Login to continue.</h4>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="container"></div>
		</div>

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>
