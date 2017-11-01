<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${username!=null}">
		<li><div class="dropdown">
				<button class="btn btn-link dropdown-toggle" type="button"
					id="logoutButton" data-toggle="dropdown"
					style="padding: 12px 12px;">
					<span class="glyphicon glyphicon-log-in"></span> ${username} <span
						class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu" aria-labelledby="logoutButton">
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="Logout">Logout</a></li>
				</ul>
			</div></li>
	</c:when>
	<c:otherwise>
		<li><a href="login.jsp"> <span
				class="glyphicon glyphicon-log-in"></span> Login
		</a></li>
	</c:otherwise>
</c:choose>