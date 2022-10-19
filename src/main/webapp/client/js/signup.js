$(document).ready(function() {
	$("#btn-Signup").on("click", function(event) {
		if (!validate()) {
			event.preventDefault();
		}
	})

})
function validate() {
var isValidate = true;
	var username = $("#username");
	var fullname = $("#fullname");
	var email = $("#email");
	var address = $("#address");
	var sdt = $("#sdt");
	var regexMail = /^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/g;
	if (username.val() == "") {
		isValidate = false;
		username.parent().siblings(".form-message").text("Username is required!");
	}
	else {
		username.parent().siblings(".form-message").text("");
	}
	if (fullname.val() == "") {
		isValidate = false;
		fullname.parent().siblings(".form-message").text("Fullname is required!");
	}
	else {
		fullname.parent().siblings(".form-message").text("");
	}
	if (email.val() == "") {
		isValidate = false;
		email.parent().siblings(".form-message").text("Email is required!");
	}
	else if (!regexMail.test(email.val())) {
		alert("email is invalid")
		isValidate = false;
		email.parent().siblings(".form-message").text("Email is invalid!");
	}
	else {
		email.parent().siblings(".form-message").text("");
	}

	if (sdt.val() == "") {
		isValidate = false;
		sdt.parent().siblings(".form-message").text("SDT is required!");
	}
	else {
		sdt.parent().siblings(".form-message").text("");
	}
	return isValidate;
}