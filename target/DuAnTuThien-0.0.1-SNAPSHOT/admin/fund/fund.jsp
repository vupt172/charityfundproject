<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Fund</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="../js/fund.js"></script>
<link rel="stylesheet" href="../css/body-content.css">
<link rel="stylesheet" href="../css/status.css">
</head>
<body>
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
	<c:if
		test="${param.status=='aftermultipledelete'&&param.result==true }">
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
	<jsp:include page="../layout/admin-header.jsp"></jsp:include>
	<div id="content">
		<div id="content-header">
			<h4>Quản Lý Qũy Quyên Góp</h4>
			<div id="table-filter" class="d-flex py-2">
				<input id="filter-id" type="text" placeholder="ID"
					class="form-control" value="${param.id}"> <input
					id="filter-name" type="text" placeholder="Name"
					class="form-control" value="${param.name}"> <input
					id="filter-foundation" type="text" placeholder="Foundation"
					class="form-control" value="${param.foundation}"> <select
					id="filter-category" class="form-select aria-label="Defaultselectexample"">
					<option selected>Category</option>
					<c:forEach var="category" items="${categoryList}">
						<c:if test="${category.name==param.category_name}">
							<option selected>${category.name}</option>
						</c:if>
						<c:if test="${category.name!=param.category_name}">
							<option>${category.name}</option>
						</c:if>
					</c:forEach>
				</select>

				<button id="btn-Search" type="button"
					class="btn btn-success btn-sm mx-1">
					<i class="fa-solid fa-magnifying-glass"></i> Tìm Kiếm
				</button>
				<button id="btn-Add" type="button"
					class="btn btn-primary btn-sm mx-1">
					<i class="fa-solid fa-plus"></i> Thêm Mới
				</button>
			</div>
		</div>
		<table id="tb-fund" class="table table-bordered table-sm table-hover">
			<thead class="table-primary">
				<th><button type="button" class="btn btn-danger btn-sm "
						id="btn-SelectedDelete">
						<i class="fa-solid fa-trash"></i>
					</button></th>
				<th>ID</th>
				<th>Name</th>
				<th>Expected Result</th>
				<th style="width:120px">Status</th>
				<th>Created Date</th>
				<th>End Date</th>
				<th>Category</th>
				<th>Foundation</th>
				<th style="width: 120px">Function</th>
			</thead>
			<tbody>
				<c:forEach var="fund" items="${fundList}">
					<tr>
						<td style="width: 30px"><input type="checkbox"></td>
						<td class="fund-id">${fund.id}</td>
						<td class="fund-name">${fund.name}</td>
						<td class="fund-expected-result">${fund.expectedResult}</td>
						<td class="fund-status"><c:choose>
								<c:when test="${fund.status=='Opening'}">
									<span class="badge badge-success">Opening</span>
								</c:when>
								<c:when test="${fund.status=='Waiting'}">
									<span class="badge badge-secondary">Waiting</span>
								</c:when>
								<c:when test="${fund.status=='Close'}">
									<span class="badge badge-warning">Close</span>
								</c:when>
								<c:when test="${fund.status=='Finish'}"><span class="badge badge-info">Finish</span></c:when>
							</c:choose></td>
						<td class="fund-created-date">${fund.createdDate}</td>
						<td class="fund-end-date">${fund.endDate}</td>
						<td class="fund-category-name">${fund.category.name}</td>
						<td class="fund-foundation-name">${fund.foundation.name}</td>
						<td>
							<button type="button" class="btn btn-warning btn-sm btn-edit">Edit</button>
							<button type="button" class="btn btn-danger btn-sm btn-delete">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-center">
				<c:forEach var="i" begin="1" end="${totalPage}">
					<c:choose>
						<c:when test="${i==param.page}">
							<c:choose>
								<c:when
									test="${param.id!=null||param.name!=null||param.email!=null}">
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/fund/?page=${i}&id=${param.id}&name=${param.name}&foundation_name=${param.foundation_name}&category_name=${param.category_name}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/fund/?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when
							test="${param.id!=null||param.name!=null||param.email!=null}">
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/fund/?page=${i}&id=${param.id}&name=${param.name}&foundation_name=${param.foundation_name}&category_name=${param.category_name}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/fund/?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</div>

	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>

</body>
</html>