<%@page import="Dao.DonationDAO"%>
<%@page import="Dao.FoundationDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Website quyên góp từ thiện Việt Nam</title>
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
</head>
<body>
<c:if test="${param.status=='afteradddonate' && param.result==true}"><script>alert("Quyên góp thành công")</script></c:if>
	<jsp:useBean id="donationDAO" class="Dao.DonationDAO" scope="page"></jsp:useBean>
	<%@ include file="layout/client-header.jsp"%>
	<main>
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">Danh
					sách quỹ từ thiện</li>
			</ol>
		</nav>
		<div class="content-header">
			<h3 class="content-title display-5 text-center">LƯỢT QUYÊN GÓP
				GẦN ĐÂY</h3>
			<hr class="content-title-underline">
		</div>
		<div id="carouselExampleIndicators" class="carousel slide m-md-5"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach var="donation" items="${donationList}" varStatus="status">
					<c:if test="${status.first}">
						<li data-target="#carouselExampleIndicators"
							data-slide-to="${status.index}" class="active"></li>
					</c:if>
					<c:if test="${!status.first}">
						<li data-target="#carouselExampleIndicators"
							data-slide-to="${status.index}"></li>
					</c:if>
				</c:forEach>
			</ol>
			<div class="carousel-inner">
				<c:forEach var="donation" items="${donationList}" varStatus="status">
					<c:if test="${status.first}">
						<div class="carousel-item active">
					</c:if>
					<c:if test="${!status.first}">
						<div class="carousel-item ">
					</c:if>
					<div></div>
					<img class="d-block w-100" src="${donation.fund.image_url}"
						alt="First slide">
					<div class="carousel-caption d-none  d-flex flex-column"
						style="background: rgba(255, 165, 0, 0.8);">
						<h5>${donation.fund.name}</h5>
						<div>
							<span style="font-weight: bold;">${donation.user.username}</span>
							đã quyên góp ${donation.donationAmount} vnđ
						</div>
						<q>${donation.donationMessage}</q> <span style="font-size: 11px">${donation.createdDate}</span>
					</div>
			</div>
			</c:forEach>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
		</div>

		<div>
			<div class="content-header">
				<h3 class="content-title display-5 text-center">CÁC HOẠT ĐỘNG
					ĐANG DIỄN RA</h3>
				<hr class="content-title-underline">
			</div>
			<c:set var="retrieveFundList" value="${openingFundList}"
				scope="request"></c:set>
			<%@ include file="layout/displayFundList.jsp"%>
			<div>
				<a href="${pageContext.request.contextPath}/activity?status=Opening"
					class="btn btn-primary mx-auto d-block w-25">Xem thêm</a>

			</div>

		</div>
		<div>
			<div class="content-header">
				<h3 class="content-title display-5 text-center">CÁC HOẠT ĐỘNG
					ĐÃ HOÀN THÀNH</h3>
				<hr class="content-title-underline">
			</div>
			<c:set var="retrieveFundList" value="${finishFundList}"
				scope="request"></c:set>
			<%@ include file="layout/displayFundList.jsp"%>
			<a href="${pageContext.request.contextPath}/activity?status=Finish"
				class="btn btn-primary mx-auto d-block w-25">Xem thêm</a>
		</div>
	</main>
	<jsp:include page="layout/client-footer.jsp"></jsp:include>
</body>
</html>