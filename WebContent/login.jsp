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

<script src="js/tbla.js"></script>

<link href="css/template.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>

<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid ">
			<div class="container">
				<br>
				<div class="loginmodal-container">
					<h1>Login to Your Account</h1>
					<br>
					<p class="errorFont">
						<c:if test="${errorMessage!=null}">
							<c:out value="${errorMessage}">
							</c:out>
						</c:if>
					</p>
					<form action="Login" method="post">
						<input type="text" name="sso_id" placeholder="Username" required />
						<input type="password" name="password" placeholder="Password"
							required />
						<div class="well well-sm" style="text-align: center;">
							<input type="radio" name="kind" value="tea" checked="checked" />
							Instructor <input type="radio" name="kind" value="stu" />
							Student
						</div>
						<input type="submit" name="login" class="login loginmodal-submit"
							value="Login">
					</form>
					<div class="login-help">
						<a href="#">Register</a> - <a href="#">Forgot Password</a>
					</div>
				</div>
			</div>
		</div>

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>
