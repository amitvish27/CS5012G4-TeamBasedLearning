<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${userFirstName!=null}">
		<script src="js/timeout.js"></script>
		<li><div id="SecondsUntilExpire" style="display: none;"></div>
			<div class="dropdown">
				<button class="btn btn-link dropdown-toggle" type="button"
					id="logoutButton" data-toggle="dropdown"
					style="padding: 12px 12px;">
					<p id="loginusername"><span class="glyphicon glyphicon-user"></span> ${userFirstName} ${userLastName} <span
						class="caret"></span></p>
				</button>
				<ul class="dropdown-menu" role="menu" aria-labelledby="logoutButton">
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="Profile">Profile</a></li>
					<li class="divider"></li>
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