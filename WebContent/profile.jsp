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
<script src="js/profilePage.js"></script>
<link href="css/template.css" rel="stylesheet">

</head>

<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid ">
			<div class="container">
				<br>
				<div class="well">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tabProfile" data-toggle="tab">Profile</a></li>
						<li><a href="#tabPassword" data-toggle="tab">Password</a></li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane active in" id="tabProfile">
							<div id="tab1" class="form-horizontal">
								<br>
								<div class="form-group top-buffer">
									<label class="control-label col-sm-2" for="ssoid">SSO
										ID:</label>
									<div class="col-sm-3">
										<p class="form-control-static" id="ssoid">${userId}</p>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="Fname">First
										Name:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="Fname"
											placeholder="Enter First Name" name="Fname"
											value="${attrFname}" readonly>
									</div>
									<div class="col">
										<button id="editbtnFname" type="button"
											onclick="editField('Fname')" style="height: 2.4em"
											class="btn btn-link">
											<span class="glyphicon glyphicon-edit"></span>
										</button>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="Lname">Last
										Name:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="Lname"
											placeholder="Enter Last Name" name="Lname"
											value="${attrLname}" readonly>
									</div>
									<div class="col">
										<button id="editbtnLname" type="button"
											onclick="editField('Lname')" style="height: 2.4em"
											class="btn btn-link">
											<span class="glyphicon glyphicon-edit"></span>
										</button>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Email:</label>
									<div class="col-sm-3">
										<input type="email" class="form-control" id="email"
											placeholder="Enter Email" name="email" value="${attrEmail}"
											readonly>
									</div>
									<div class="col">
										<button id="editbtnemail" type="button"
											onclick="editField('email')" style="height: 2.4em"
											class="btn btn-link">
											<span class="glyphicon glyphicon-edit"></span>
										</button>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="dept">Department:</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="dept"
											placeholder="Enter Department Name" name="dept"
											value="${attrDept}" readonly>
									</div>
									<div class="col">
										<button id="editbtndept" type="button"
											onclick="editField('dept')" style="height: 2.4em"
											class="btn btn-link">
											<span class="glyphicon glyphicon-edit"></span>
										</button>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-2 col-sm-2"></div>
									<div class="col-md-10 col-sm-10 errorFont"
										id="divValidateResult"></div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-2">
										<button class="btn btn-default" type="button"
											onclick="onUpdate()">Update</button>
									</div>
									<div class="col-sm-2">
										<button class="btn btn-default" type="button"
											onclick="onReset()">Reset</button>
									</div>
								</div>

							</div>
						</div>
						<div class="tab-pane fade" id="tabPassword">
							<div id="tab2" class="form-horizontal">
								<script src="js/passwordStrengthMeter.js"></script>
								<br>
								<div class="form-group top-buffer">
									<label class="control-label col-sm-2" for="currpwd">Current
										Password:</label>
									<div class="col-sm-3">
										<input type="password" class="form-control" id="currpwd"
											placeholder="Enter Current password" name="currpwd">
									</div>
								</div>

								<div class="form-group ">
									<label class="control-label col-sm-2" for="newpwd">New
										Password:</label>
									<div class="col-sm-3">
										<input type="password" class="form-control" id="newpwd"
											placeholder="Enter New password" name="newpwd"
											onkeypress="checkpswd('${userId}')">
									</div>
									<div class="col-sm-1" id='pswdresult'></div>
									<div class="col-sm-2">
										<script>
											$('[data-toggle="tooltip"]')
													.tooltip();
										</script>
										<a href="#" rel="tooltip" data-placement="right"
											title="Minimum 6 characters,
Should have Uppercase letter,
Should have Lowercase letter,
Should have Number,
Should have special character (!@#$%&*._+)">Hover
											for Hints</a>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="cnfmpwd">Confirm
										Password:</label>
									<div class="col-sm-3">
										<input type="password" class="form-control" id="cnfmpwd"
											placeholder="Enter New password again" name="cnfmpwd"
											onfocusout="passwordMatch()">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-2"></div>
									<div class="col-sm-3 errorFont" id="divCheckPasswordMatch">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-2">
										<button id="pswdUpdateBtn" type="button"
											class="btn btn-default" onclick="passwordUpdate()" disabled>Update</button>
									</div>
									<div class="col-sm-2">
										<button class="btn btn-default" type="button"
											onclick="clearPasswordProfile()">Reset</button>
									</div>
								</div>
							</div>
						</div>
					</div>
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
