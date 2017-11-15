<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">UMSL Team Based Learning</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<c:if test="${userRole==1 or userRole==2}"> <!-- UserRole 0-Student, 1-Instructor, 2-admin -->
					<li><a href="Course">Course</a></li>
					<li><a href="Topic">Topic</a></li>
					<li><a href="Question">Question Bank</a></li>
					<li><a href="#">Students</a></li>
					<li><a href="#">Quiz</a></li>
					<li><a href="#">Group</a></li>
				</c:if>
				<c:if test="${userRole==2}">
					<li><a href="ManageUser">Manage Users</a></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%@include file="loginButtonPartial.jsp"%>
			</ul>
		</div>
	</div>
</nav>