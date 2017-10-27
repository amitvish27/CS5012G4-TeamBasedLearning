<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.java.beans.*"%>

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

<script type="text/javascript"
	src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>



<title>User List</title>

</head>
<body>
<jsp:include page="top.jsp" />
	<%
		List<UserBean> myuserlist =  (List<UserBean>) request.getAttribute("usrlist");
	%>

	<div class="container">
		<h2>Admin and Instructor</h2>
		<p>List of all admin and instructor</p>
		<form ACTION="GetUserServlet" method ="POST">
			<table class="table table-hover">
				<thead>
					<tr>
						<th></th>
						<th>SSOID</th>
						<th><a href="ListUserServletFname">Firstname</th>
						<th><a href="ListUserServletLname">Lastname</th>
						
					</tr>
				</thead>
				
				<%
						for (UserBean user : myuserlist) {
					%>
				<tbody>
					<tr>
						<td><input type="radio" name="id" value=<%=user.getId() %>></td>
						<td><%=user.getSsoid() %>
						<td><%= user.getFname() %></td>
						<td><%= user.getLname() %></td>						
					</tr>
					

				</tbody>
				<%} %>

			</table>
			
			<input type="submit" value="Edit">
			
			<input type="submit" value="Delete" onclick="form.action='delete.jsp';">
		</form>
		
		<central>
			<div class="w3-container">

</div>
<p>



<div class="w3-bar" ><central>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="w3-button">Previous</a>
  <a href="#" class="w3-button">1</a>
  <a href="#" class="w3-button">2</a>
  <a href="#" class="w3-button">3</a>
  <a href="#" class="w3-button">4</a>
  <a href="#" class="w3-button">Next</a>
  </central>
</div>



</body>
</html>