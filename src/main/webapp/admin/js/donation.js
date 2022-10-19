$(document).ready(function(){
	$("#btn-Search").on("click",function(){
		var id=$("#filter-id").val();
		var username=$("#filter-username").val();
		var fundname=$("#filter-fundname").val();
		var pathS=window.location.pathname.split("/");
		window.location.replace("/"+pathS[1]+"/admin/donation/?id="+id+"&username="+username+"&fundname="+fundname);
	});
	$("#btn-Add").on("click",function(){
		var pathS=window.location.pathname.split("/");
		window.location.replace("/"+pathS[1]+"/admin/donation/?action=add");
	})
	$(".btn-Edit").on("click",function(){
		var donationId=$(this).parent().siblings(".donation-id").text();
	
		var pathS=window.location.pathname.split("/");
		window.location.replace("/"+pathS[1]+"/admin/donation/?action=edit&id="+donationId);
	});
	$(".btn-Delete").on("click",function(){
		
		if (confirm("Are you sure to delete this?")) {
			var pathS = window.location.pathname.split("/");
			var donationId=$(this).parent().siblings(".donation-id").text();
			var url = "/"+pathS[1]+"/admin/donation/?action=delete";
			var form = $('<form action="' + url + '" method="post">' +
				'<input type="text" name="id" value="'+donationId + '" />' +
				'</form>');
			$('body').append(form);
			form.submit();
			
			// window.location.replace("/"+pathS[1]+"/admin/foundation/?action=delete&id="+foundationId);
		}
	});
	$("#btn-SelectedDelete").on("click",function(){
			const selectedIdList=[];
		$("#tb-donation input:checked").each(function(){
			selectedIdList.push($(this).parent().siblings(".donation-id").text());
		});
		if(selectedIdList.length==0){
			alert("Không có phần tử nào được chọn");
			return;
		}
			if (confirm("Are you sure to delete those items?")) {
			var pathS = window.location.pathname.split("/");
			var url = "/"+pathS[1]+"/admin/donation/?action=multipledelete";
			var inputs='';
			selectedIdList.forEach((donationId)=>{
				inputs+='<input type="text" name="donation-id" value="'+donationId + '" />';
			});
			var form = $('<form action="' + url + '" method="post">' +
				inputs +
				'</form>');
			$('body').append(form);
			form.submit();
			
			// window.location.replace("/"+pathS[1]+"/admin/foundation/?action=delete&id="+foundationId);
		}
	})
})