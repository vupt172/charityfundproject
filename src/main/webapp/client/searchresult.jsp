<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Kết quả tìm kiếm</title>
<meta charset="UTF-8">
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
<style>
.content-header {
	margin-top: 20px;
	font-family: 'Roboto Condensed', sans-serif;
}

.content-header hr {
	border: 1px solid black;
	width: 50%;
	text-align: center;
}

.fund-name, .card-foundation {
	display: -webkit-box;
	height: 3.2rem;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: normal;
	-webkit-line-clamp: 2
}

.card {
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
}

.card:hover {
	box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
}

.card-date {
	width: 100px;
	text-align: right;
}

.card-info1 {
	font-size: 12px;
	font-weight: 500;
}
</style>
</head>
<body>
	<jsp:useBean id="donationDAO" class="Dao.DonationDAO"></jsp:useBean>
	<%@ include file="layout/client-header.jsp"%>
	<main>
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active"><a
					href="${pageContext.request.contextPath}/home">Quỹ Từ Thiện</a></li>
				<li class="breadcrumb-item active" aria-current="page">Tìm kiếm</li>
			</ol>
		</nav>
		<div class="content-header">
			<h3 class="content-title display-5 text-center">Kết quả tìm kiếm
				cho từ khóa : ${param.keyword}</h3>
			<hr class="content-title-underline">
		</div>
		<div>
		<c:set var="retrieveFundList" value="${fundList}" scope="request"></c:set>
				<%@ include file="layout/displayFundList.jsp"%>
		</div>
	</main>
	<jsp:include page="layout/client-footer.jsp"></jsp:include>
</body>
</html>