//Profile page Tab Profile Selected 
function editField(f) {
	// save to local to incase of reset
	localStorage['oldVal' + f] = $("#" + f).val();
	$("#" + f).removeAttr("readonly");
}

function onReset() {
	// reset all to the oldValues in local
	var oldValFname = localStorage['oldValFname'];
	var oldValLname = localStorage['oldValLname'];
	var oldValemail = localStorage['oldValemail'];
	var oldValdept = localStorage['oldValdept'];
	$("#divValidateResult").html("");
	if (oldValFname != null) {
		$("#Fname").val(oldValFname);
		$("#Fname").attr("readonly", true);
	}
	if (oldValLname != null) {
		$("#Lname").val(oldValLname);
		$("#Lname").attr("readonly", true);
	}
	if (oldValemail != null) {
		$("#email").val(oldValemail);
		$("#email").attr("readonly", true);
	}
	if (oldValdept != null) {
		$("#dept").val(oldValdept);
		$("#dept").attr("readonly", true);
	}
	localStorage.clear()
}

function onUpdate() {
	// validate fields
	if ($("#Fname").val() == '' || $("#Lname").val() == ''
			|| $("#dept").val() == '' || $("#email").val() == '') {
		$("#divValidateResult").html("Fields cannot be empty");
	} else if (!isValidEmailAddress($("#email").val())) {
		$("#divValidateResult").html("Invalid Email address.");
	} else {
		$("#divValidateResult").html("");
		var ans = confirm("Are you sure you want to update your profile?");
		if (ans) {
			var oldValFname = localStorage['oldValFname'];
			var oldValLname = localStorage['oldValLname'];
			var oldValemail = localStorage['oldValemail'];
			var oldValdept = localStorage['oldValdept'];

			if (oldValFname != null) {
				$("#Fname").attr("readonly", true);
			}
			if (oldValLname != null) {
				$("#Lname").attr("readonly", true);
			}
			if (oldValemail != null) {
				$("#email").attr("readonly", true);
			}
			if (oldValdept != null) {
				$("#dept").attr("readonly", true);
			}
			localStorage.clear();

			$
					.get(
							"SaveProfile",
							{
								type : "profileUpdate",
								ssoid : $("#ssoid").text(),
								fname : $("#Fname").val(),
								lname : $("#Lname").val(),
								email : $("#email").val(),
								dept : $("#dept").val()
							},
							function(data, status) {
								$("#msgModal").html(data);
								$('#msgModal').modal('show');
								var loginname = '<span class="glyphicon glyphicon-user"></span> '
										+ $("#Fname").val()
										+ ' '
										+ $("#Lname").val()
										+ ' <span'
										+ 'class="caret"></span>';
								$('#loginusername').html(loginname);
							});
		} else {
			onReset();
		}
	}
}
// Profile page Tab Password Selected
function passwordMatch() {
	var currpswd = $("#currpwd").val();
	var password = $("#newpwd").val();
	var confirmPassword = $("#cnfmpwd").val();

	if (password != confirmPassword)
		$("#divCheckPasswordMatch").html("Passwords do not match!");
	else {
		$("#divCheckPasswordMatch").html("");
		$("#pswdUpdateBtn").removeAttr("disabled");
	}
}
function checkpswd(user) {
	var pswd = $("#newpwd").val();
	var res = passwordStrength(pswd, user);
	$('#pswdresult').html(res);
}
function passwordUpdate() {

	var ssoid = $("#ssoid").text();
	var newpwd = $("#newpwd").val();
	var currpwd = $("#currpwd").val();
	var cnfmpwd = $("#cnfmpwd").val();

	if (newpwd == '' || currpwd == '') {
		$("#divCheckPasswordMatch")
				.html("Password fields should not be blank.");
	} else if (newpwd == currpwd) {
		$("#divCheckPasswordMatch").html(
				"Current and New Passwords should not be same!");
	} else {
		$("#divCheckPasswordMatch").html("");
		$.get("SaveProfile", {
			type : "pswdUpdate",
			ssoid : ssoid,
			newpwd : newpwd,
			currpwd : currpwd,
		}, function(data, status) {
			$("#msgModal").html(data);
			$('#msgModal').modal('show');
			clearPasswordProfile();
		});
	}
}
function clearPasswordProfile() {
	$("#newpwd").val('');
	$("#currpwd").val('');
	$("#cnfmpwd").val('');
	$("#divCheckPasswordMatch").html("");
	$("#pswdresult").html("");
}

// Login Page Forgot password modal
function verifyForgotPswd() {
	var mod_ssoid = $("#mod_ssoid").val();
	var mod_email = $("#mod_email").val();

	if (mod_ssoid == '') {
		$("#divValidateResult").html("SSO ID is required");
	} else if (mod_email == '') {
		$("#divValidateResult").html("Email is required");
	} else if (!isValidEmailAddress(mod_email)) {
		$("#divValidateResult").html("Invalid Email address.");
	} else {

		// get ForgotPasswordServlet
		$.get("ForgotPassword", {
			mod_ssoid : mod_ssoid,
			mod_email : mod_email
		}, function(data, source) {
			$("#msgModal").html(data);
			$('#msgModal').modal('show');
			clearForgotPasswordModal();
		});
	}
}

function clearForgotPasswordModal() {
	$("#divValidateResult").html("");
	$("#mod_ssoid").val('');
	$("#mod_email").val('');
	$('#forgotPswdModal').modal('hide');
}

function isValidEmailAddress(emailAddress) {
	var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	return pattern.test(emailAddress);
}
