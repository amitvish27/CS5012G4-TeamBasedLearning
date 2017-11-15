$(document).ready(function() {
	clearUserUpdateModal();
	var errMsg = $("#errMsg").val();
	if (errMsg != undefined && errMsg != '') {
		alert(errMsg);
	} else {
		var sortBy = document.getElementById("idSortBy").value
		if (sortBy != undefined) {
			changeSortIcon(sortBy);
		}
	}
});

function showEntries() {
	var entries = $("#selShowEntries").val();
	var crtpg = parseInt(document.getElementById("crtpg").value);
	document.location.href = "ManageUser?page=" + crtpg + "&ent=" + entries;
}

function goToUsersPage() {
	var entries = $("#selShowEntries").val();
	var maxpg = parseInt(document.getElementById("maxpg").value);
	var crtpg = parseInt(document.getElementById("crtpg").value);
	var nextPage = $("#userpage").val();
	if (nextPage != undefined) {
		$("#userpage").val('');
		//console.log("crtpg = " + crtpg);
		//console.log("maxpg = " + maxpg);
		if (nextPage >= 0 && nextPage <= maxpg) {
			document.location.href = "ManageUser?page=" + nextPage + "&ent="
					+ entries;
		} else {
			alert("Page Number exceeds maximum");
		}
	}
}

function loadUsersAtPage(u) {
	var entries = $("#selShowEntries").val();
	var pgnum = parseInt(document.getElementById("crtpg").value);
	//console.log("pgnum = " + pgnum);
	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}
	document.location.href = "ManageUser?page=" + pgnum + "&ent=" + entries;
}

function onUserAdd() {
	clearUserUpdateModal();
	$("#updateUser").text('Add New User');
	$("#addUpdateForm").attr('action', 'AddUser');
	$("#modalTitle").text('Add User');
	$("#editUserModal").modal('show');
}

function onUserEdit() {
	var id = $("input[name='sel_user_id']:checked").val();
	var ssoid = $("#sel_user_ssoid" + id).text();
	var fname = $("#sel_user_fname" + id).text();
	var lname = $("#sel_user_lname" + id).text();
	var email = $("#sel_user_email" + id).text();
	var dept = $("#sel_user_dept" + id).text();
	var role = $("#sel_user_role" + id).val();
	var active = $("#sel_user_active" + id).val();
	var deleted = $("#sel_user_deleted" + id).val();

	if (id != undefined) {
		$("#updateUser").text('Update User');
		$("#modalTitle").text('Edit User');
		$("#addUpdateForm").attr('action', 'UpdateUser');
		$("#editUserModal").modal('show');
		$("#mod_user_id").val(id);
		$("#mod_user_ssoId").val(ssoid);
		$("#mod_user_fname").val(fname);
		$("#mod_user_lname").val(lname);
		$("#mod_user_email").val(email);
		$("#mod_user_dept").val(dept);
		$("input[name=mod_user_role][value=" + role + "]").attr('checked',
				'checked');
		$("input[name=mod_user_active][value=" + active + "]").attr('checked',
				'checked');
		$("input[name=mod_user_deleted][value=" + deleted + "]").attr(
				'checked', 'checked');

	} else {
		alert("Please select a record");
	}
}

function clearUserUpdateModal() {
	$("#mod_user_id").val('');
	$("#mod_user_ssoId").val('');
	$("#mod_user_fname").val('');
	$("#mod_user_lname").val('');
	$("#mod_user_email").val('');
	$("#mod_user_dept").val('');
	$("input[name=mod_user_role][value=0]").attr('checked', 'checked');
	$("input[name=mod_user_active][value=1]").attr('checked', 'checked');
	$("input[name=mod_user_deleted][value=0]").attr('checked', 'checked');
}

function onUserDelete() {
	var id = $("input[name='sel_user_id']:checked").val();
	if (id != undefined) {
		var ans = confirm("Are you sure you want to delete the selected record?");
		if (ans) {

			$.post("DelUser", {
				id : id
			}, function(data, status) {
				window.location.reload();
			});
		}
	} else {
		alert("Please select a record");
	}
}

function isValidEmail() {
	var mod_email = $("#mod_user_email").val();
	if (!isValidEmailAddress(mod_email)) {
		$("#divValidateResult").html("Invalid Email address.");
		$("#updateUser").attr('disabled', 'disabled');
	} else {
		$("#updateUser").removeAttr('disabled');
		$("#divValidateResult").html("");
	}
}

function changeSortIcon(v) {
	var sortDir = document.getElementById("idSortDir").value

	if (sortDir === "" || sortDir == undefined) {
		$("#" + v + "SortIcon").attr('class', 'glyphicon glyphicon-sort');

		$("#" + v + "SortDir").val('ASC');
	} else if (sortDir === "ASC") {
		$("#" + v + "SortIcon").attr('class',
				'glyphicon glyphicon-sort-by-attributes');

		$("#" + v + "SortDir").val('DESC');
	} else if (sortDir === "DESC") {
		$("#" + v + "SortIcon").attr('class',
				'glyphicon glyphicon-sort-by-attributes-alt');
		$("#" + v + "SortDir").val('');
	}

	return true;
}

function sortColumn(v) {
	if (!changeSortIcon(v)) {
		return;
	}
	var d = $("#" + v + "SortDir").val();
	var url = window.location.search;
	var sortIndex = url.indexOf('sort=');
	var dirIndex = url.indexOf('dir=');
	if (sortIndex > -1) {
		url = url.substr(0, sortIndex - 1);
	}
	if (url.indexOf('?') > -1) {
		url += "&sort=" + v + "&dir=" + d; // if no sort already then add
	} else {
		url += "?sort=" + v + "&dir=" + d; // if no sort already then add
	}

	//console.log("new url :" + url);
	document.location.href = "ManageUser" + url;
}

function onSearch() {
	var searchText = $("#idsearchtext").val();
	document.location.href = "ManageUser" + "?search="+searchText;
}
function onRefresh() {
	$("#idsearchtext").val('');
	document.location.href = "ManageUser";
}

