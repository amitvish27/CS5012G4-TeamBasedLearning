<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

//String role = "1";
//session.setAttribute("role", role);


String x = (String) session.getAttribute("role");
out.println("This is test.jsp, role session: "+ x);

//request.getRequestDispatcher("index.jsp").forward(request, response);
response.sendRedirect("listuser");
//request.getRequestDispatcher("listuser").forward(request, response);
%>
</body>
</html>