
$(document).ready(function() {
	$("#btn-Search").on("click", function() {
		var id = $("#filter-id").val();
		var name = $("#filter-name").val();
		var email = $("#filter-email").val();
		var pathS = window.location.pathname.split("/");
		window.location.replace("/" + pathS[1] + "/admin/foundation/?id=" + id + "&name=" + name + "&email=" + email);
	});
	$("#btn-Add").on("click", function() {
		var pathS = window.location.pathname.split("/");
		window.location.replace("/" + pathS[1] + "/admin/foundation/?action=add");
	});
	$(".btn-Edit").on("click",function(){
		var pathS = window.location.pathname.split("/");
		var foundationId=$(this).parent().siblings(".foundation-id").text();
		window.location.replace("/"+pathS[1]+"/admin/foundation/?action=edit&id="+foundationId);
	});
	$(".btn-Delete").on("click", function(event) {
		if (confirm("Are you sure to delete this?")) {
			var pathS = window.location.pathname.split("/");
			var foundationId = $(this).parent().siblings(".foundation-id").text();
			var url = "/"+pathS[1]+"/admin/foundation/?action=delete";
			var form = $('<form action="' + url + '" method="post">' +
				'<input type="text" name="foundationId" value="'+foundationId + '" />' +
				'</form>');
			$('body').append(form);
			form.submit();
			
			// window.location.replace("/"+pathS[1]+"/admin/foundation/?action=delete&id="+foundationId);
		}
	});
	$("#btn-SelectedDelete").on("click",function(){
		const selectedIdList=[];
		$("#tb-foundation input:checked").each(function(){
			selectedIdList.push($(this).parent().siblings(".foundation-id").text());
		});
		if(selectedIdList.length==0){
			alert("Không có phần tử nào được chọn");
			return;
		}
			if (confirm("Are you sure to delete those items?")) {
			var pathS = window.location.pathname.split("/");
			var foundationId = $(this).parent().siblings(".foundation-id").text();
			var url = "/"+pathS[1]+"/admin/foundation/?action=multipledelete";
			var inputs='';
			selectedIdList.forEach((foundationId)=>{
				
				inputs+='<input type="text" name="foundationId" value="'+foundationId + '" />';
			});
			var form = $('<form action="' + url + '" method="post">' +
				inputs +
				'</form>');
			$('body').append(form);
			form.submit();
			
			// window.location.replace("/"+pathS[1]+"/admin/foundation/?action=delete&id="+foundationId);
		}
	});
})