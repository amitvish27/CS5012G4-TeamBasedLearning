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
<script src="js/profilePage.js"></script>
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
							required /> <input type="submit" name="login"
							class="login loginmodal-submit" value="Login">
					</form>
					<div class="login-help">
						<a href="#forgotPswdModal" data-toggle="modal">Forgot
							Password?</a>
					</div>
				</div>
			</div>
		</div>

		<!-- Forgot Password modal -->
		<div class="modal fade" id="forgotPswdModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form class="form-horizontal">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" onclick="clearForgotPasswordModal()">&times;</button>
							<h4 class="modal-title">Request New Password</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="mod_ssoid" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>SSO ID:</label>
								<div class="col-md-4 col-sm-4">
									<input id="mod_ssoid" type="text" name="mod_ssoid"
										class="form-control" required />
								</div>
							</div>
							<div class="form-group">
								<label for="mod_email" class="control-label col-md-2 col-sm-2"><span
									class="errorFont">*</span>Email:</label>
								<div class="col-md-10 col-sm-10">
									<input id="mod_email" type="email" name="mod_email"
										class="form-control" required />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2 col-sm-2"></div>
								<div class="col-md-10 col-sm-10 errorFont" id="divValidateResult">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="createTopic" type="button" class="btn btn-default" onclick="verifyForgotPswd()" >Request</button>
							<button type="button" class="btn btn-default" onclick="clearForgotPasswordModal()"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- Message Modal -->
		<div class="modal fade" id="msgModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel"></div>

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>
