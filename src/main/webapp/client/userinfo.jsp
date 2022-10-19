<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info</title>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/client/css/content-format.css">
<script src="client/js/userinfo.js"></script>
<style>
main {
	
}

form {
	background: #fff;
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
}

.input-group-text {
	width: 100px;
}
</style>
</head>
<body>
	<%@ include file="layout/client-header.jsp"%>
	<main class="m-3">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">Thông
					tin user</li>
			</ol>
		</nav>
		<form method="post" action="${pageContext.request.contextPath}/user?action=update" class="w-75 m-3 m-auto p-3">
			<div class="content-header">
				<h3 class="content-title">THÔNG TIN CÁ NHÂN</h3>
			</div>
			<div class="form-group">
				<div class="input-group mb-3 d-flex flex-md-row">
					<span class="input-group-text" id="basic-addon1">Username</span> <input id="username" name="username"
						type="text" class="form-control" placeholder="Username"
						aria-label="Username" aria-describedby="basic-addon1"
						value="${sessionScope.user.username}" readonly>
				</div>
				<span class="form-message form-text text-muted"></span>
			</div>
			<div class="form-group">
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon1">Fullname</span> <input id="fullname" name="fullname"
						type="text" class="form-control" placeholder="Fullname"
						aria-label="Username" aria-describedby="basic-addon1"
						value="${sessionScope.user.fullName}">
				</div>
				<span class="form-message form-text text-muted"></span>
			</div>
			<div class="form-group">
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon1">SDT</span> <input id="sdt" name="sdt"
						type="text" class="form-control" placeholder="SDT"
						aria-label="Username" aria-describedby="basic-addon1"
						value="${sessionScope.user.sdt}">
				</div>
				<span class="form-message form-text text-muted"></span>
			</div>
			<div class="form-group">
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon1">Email</span> <input id="email" name="email"
						type="text" class="form-control" placeholder="Email"
						aria-label="Username" aria-describedby="basic-addon1"
						value="${sessionScope.user.email}">
				</div>
				<span class="form-message form-text text-muted"></span>
			</div>
			<div class="form-group">
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon1">Địa chỉ</span> <input id="address" name="address"
						type="text" class="form-control" placeholder="Địa chỉ"
						aria-label="Username" aria-describedby="basic-addon1"
						value="${sessionScope.user.diachi}">
				</div>
				<span class="form-message form-text text-muted"></span>
			</div>
			<span class="form-message form-text text-muted">${message}</span>
			<div class="text-center">
			<button id="btn-Save" type="submit" class="btn btn-warning"><i class="fa-solid fa-floppy-disk"></i> Cập nhật</button>
			<a id="btn-Cancel" href="${pageContext.request.contextPath}/home"class="btn btn-primary"><i class="fa-solid fa-rotate-left"></i> Quay lại</a>
			</div>
		</form>
	</main>
	<%@ include file="layout/client-footer.jsp"%>
</body>
</html>