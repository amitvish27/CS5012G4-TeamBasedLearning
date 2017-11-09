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

<%
String fname = request.getParameter("first_name");
String lname = request.getParameter("last_name");
String ssoid = request.getParameter("ssoid");
//out.println(fname);
//out.println(lname);
//out.println(ssoid);
//UserBean usr =  (UserBean) request.getAttribute("usr");
//out.println(usr.getFname());
%>

	<div class="relative">
		<form action="SearchUserServlet" method="POST">
		<!--  form action="searchUserResult.jsp" method="POST" -->
			<br> SSOID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				type="text" name="ssoid"> <br /> First Name: <input
				type="text" name="first_name">  <br /> Last Name: <input
				type="text" name="last_name" /> <br />  <input type="submit" value="Search" />
		</form>

	</div>

<%
//UserBean usr =  (UserBean) request.getAttribute("usr");
//out.println(usr.getFname());


%>

</body>
</html>