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


<script>
function checkSubmit() {
	if (!$("input[name='id']:checked").val()) {
	   alert('Nothing is checked!');
	   return false;
	}
	else {
		return true
	}
	
}
</script>


<style>
div.relative {
    position: relative;
    left: 30px;
   
}
</style>
</head>
<body>
<jsp:include page="top.jsp" />

<%
String fname = request.getParameter("first_name");
String lname = request.getParameter("last_name");
String ssoid = request.getParameter("ssoid");
String role ="";
String active="";

List<UserBean> myuserlist = (List<UserBean>) request.getAttribute("usrlist");
if (myuserlist.size()>0) {
%>
	<form ACTION="GetUserServlet" method="POST" onsubmit="return checkSubmit()">
	<table class="table table-bordered" style="width:50%">

<%	
	for (UserBean user : myuserlist) {
		
		if (user.getRole() == 0)
			role = "Admin";
		else if (user.getRole() == 1)
			role = "Instructor";
		else 
			role = "Student";
		
		if (user.getActive() ==0)
			active ="No";
		else
			active = "Yes";
		
%>


			
				<tr bgcolor=#D6EAF8><th><input type="radio" name="id" value=<%=user.getId()%>></th><td></td></tr>
					
					<tr >
						<th>SSOID</th>
						<td><%=user.getSsoid()%> </td>
					</tr>
				
					<tr>
						<th>First Name</th>
						<td><%=user.getFname()%></td>
					</tr>
					
					<tr>
						<th>Last Name</th>
						<td><%=user.getLname()%></td>
					</tr>
					
					<tr>
						<th>Email</th>
						<td><%=user.getEmail()%></td>
					</tr>
					<tr>
						<th>Department</th>
						<td><%=user.getDept()%></td>
					</tr>
					<tr>
						<th>Role</th>
						<td><%=role%></td>
					</tr>
					
					<tr>
						<th>Active</th>
						<td><%=active%></td>
					</tr>
				
		<%} %>
</table>
<input type="submit" value="Edit"> <input type="submit"
				value="Delete" onclick="form.action='deleteUser.jsp';"></input></form>

<%}else { %>		
				

<div class="alert alert-info">
  <strong>Info: </strong> No record found.
</div>


<% } %>
</body>
</html>