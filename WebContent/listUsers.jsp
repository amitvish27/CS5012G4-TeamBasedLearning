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

<script type="text/javascript" src="/js/user.js"></script>
<script>
	function dbAlert() {
		alert(arguments[0]);
	}
	
	 
	function validateForm(number) {
		    var page_n = document.forms["pagejump"]["page"].value;
		    if (page_n > number ) {
		        alert("There is no such page");
		        return false;
		    }
		}

	
	function confirmFunction() {
	    confirm("Press a button!");
	}
	
</script>

<title>Registration</title>

</head>
<body>
	<jsp:include page="top.jsp" />
	<%
		
	//String xx = (String) session.getAttribute("role");
	//out.println("session role: "+xx);
	
		List<UserBean> myuserlist = (List<UserBean>) request.getAttribute("usrlist");
		PageBean pb = (PageBean) request.getAttribute("pb");
		String sortBy = pb.getSortBy();
		String sortByServlet = "";

		switch (sortBy) {
			case "fname" :
				sortByServlet = "ListUserServletFname?page=";
				break;
			case "lname" :
				sortByServlet = "ListUserServletLname?page=";
				break;

			default :
				sortByServlet = "listuser?page=";
				break;

		}
		//session
		String role = (String) session.getAttribute("role");
		int role_i = Integer.parseInt(role);
		String message = (String) session.getAttribute("message");
		if (message != null) {
	%>
	<div class="container">
		<div class="alert alert-warning">
			<strong>Warning!</strong>
			<c:out value="${message}"></c:out>
		</div>
	</div>
	<script>
		dbAlert("Warning: Add record failed. Duplicate entry.");
	</script>
	<%
		message = "";
			request.getSession().removeAttribute("message");

		}
	%>



	<div class="container">
	<%if (role_i==0) { %>
		<h2>Admin Page</h2>
	<%}else { %>	
	<h2>Instructor Page</h2>
	<%} %>
		<p>List of all admin and instructor</p>
		<form ACTION="GetUserServlet" method="POST">
			<table class="table table-hover">
				<thead>
					<tr>
						<th></th>
						<th>SSOID</th>
						<th><a href="ListUserServletFname">Firstname</a></th>
						<th><a href="ListUserServletLname">Lastname</a></th>

					</tr>
				</thead>

				<%
					for (UserBean user : myuserlist) {
				%>
				<tbody>
					<tr>
						<td><input type="radio" name="id" value=<%=user.getId()%>></td>
						<td><%=user.getSsoid()%>
						<td><%=user.getFname()%></td>
						<td><%=user.getLname()%></td>
					</tr>


				</tbody>
				<%
					}
				%>

			</table>

			<input type="submit" value="Edit"> 
			<input type="submit" value="Delete" onclick="form.action='deleteUser.jsp';" ></input>
		</form>
	</div>

	<div class="w3-container">
		<p>


			<%
				String pageString = request.getParameter("page");
				int prev = 1;
				int pageInteger = 1;
				if (pageString != null && !pageString.isEmpty()) {

					pageInteger = Integer.parseInt(pageString);
					prev = pageInteger - 1;
					if (prev < 1)
						prev = 1;
				} else {
					prev = 1;
				}
				
			%>
		
	</div>
	<center>
		<div class="w3-bar">
		<ul class="pagination pagination-sm">
			
				<li><a href="<%=sortByServlet%><%=prev%>" class="w3-button">Previous</a></li>

				<%for (int i = 1; i <= pb.getTotalPages(); i++) {%>
				
				<li><a href="<%=sortByServlet%><%=i%>" class="w3-button"><%=i%></a></li>
				
				<%}

			int nxt = pageInteger + 1;
			if (nxt > pb.getTotalPages())
				nxt = pb.getTotalPages();%>
				<li><a href="<%=sortByServlet%><%=nxt%>" class="w3-button">Next</a></li>
</ul>
</div>

 

<div class="relative">
	<form name ="pagejump" id = "pagejump" action = "<%=sortByServlet%>" method = "GET"  onsubmit="return validateForm(<%=pb.getTotalPages() %>)" >
         
         Page: <input type = "text" name = "page" style="width: 30px;"/>
         <input type = "submit" value = "Go"  />
      </form>

 

</div>
			
		</center>
</body>

</html>