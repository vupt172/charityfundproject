<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User</title>
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
<link rel="stylesheet" href="../css/body-content.css">
<link rel="stylesheet" href="../css/status.css">
<script src="../js/user.js"></script>
<style>
.user-email{min-width:200px;
}
</style>
</head>

<body>
	<jsp:include page="../layout/response-param.jsp"></jsp:include>
	<c:if test="${param.status=='updateStatusToInactive'&&param.result==true}"><script>alert("User có dữ liệu đang sử dụng.\nXóa thất bại,chuyển trạng thái user thành Không hoạt động")</script></c:if>
	<c:if test="${param.status=='afterdelete'&&param.result==false&&param.cause=='adminrole'}"><script>alert("Không thể xóa tài khoản là ADMIN")</script></c:if>
	<c:if test="${param.status=='afterupdatepassword'&&param.result==true}"><script>alert("Cập nhật mật khẩu thành công.")</script></c:if>
	<%@ include file="../layout/admin-header.jsp" %>  
	<div id="content">
		<div id="content-header">
			<h4>Quản Lý Tài Khoản</h4>
			<div id="table-filter" class="d-flex py-2">
				<input id="filter-username" type="text" placeholder="Username"
					class="form-control" value="${param.username}"> <input
					id="filter-sdt" type="text" placeholder="SDT" class="form-control"
					value="${param.sdt}"> <input id="filter-email" type="text"
					placeholder="Email" class="form-control" value="${param.email}">
				<select id="filter-role">
					<option>Role</option>
					<c:forEach var="role" items="${roles}">
						<c:if test="${role==param.role}">
							<option selected>${role}</option>
						</c:if>
						<c:if test="${role!=param.role}">
							<option >${role}</option>
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
		<table class="table table-bordered table-sm table-hover">
			<thead class="table-primary">
				<th>ID</th>
				<th>Username</th>
				<th>Role</th>
				<th>Fullname</th>
				<th>SDT</th>
				<th>Email</th>
				<th>Dia Chi</th>
				<th>Status</th>
				<th style="width: 120px">Function</th>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">
					<tr>
					
						<td class="user-id">${user.id}</td>
						<td class="user-username">${user.username}</td>
						<td class="user-role"><c:choose>
								<c:when test="${user.role==1}"><i class="fa-solid fa-user-gear" ></i> Admin</c:when>
								<c:when test="${user.role==2}"><i class="fa-solid fa-user-tag"></i> User</c:when>
								<c:otherwise>Không rõ</c:otherwise>
							</c:choose></td>
						<td class="user-fullname">${user.fullName }</td>
						<td class="user-sdt"><i class="fa-solid fa-phone"></i> ${user.sdt}</td>
						<td class="user-email"><i class="fa-solid fa-envelope"></i> ${user.email}</td>
						<td class="user-diachi">${user.diachi}</td>
						<td class="user-status"><c:choose>
								<c:when test="${user.status=='Active'}">
									<span class="badge badge-success">Active</span>
								</c:when>
								<c:when test="${user.status=='Inactive'}">
									<span class="badge badge-warning">Inactive</span>
								</c:when>
								<c:when test="${user.status=='NotActivated'}">
									<span class="badge badge-secondary">NotActivated</span>
								</c:when>
								<c:when test="${user.status=='Banned'}">
									<span class="badge badge-danger">Banned</span>
								</c:when>
							</c:choose></td>
						<td>
							<button  type="button" class="btn btn-warning btn-sm btn-Edit">Edit</button>
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
									test="${param.id!=null||param.name!=null||param.email!=null||param.role!=null}">
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/user/?page=${i}&username=${param.username}&email=${param.email}&sdt=${param.sdt}&role=${param.role}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/user/?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when
							test="${param.id!=null||param.name!=null||param.email!=null||param.role!=null}">
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/user/?page=${i}&username=${param.username}&email=${param.email}&sdt=${param.sdt}&role=${param.role}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/user/?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</div>
	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>
</body>
</html>