<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<style>
body {
	background: #f1f1f1;
}

.card {
	width: 80%;
	min-width: 500px;
	margin: 40px auto;
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
}
</style>
<body>
	<div class="card">
		<h5 class="card-header text-white bg-info">Duantuthien.com thông báo</h5>
		<div class="card-body">
			<h5 class="card-title">Kích hoạt tài khoản thành công</h5>
			<p class="card-text">Xin chào <b>${username}</b>,bạn đã kích hoạt tài khoản thành công			
		</div>
		 <div class="card-footer"><a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Quay về trang chủ</a></div>
	</div>
</body>
</html>