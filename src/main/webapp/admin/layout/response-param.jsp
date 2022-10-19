<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${param.status=='afterdelete'&&param.result==true }">
	<script>
		alert("Xóa thành công")
	</script>
</c:if>
<c:if test="${param.status=='afterdelete'&&param.result==false }">
	<script>
		alert("Xóa thất bại")
	</script>
</c:if>
<c:if test="${param.status=='aftermultipledelete'&&param.result==true }">
	<script>
		alert("Xóa nhiều thành công")
	</script>
</c:if>
<c:if
	test="${param.status=='aftermultipledelete'&&param.result==false }">
	<script>
		alert("Xóa nhiều thất bại")
	</script>
</c:if>
<c:if test="${param.status=='afteredit'&&param.result==true }">
	<script>
		alert("Cập nhật thành công")
	</script>
</c:if>
<c:if test="${param.status=='afteredit'&&param.result==false }">
	<script>
		alert("Cập nhật thất bại")
	</script>
</c:if>
<c:if test="${param.status=='afteradd'&&param.result==true}">
	<script>
		alert("Thêm thành công")
	</script>
</c:if>
<c:if test="${param.status=='afterdeletehasdata' && param.result==true}">
	<script>
		alert("Không thể xóa phần tử có dữ liệu liên kết. Chuyển đổi trạng thái thành Disable");
	</script>
</c:if>
<c:if test="${param.status=='afterdeletehasdata' && param.result==false}">
	<script>
		alert("Không thể xóa phần tử có dữ liệu liên kết. Chuyển đổi trạng thái thành Disable thất bại");
	</script>
</c:if>