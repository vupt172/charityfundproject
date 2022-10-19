$(document).ready(function() {
	$("#btn-Save").on("click", function(event) {
		if (!validate()) {
			event.preventDefault();
		}
	});

})
function validate() {
	var isValidate = true;
	//var username = $("#username").val();
	var fullname = $("#fullname").val();
	var sdt = $("#sdt").val();
	var email = $("#email").val();
	var diachi = $("#address").val();
	var regexMail = /^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/g;

	if (fullname == "") {
		isValidate = false;
		$("#fullname").parent().siblings(".form-message").text("Fullname is required!");
	}
	else{s
		$("#fullname").parent().siblings(".form-message").text("a");
	}

	if (sdt == "") {
		isValidate = false;
		$("#sdt").parent().siblings(".form-message").text("SDT is required!");
	}
	else {
		$("#sdt").parent().siblings(".form-message").text("");
	}
	if (email == "") {
		isValidate = false;
		$("#email").parent().siblings(".form-message").text("Email is required!");
	}
	else if (!regexMail.test(email)) {
		isValidate = false;
		$("#email").parent().siblings(".form-message").text("Email is invalid!");
	}
	else {
		$("#email").parent().siblings(".form-message").text("");
	}

	return isValidate;
}