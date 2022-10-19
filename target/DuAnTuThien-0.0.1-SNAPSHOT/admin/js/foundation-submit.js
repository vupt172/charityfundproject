$(document).ready(function(){
	$("#btn-Cancel").on("click",function(){
	 var pathS=window.location.pathname.split("/");
	 window.location.replace("/"+pathS[1]+"/admin/foundation/");
	});
	$("#btn-Save").on("click",function(event){
		if(!validate()){
			event.preventDefault();
		}
		var foundationId=$("#foundation-id").val();
		var pathS=window.location.pathname.split("/");
		if(foundationId==""){
			$("form").attr("action","/"+pathS[1]+"/admin/foundation/?action=add")
		}
		else{
			$("form").attr("action","/"+pathS[1]+"/admin/foundation/?action=edit")
		}
	})
	function validate(){
		var isValidate=true;
		var id=$("#foundation-id").val()
		var name=$("#foundation-name").val();
		var description=$("#foundation-description").val();
		var email=$("#foundation-email").val();
		var regexMail=/^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/g;
		//validate name
		if(name==""){
			isValidate=false;
			$("#foundation-name").siblings(".form-message").text("This field is require!");
		}
		else{
			$("#foundation-name").siblings(".form-message").text("");
		}
		//validate email
		if(email==""){;
			isValidate=false;
			$("#foundation-email").siblings(".form-message").text("This field is require!");
		}
		else if(!regexMail.test(email)){
			isValidate=false;
			$("#foundation-email").siblings(".form-message").text("Email is not valid !!");
		}
		else{
				$("#foundation-email").siblings(".form-message").text("");
		}
		return isValidate;
	}
})