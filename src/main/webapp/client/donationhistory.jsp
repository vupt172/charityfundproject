<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử quyên góp</title>
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
	<%@ include file="layout/client-header.jsp"%>
	<main>
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/user">User</a></li>
				<li class="breadcrumb-item active" aria-current="page">Lịch sử
					quyên góp</li>
			</ol>
		</nav>
		<div id="content">
			<table id="tb-donationhistory"
				class="table table-bordered table-sm table-hover">
				<thead class="table-warning">
					<th>Tên quỹ quyên góp</th>
					<th>Tiền quyên góp</th>
					<th>Lời nhắn</th>
					<th>Thời gian</th>
				</thead>
				<tbody>
					<c:forEach var="donation" items="${donationList}">
						<tr>
							<td class="category-name">${donation.fund.name}</td>
							<td class="category-name">${donation.donationAmount}</td>
							<td class="category-description">${donation.donationMessage}</td>
							<td class="category-description">${donation.createdDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<c:forEach var="page" begin="1" end="${totalPages}">
						<c:choose>
							<c:when test="${page==param.page}">
								<li class="page-item active">
							</c:when>
							<c:otherwise>
								<li class="page-item">
							</c:otherwise>
						</c:choose>
						<a class="page-link"
							href="${pageContext.request.contextPath}/donationhistory?page=${page}">${page}</a>
						</li>
					</c:forEach>
				</ul>
			</nav>
		</div>
	</main>
	<%@ include file="layout/client-footer.jsp"%>
</body>
</html>