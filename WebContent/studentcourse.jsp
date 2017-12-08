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
<script src="js/studentcoursepage.js">
	
</script>
<link href="css/template.css" rel="stylesheet">
<style type="text/css">
	.btn-primary-outline {
		width:100%;
	}
	.striped .row:nth-of-type(odd) div {
		background-color:#ffffff;
	}
	.striped .row:nth-of-type(even) div {
		background: #f9f9f9;
	}
			
</style>

</head>
<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>
	
		<input type="hidden" id="selectedCourse" value=""/>
		<!-- Content Body -->
		<div class="container-fluid">
			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body form-group">
						<div class="row">
							<div class="col-md-offset-2 col-md-8 h3 text-center">Student
								to Course Management</div>
						</div>
						<div class="row">
							<div class=col-sm-offset-2>
							<div class="form-inline">
								<div class="form-group">
									<label for="s_course">Select Course:</label> <select id="s_course"
										name="course_year" class="form-control">
										<option selected value=""></option>
									</select>
								</div>
							</div>
							</div>
						</div>
					</div>
					<!-- End panel-body -->
				</div>
				<!-- End panel -->
			</div>
			<!-- End container -->

			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body ">
						<div class="row">
							<div class="col-sm-4" >
								<strong>Students</strong>
							</div> <!-- End col-student -->
							<div class="col-sm-2">
								<button type="button" class="btn btn-link btn-sm"
											id="addStudentModalBtn">
									<span class="glyphicon glyphicon-plus"></span> Add Student(s)
								</button>
							</div>
						</div><!-- End head row -->
						<div class="row">
							<div class="col-sm-6 striped" id="studentlist">
								
							</div> <!-- End col-student -->
						</div><!-- End content row -->
												
					</div>
					<!-- End panel-body -->
				</div>
				<!-- End panel -->
			</div>
			<!-- End container -->
		</div>
		<!-- End container-fluid -->

		<!-- Add Student Modal -->
		<div class="modal fade" id="addStudentModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="clear-StudentsModal close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add Student(s) to Course</h4>
					</div>
					<!-- End modal-header -->
					<div class="modal-body" id="addStudentModalbody"></div>
					<!-- End modal-body -->
					<div class="modal-footer">
						<button id="addStudentBtn" type="button" class="btn btn-default">Add
							Student(s)</button>
						<button type="button" class="btn btn-default clear-StudentsModal" 
							data-dismiss="modal">Close</button>
					</div>
					<!-- End modal-footer -->

				</div>
				<!-- End modal-content -->
			</div>
			<!-- End modal-dialog -->
		</div>
		<!-- End modal -->
		
		<!-- Message Modal -->
		<div class="modal fade" id="msgModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class='modal-dialog modal-sm alert'>
				<button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					<span aria-hidden='true'>&times;</span>
				</button><p id='msgModalContent'></p>
			</div>
		</div><!-- End msg-modal -->
		
		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
	<!-- End wrapper -->
</body>
</html>
