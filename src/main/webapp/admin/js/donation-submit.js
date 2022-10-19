$(document).ready(function() {
	$("#btn-Save").on("click", function(event) {
		if(!validate()){
			event.preventDefault();
		}
		var pathS = window.location.pathname.split("/");
		var id = $("#id").val();
		if (id == "") {
			$("form").attr("action", "/" + pathS[1] + "/admin/donation/?action=add")
		}
		else {
			$("form").attr("action", "/" + pathS[1] + "/admin/donation/?action=edit")
		}
	});
})
function validate() {
	var isValidate = true;
	var donationAmount = $("#donation-amount").val();
	var donationMessage = $("#donation-message").val();
	var username = $("#username option:selected").text();
	var fundname = $("#fundname option:selected").text();
	if (donationAmount == "") {
		isValidate = false;
		$("#donation-amount").siblings(".form-message").text("This field is required!");
	}
	else {
		$("#donation-amount").siblings(".form-message").text("");
	}
	if (username == "Username") {
		isValidate = false;
		$("#username").siblings(".form-message").text("This field is required!");
	}
	else {
		$("#username").siblings(".form-message").text("");
	}
	if (fundname == "Fundname") {
		isValidate = false;
		$("#fundname").siblings(".form-message").text("This field is required!");
	}
	else {
		$("#fundname").siblings(".form-message").text("");
	}
	return isValidate;
}