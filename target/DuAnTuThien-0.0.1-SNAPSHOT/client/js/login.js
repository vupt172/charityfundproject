$(document).ready(function() {
	$("#btn-Login").on("click", function() {

		if (!validate()) {
			event.preventDefault();
		}
	});
	$("#btn-SendPasswordEmail").on("click", function(event) {
		if (!validatePasswordEmail()) {
			event.preventDefault();
		}
		var pathS = window.location.pathname.split("/");
		var receivedPasswordEmail = $("#receivedPasswordEmail");
		var url = "/" + pathS[1] + "/login?action=resetpassword";
		var form = $('<form action="' + url + '" method="post">' +
			'<input type="text" name="email" value="' + receivedPasswordEmail + '" />' +
			'</form>');
		$('body').append(form);
		form.submit();
	})
})
function validate() {
	var isValidate = true;
	if ($("#username").val() == "") {
		isValidate = false;
		$("#username").parent().siblings(".form-message").text("Username is required!");
	}
	else {
		$("#username").parent().siblings(".form-message").text("");
	}

	if ($("#password").val() == "") {
		isValidate = false;
		$("#password").parent().siblings(".form-message").text("Password is required!");
	}
	else {
		$("#password").parent().siblings(".form-message").text("");
	}
	return isValidate;
}
function validatePasswordEmail() {
	var isValidate = true;

	var regexMail = /^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/g;
	var receivedPasswordEmail = $("#receivedPasswordEmail").val();
	if (receivedPasswordEmail == "") {
		isValidate = false;
		$("#receivedPasswordEmail").parent().siblings(".form-message").text("Email is required!");
	}
	else if (!regexMail.test(receivedPasswordEmail)) {
		isValidate = false;
		$("#receivedPasswordEmail").parent().siblings(".form-message").text("Email is invalid!");
	}
	return isValidate;
}
