/**
 * 
 */
 $(document).ready(function(){
	$("#btn-Search").on("click",function(){
		var pathS=window.location.pathname.split("/");
		var id=$("#filter-id").val();
		var name=$("#filter-name").val();
		window.location.replace("/"+pathS[1]+"/admin/category/?id="+id+"&name="+name);
	})
	$("#btn-Add").on("click",function(){
		var pathS=window.location.pathname.split("/");
		window.location.replace("/"+pathS[1]+"/admin/category/?action=add");
	})
	$(".btn-edit").on("click",function(){
		var pathS = window.location.pathname.split("/");
		var categoryId=$(this).parent().siblings(".category-id").text();
		window.location.replace("/"+pathS[1]+"/admin/category/?action=edit&id="+categoryId);
	});
	$(".btn-delete").on("click",function(){
		if (confirm("Are you sure to delete this?")) {
			var pathS = window.location.pathname.split("/");
			var categoryId = $(this).parent().siblings(".category-id").text();
			var url = "/"+pathS[1]+"/admin/category/?action=delete";
			var form = $('<form action="' + url + '" method="post">' +
				'<input type="text" name="category-id" value="'+categoryId + '" />' +
				'</form>');
			$('body').append(form);
			form.submit();
	}
	});
	$("#btn-SelectedDelete").on("click",function(){
				const selectedIdList=[];
		$("#tb-category input:checked").each(function(){
			selectedIdList.push($(this).parent().siblings(".category-id").text());
		});
		if(selectedIdList.length==0){
			return;
		}
			if (confirm("Are you sure to delete those items?")) {
			var pathS = window.location.pathname.split("/");
			var url = "/"+pathS[1]+"/admin/category/?action=multipledelete";
			var inputs='';
			selectedIdList.forEach((categoryId)=>{
				
				inputs+='<input type="text" name="category-id" value="'+categoryId + '" />';
			});
			var form = $('<form action="' + url + '" method="post">' +
				inputs +
				'</form>');
			$('body').append(form);
			form.submit();
			
			// window.location.replace("/"+pathS[1]+"/admin/foundation/?action=delete&id="+foundationId);
		}
	});
});