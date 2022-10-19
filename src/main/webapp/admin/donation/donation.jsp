<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Donation</title>

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
<script src="../js/donation.js"></script>
<link rel="stylesheet" href="../css/body-content.css">

</head>
<style>
</style>
<body>
	<jsp:include page="../layout/response-param.jsp"></jsp:include>
	<%@ include file="../layout/admin-header.jsp"%>
	<div id="content">
		<div id="content-header">
			<h4>Quản Lý Luợt Quyên Góp</h4>
			<div id="table-filter" class="d-flex py-2">
				<input id="filter-id" name="id" type="text" placeholder="ID"
					class="form-control" value="${param.id}"> <input
					id="filter-username" name="username" type="text"
					placeholder="Username" class="form-control"
					value="${param.username}"> <input id="filter-fundname"
					name="fundname" type="text" placeholder="FundName"
					class="form-control" value="${param.fundname}">
				<button id="btn-Search" type="button"
					class="btn btn-success btn-sm mx-1">
					<i class="fa-solid fa-magnifying-glass"></i> Tìm Kiếm
				</button>

			</div>
		</div>
		<table id="tb-donation"
			class="table table-bordered table-sm table-hover">
			<thead class="table-primary">

				<th>ID</th>
				<th>Donation Amount</th>
				<th>Donation Message</th>
				<th>Created Date</th>
				<th>Username</th>
				<th>Fundname</th>
			</thead>
			<tbody>
				<c:forEach var="donation" items="${donationList}">
					<tr>

						<td class="donation-id">${donation.id}</td>
						<td class="donation-amount"><i
							class="fa-solid fa-circle-dollar-to-slot"></i>
							${donation.donationAmount}</td>
						<td class="donation-message">${donation.donationMessage}</td>
						<td class="donation-created-date">${donation.createdDate}</td>
						<td class="donation-username">${donation.user.username}</td>
						<td class="donation-fundname">${donation.fund.name}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-center">
				<c:forEach var="i" begin="1" end="${totalPages}">
					<c:choose>
						<c:when test="${i==param.page}">
							<c:choose>
								<c:when
									test="${param.id!=null||param.name!=null||param.email!=null}">
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/donation/?page=${i}&id=${param.id}&username=${param.username}&fundname=${param.fundname}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item active"><a class="page-link "
										href="${pageContext.request.contextPath}/admin/donation/?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when
							test="${param.id!=null||param.name!=null||param.email!=null}">
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/donation/?page=${i}&id=${param.id}&username=${param.username}&fundname=${param.fundname}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link "
								href="${pageContext.request.contextPath}/admin/donation/?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</div>
	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>
</body>
</html>