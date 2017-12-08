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
<script src="js/takeQuizPage.js">
	
</script>
<link href="css/template.css" rel="stylesheet">

</head>
<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>
	
		<!-- Content Body -->
		<div class="container-fluid">
			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body form-group">
					Take Quiz page
					student username : student
					student password: passwd
					you there?
					yes
					Dekh ye basic setup hai 
					GroupServlet will be the best place to take reference from .. 
					mera ab tak ka best hai work hai on Ajax
					related files groupDao,.java
					teko ye wale takeQuiz.jsp and TAkeQuiz servlet me focuse karna hai 
					okand also .. apne ko javascript lagea isme 
					wo me karta simultaneously
					oyka
					yaha se start kar sakta hai ?>?
					mujhe ek baar call kr sakta hai
					yes
					 
					</div>
					<!-- End panel-body -->
				</div>
				<!-- End panel -->
			</div>
			<!-- End container -->
		</div>
		<!-- End container-fluid -->

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
