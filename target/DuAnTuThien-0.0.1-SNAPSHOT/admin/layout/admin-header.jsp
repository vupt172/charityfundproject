<%@page import="Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="../js/admin-header.js"></script>
<style>
.dropdown-menu {
	width: 100%;
}

@media ( max-width : 991.98px) {
	#userDropdownMenu {
		width: 100%;
	}
}
</style>
<!-- check session -->
<c:if test="${sessionScope.user==null }">
<c:redirect url="/login"></c:redirect>
</c:if>
<c:if test="${sessionScope.user.role==2}"><c:redirect url="/home"></c:redirect></c:if>
<!--  -->
<header
	class="d-md-flex flex-md-row justify-content-between align-items-center mx-md-5 ">
	<img src="../../images/logo.png" alt="" style="width: 300px">
	<h1 style="color: #42727c;" class="text-md-center">MANAGEMENT
		SYSTEM</h1>
	<div class="dropdown">
		<button class="btn dropdown-toggle" type="button"
			id="userDropdownMenu" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">
			<img src="../../images/icon1.png" style="width: 50px"> Welcome
			<b>${sessionScope.user.username}</b>
		</button>
		<div class="dropdown-menu" aria-labelledby="userDropdownMenu">
			<a class="dropdown-item" style="width: 100%;" href="#">Change
				Password</a> <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>
	</div>

</header>
<nav id="admin-header-nav"
	class="navbar navbar-expand-lg navbar-dark bg-info">
	<a class="navbar-brand" href="#">MENU</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto ">
			<li class="nav-item "><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/home/index.jsp"><i
					class="fa-solid fa-house"></i> Home </a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/category/"><i
					class="fa-solid fa-layer-group"></i></i> Danh mục</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/foundation/"><i
					class="fa-solid fa-people-group"></i> Nhà tổ chức</a></li>
			<li class="nav-item"><a class="nav-link "
				href="${pageContext.request.contextPath}/admin/fund/"><i
					class="fa-solid fa-sack-dollar"></i> Quỹ từ thiện</a></li>
			<li class="nav-item"><a class="nav-link "
				href="${pageContext.request.contextPath}/admin/donation/"><i
					class="fa-solid fa-hand-holding-heart"></i> Lượt Quyên Góp</a></li>
			<li class="nav-item"><a class="nav-link "
				href="${pageContext.request.contextPath}/admin/user/"> <i
					class="fa-solid fa-user"></i> Tài khoản
			</a></li>
		</ul>

	</div>
</nav>