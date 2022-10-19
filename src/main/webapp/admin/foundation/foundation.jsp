<%@page import="Model.Foundation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Foundation</title>

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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/body-content.css">
<script type="text/javascript" src="../js/foundation.js"></script>
<style>
.foundation-description {
	display: -webkit-box;
	max-height: 3.2rem;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: normal;
	-webkit-line-clamp: 2
}

.foundation-email {
	min-width: 200px;
}
</style>
</head>

<body>
	<jsp:include page="../layout/response-param.jsp"></jsp:include>
	<%@ include file="../layout/admin-header.jsp"%>
	<div id="content">
		<div id="content-header">
			<h4>Quản Lý Nhà Tổ Chức Qũy</h4>
			<div id="table-filter" class="d-flex py-2">
				<input id="filter-id" type="text" placeholder="ID"
					class="form-control" value="${param.id}"> <input
					id="filter-name" type="text" placeholder="Name"
					class="form-control" value="${param.name}"> <input
					id="filter-email" type="text" placeholder="Email"
					class="form-control" value="${param.email}">
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
		<table id="tb-foundation"
			class="table table-bordered table-sm table-hover">
			<thead class="table-primary">
				<th><button type="button" class="btn btn-danger btn-sm "
						id="btn-SelectedDelete">
						<i class="fa-solid fa-trash"></i>
					</button></th>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Email</th>
				<th>Status</th>
				<th style="width: 120px">Function</th>
			</thead>
			<tbody>
				<c:forEach var="foundation" items="${foundationList}">
					<tr>
						<td style="width: 30px"><input type="checkbox"></td>
						<td class="foundation-id">${foundation.id}</td>
						<td class="foundation-name">${foundation.name}</td>
						<td class="foundation-description">${foundation.description}</td>
						<td class="foundation-email"><i class="fa-solid fa-envelope"></i>
							${foundation.email}</td>
						<td class="foundation-status"><c:choose>
								<c:when test="${foundation.status=='Enable'}">
									<span class="badge badge-success">Enable</span>
								</c:when>
								<c:when test="${foundation.status=='Disable'}">
									<span class="badge badge-secondary">Disable</span>
								</c:when>
							</c:choose></td>
						<td>
							<button type="button" class="btn btn-warning btn-sm btn-Edit">Edit</button>
							<button type="button" class="btn btn-danger btn-sm btn-Delete">Delete</button>
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
										href="${pageContext.request.contextPath}/admin/foundation/?page=${i}&id=${param.id}&name=${param.name}&email=${param.email}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/foundation/?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when
							test="${param.id!=null||param.name!=null||param.email!=null}">
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/foundation/?page=${i}&id=${param.id}&name=${param.name}&email=${param.email}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/foundation/?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</div>

	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>
</body>
</html>