<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/client/js/client-header.js"></script>
<style>
header, nav {
	font-family: 'Roboto Condensed', sans-serif;
}

@media ( max-width : 991.98px) {
	#userDropdownMenu {
		width: 100%;
	}
}
</style>
<!-- check session -->



<!--  -->
<header
	class="d-md-flex flex-md-row justify-content-between align-items-center mx-md-5 ">
	<img src="${pageContext.request.contextPath}/images/clientlogo.png"
		alt="" style="width: 300px">
	<c:if test="${sessionScope.user==null }">
		<div>
			<a href="${pageContext.request.contextPath}/login">Đăng nhập |</a> <a
				href="${pageContext.request.contextPath}/register">Đăng ký</a>
		</div>
	</c:if>
	<c:if test="${sessionScope.user.role==1}">
		<c:redirect url="/admin/home"></c:redirect>
	</c:if>
	<c:if test="${sessionScope.user.role==2}">
		<div class="dropdown">
			<button class="btn dropdown-toggle" type="button"
				id="userDropdownMenu" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
				<img src="${pageContext.request.contextPath}/images/icon1.png"
					style="width: 50px"> Welcome <b>${sessionScope.user.username}</b>
			</button>
			<div class="dropdown-menu" aria-labelledby="userDropdownMenu"
				style="width: 100%">
				<a class="dropdown-item" style="width: 100%;"
					href="${pageContext.request.contextPath}/user">Thông tin cá
					nhân</a>
					<a class="dropdown-item" style="width: 100%;"
					href="${pageContext.request.contextPath}/donationhistory">Lịch sử quyên góp</a>
					 <a class="dropdown-item" style="width: 100%;"
					href="${pageContext.request.contextPath}/password?action=changepassword">Thay
					đổi mật khẩu</a> <a class="dropdown-item"
					href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
			</div>
		</div>
	</c:if>
</header>
<nav id="client-header-nav"
	class="navbar navbar-expand-lg navbar-dark bg-info">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item "><a class="nav-link"
				href="${pageContext.request.contextPath}/home"><i
					class="fa-solid fa-house"></i> Trang chủ </a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="categoryDropdownList"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
				href="#"><i class="fa-solid fa-layer-group"></i></i> Danh mục</a>
				<div class="dropdown-menu" aria-labelledby="#categoryDrodownList"
					style="width: auto">
					<c:forEach var="category" items="${categoryList}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/category?id=${category.id}">${category.name}</a>
					</c:forEach>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/foundation"><i
					class="fa-solid fa-people-group"></i> Nhà tổ chức</a></li>
			<li class="nav-item"><a class="nav-link " href="#"><i
					class="fa-sharp fa-solid fa-address-card"></i> Liên hệ</a></li>
			<li class="nav-item"><a class="nav-link " href="#"><i
					class="fa-solid fa-circle-info"></i> Về chúng tôi</a></li>
		</ul>
		<div class="form-inline my-2 my-lg-0 ">
			<input id="keyword" type="text" class="form-control"
				placeholder="Tìm kiếm quỹ quyên góp"
				aria-label="Recipient's username" aria-describedby="basic-addon2"
				value="${param.keyword}">
			<div class="input-group-append">
				<button id="btn-Search" class="input-group-text" id="basic-addon2">
					<i class="fa-solid fa-magnifying-glass"></i>Search
				</button>
			</div>
		</div>

	</div>
</nav>