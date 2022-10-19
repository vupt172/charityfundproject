$(document).ready(function() {
	$("#btn-Cancel").on("click", function() {
		var pathS = window.location.pathname.split("/");
		window.location.replace("/" + pathS[1] + "/admin/home")
	});
	$("#btn-Save").on("click", function(event) {
		if (!validate()) {
			event.preventDefault();
		}
	})

})
function validate() {
	var isValidate = true;
	var oldPassword = $("#user-oldpassword").val();
	var username = $("#user-username").val();
	var newPassword = $("#user-newpassword").val();
	var confirmPassword = $("#user-confirmpassword").val();
	if (oldPassword == "") {
		isValidate = false;
		$("#user-oldpassword").siblings(".form-message").text("This field is required!");
	}
	else {
		$("#user-oldpassword").siblings(".form-message").text("");
	}
	if (newPassword == "") {
		isValidate = false;
		$("#user-newpassword").siblings(".form-message").text("This field is required!");
	}
	else if (newPassword.length < 8) {
		isValidate = false;
		$("#user-newpassword").siblings(".form-message").text("Password must be equal or greater than 8!");
	}
	else {
		$("#user-newpassword").siblings(".form-message").text("");
	}
	if (confirmPassword == "") {
		isValidate = false;
		$("#user-confirmpassword").siblings(".form-message").text("This field is required!");
	}
	else if (confirmPassword != newPassword) {
		isValidate = false;
		$("#user-confirmpassword").siblings(".form-message").text("Confirm password must be same as new password!");
	}
	else {
		$("#user-confirmpassword").siblings(".form-message").text("");
	}
	return isValidate;
}

