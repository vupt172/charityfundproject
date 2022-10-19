/**
 * 
 */
$(document).ready(function() {
	$("#btn-Save").on("click", function(event) {
			if(!validate()){
			event.preventDefault();
		}
		var pathS = window.location.pathname.split("/");
		if ($("#category-id").val() == "") {
			$("form").attr("action","/"+pathS[1]+"/admin/category/?action=add");
					}
		else {
       	$("form").attr("action","/"+pathS[1]+"/admin/category/?action=edit");
		}

	});

})
function validate() {
	var isValidate = true;
	var id = $("#category-id").val();
	var name = $("#category-name").val();
	var description = $("#category-description").val();
	if (name == "") {
		isValidate = false;
		$("#category-name").siblings(".form-message").text("This field is required!");
	}
	return isValidate;
}