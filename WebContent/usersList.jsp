<%@page import="edu.umsl.java.beans.PageBean"%>
<%@page import="java.util.*"%>
<%@page import="edu.umsl.java.beans.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<link href="css/template.css" rel="stylesheet">

<script src="js/manageUsersPage.js"></script>
<script src="js/profilePage.js"></script>
</head>

<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>

		<!-- Content Body -->
		<div class="container-fluid ">
			<div class="container">
				<div class="panel panel-default top-buffer">
					<div class="panel-body ">
						<fieldset>
							<legend style="text-align:center;"> User Management </legend>
							<input type='hidden' id="idSortBy" value="${sortBy}" /> <input
								type='hidden' id="idSortDir" value="${sortDir}" />
							<table class="table table-hover">
								<thead>
									<tr>
										<th style="padding-left: 5em" colspan=3>Users list</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th>&nbsp;</th>
										<th><button type="button" class="btn btn-link btn-sm"
												onclick="onUserAdd()">
												<span class="glyphicon glyphicon-plus"></span> Add new
												record
											</button></th>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<th>SSOID<input type="hidden" id="ssoidSortDir" value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('ssoid')">
												<span id="ssoidSortIcon" class="glyphicon glyphicon-sort"></span>
											</button>
										</th>
										<th>Firstname <input type="hidden" id="fnameSortDir"
											value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('fname')">
												<span id="fnameSortIcon" class="glyphicon glyphicon-sort"></span>
											</button>
										</th>
										<th>Lastname<input type="hidden" id="lnameSortDir"
											value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('lname')">
												<span id="lnameSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
										<th>Email<input type="hidden" id="emailSortDir" value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('email')">
												<span id="emailSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
										<th>Department<input type="hidden" id="deptSortDir"
											value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('dept')">
												<span id="deptSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
										<th>Role <input type="hidden" id="roleSortDir" value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('role')">
												<span id="roleSortIcon" class="glyphicon glyphicon-sort"></span>
											</button>
										</th>
										<th>Active? <input type="hidden" id="activeSortDir"
											value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('active')">
												<span id="activeSortIcon" class="glyphicon glyphicon-sort"></span>
											</button>
										</th>
										<th>Deleted?<input type="hidden" id="deletedSortDir"
											value="" />
											<button type="button" class="btn btn-link btn-sm"
												onclick="sortColumn('deleted')">
												<span id="deletedSortIcon" class="glyphicon glyphicon-sort"></span>
											</button></th>
									</tr>
								</thead>
								<c:forEach var="user" items="${usrlist}">
									<tbody>
										<tr>
											<td><input type="radio" name="sel_user_id"
												id="sel_user_id" value="${user.getId()}"></td>
											<td><p id="sel_user_ssoid${user.getId()}">${user.getSsoid()}</p></td>
											<td><p id="sel_user_fname${user.getId()}">${user.getFname()}</p></td>
											<td><p id="sel_user_lname${user.getId()}">${user.getLname()}</p></td>
											<td><p id="sel_user_email${user.getId()}">${user.getEmail()}
												</p></td>
											<td><p id="sel_user_dept${user.getId()}">${user.getDept()}</p></td>
											<td><input id="sel_user_role${user.getId()}"
												type="hidden" value="${user.getRole()}" /> <c:choose>
													<c:when test="${user.getRole() eq 2}">
															Admin
														</c:when>
													<c:when test="${user.getRole() eq 1}">
															Instructor
														</c:when>
													<c:otherwise>
															Student
														</c:otherwise>
												</c:choose></td>
											<td><input id="sel_user_active${user.getId()}"
												type="hidden" value="${user.getActive()}" /> <c:choose>
													<c:when test="${user.getActive() eq 1}">
															Yes
														</c:when>
													<c:otherwise>
															No
														</c:otherwise>
												</c:choose></td>
											<td><input id="sel_user_deleted${user.getId()}"
												type="hidden" value="${user.getDeleted()}" /> <c:choose>
													<c:when test="${user.getDeleted() eq 1}">
															Yes
														</c:when>
													<c:otherwise>
															No
														</c:otherwise>
												</c:choose></td>
										</tr>
									</tbody>
								</c:forEach>

								<tfoot>
									<tr>
										<td colspan=2><button type="button" value="edit"
												class="btn btn-default" onclick="onUserEdit()">Edit</button></td>
										<td><button type="button" value="delete"
												class="btn btn-default" onclick="onUserDelete()">Delete</button></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td colspan=2>
											<div class="form-inline">
												<div class="form-group">
													Show <select class="form-control" id="selShowEntries"
														onchange="showEntries()">
														<option value=10
															<c:if test="${recPerPgStr eq 10}"> selected</c:if>>10</option>
														<option value=25
															<c:if test="${recPerPgStr eq 25}"> selected</c:if>>25</option>
														<option value=50
															<c:if test="${recPerPgStr eq 50}"> selected</c:if>>50</option>
														<option value=100
															<c:if test="${recPerPgStr eq 100}"> selected</c:if>>100</option>
													</select> Entries
												</div>
											</div>
										</td>
										<td colspan=2>
											<table class="input-group">
												<tr>
													<td>
														<button id="page_prevBtn" type="button"
															onclick="loadUsersAtPage(0)" style="height: 2.4em"
															class="btn btn-link"
															<c:if test="${crtpg <= 1}">disabled="disabled"</c:if>>

															<span class="glyphicon glyphicon-triangle-left"></span>
														</button>
													</td>
													<td><input type="hidden" id="crtpg" value="${crtpg}" />
														<input type="hidden" id="maxpg" value="${maxpg}" /> <input
														id="userpage" name="userpage" type="text"
														style="width: 4em; height: 2.4em" class="form-control "
														placeholder="${crtpg}/${maxpg}"
														onfocusout="goToUsersPage()" /></td>
													<td>
														<button id="page_nextBtn" type="button"
															onclick="loadUsersAtPage(1)" style="height: 2.4em"
															class="btn btn-link"
															<c:if test="${crtpg >= maxpg}">disabled="disabled"</c:if>>
															<span class="glyphicon glyphicon-triangle-right"></span>
														</button>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</tfoot>
							</table>
					</fieldset>
					</div>
				</div>
			</div>
			<div>
				<input id="errMsg" type="hidden" value="${errMsg}" />
			</div>
		</div>

		<!-- Message Modal -->
		<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<form class="form-horizontal" id="addUpdateForm"
						action="UpdateUser" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								onclick="clearUserUpdateModal()">&times;</button>
							<h4 id="modalTitle" class="modal-title">Edit User</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<div class="col-md-4 col-sm-4">
									<input id="mod_user_id" type="hidden" name="mod_user_id" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 col-sm-4"></div>
								<div class="col-md-4 col-sm-4 errorFont" id="divValidateResult"></div>
							</div>
							<div class="form-group">
								<label for="mod_user_ssoId"
									class="control-label col-md-4 col-sm-4">SsoId</label>
								<div class="col-md-4 col-sm-4">
									<input id="mod_user_ssoId" type="text" name="mod_user_ssoId"
										class="form-control" required />
								</div>
							</div>

							<div class="form-group">
								<label for="mod_user_fname"
									class="control-label col-md-4 col-sm-4">First Name</label>
								<div class="col-md-4 col-sm-4">
									<input id="mod_user_fname" type="text" name="mod_user_fname"
										class="form-control" required />
								</div>
							</div>

							<div class="form-group">
								<label for="mod_user_lname"
									class="control-label col-md-4 col-sm-4">Last Name</label>
								<div class="col-md-4 col-sm-4">
									<input id="mod_user_lname" type="text" name="mod_user_lname"
										class="form-control" required />
								</div>
							</div>

							<div class="form-group">
								<label for="mod_user_email"
									class="control-label col-md-4 col-sm-4">Email</label>
								<div class="col-md-4 col-sm-4">
									<input id="mod_user_email" type="email" name="mod_user_email"
										class="form-control" onfocusout="isValidEmail()" required />
								</div>
							</div>

							<div class="form-group">
								<label for="mod_user_dept"
									class="control-label col-md-4 col-sm-4">Department</label>
								<div class="col-md-4 col-sm-4">
									<input id="mod_user_dept" type="text" name="mod_user_dept"
										class="form-control" required />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4">Role</label>
								<div class="col-md-2 col-sm-2">
									<div class="radio">
										<label for="mod_user_role" class="radio-inline"><input
											type="radio" name="mod_user_role" value="2">Admin</label>
									</div>
									<div class="radio">
										<label class="radio-inline"><input type="radio"
											name="mod_user_role" value="1">Instructor</label>
									</div>
									<div class="radio">
										<label for="mod_user_role" class="radio-inline"><input
											type="radio" name="mod_user_role" value="0">Student</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="mod_user_active"
									class="control-label col-md-4 col-sm-4">Is Active?</label>
								<div class="col-md-2 col-sm-2">
									<div class="radio">
										<label class="radio-inline"><input type="radio"
											name="mod_user_active" value="1">Active</label>
									</div>
									<div class="radio">
										<label class="radio-inline"><input type="radio"
											name="mod_user_active" value="0">Inactive</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="mod_user_deleted"
									class="control-label col-md-4 col-sm-4">Deleted</label>
								<div class="col-md-4 col-sm-4">
									<div class="radio">
										<label class="radio-inline"><input type="radio"
											name="mod_user_deleted" value="1">Yes</label>
									</div>
									<div class="radio">
										<label class="radio-inline"><input type="radio"
											name="mod_user_deleted" value="0">No</label>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="updateUser" type="submit" class="btn btn-default">Update</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" onclick="clearUserUpdateModal()">Close</button>
						</div>
					</form>
				</div>
			</div>

		</div>

		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>
