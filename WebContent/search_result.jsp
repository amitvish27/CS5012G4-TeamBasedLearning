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

<style>
div.relative {
    position: relative;
    left: 30px;
   
}
</style>
</head>
<body>
<jsp:include page="top.jsp" />
<jsp:useBean id="userbean" scope="page"
	class="edu.umsl.java.beans.UserBean" />
<jsp:useBean id="userdao" scope="request"
	class="edu.umsl.java.dao.UserDao" />
<%
String fname = request.getParameter("first_name");
String lname = request.getParameter("last_name");
String ssoid = request.getParameter("ssoid");

userbean.setFname(fname);
userbean.setLname(lname);
userbean.setSsoid(ssoid);
userbean = userdao.getUserSearch(ssoid, fname, lname);

%>


			<table class="table table-bordered" style="width:50%">
				
					<tr>
						
						<th>SSOID</th>
						<td><%=userbean.getSsoid()%></td>
					</tr>
				
				
					<tr>
						
						<th>First Name</th>
						<td><%=userbean.getFname()%></td>
					</tr>
					
					<tr>
						
						<th>Last Name</th>
						<td><%=userbean.getLname()%></td>
					</tr>
				
				
				
				
</body>
</html>