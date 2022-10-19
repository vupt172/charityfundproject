<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</head>
<style>
main {
	padding: 12px;
}

.card-group {
	font-family: 'Roboto Condensed', sans-serif;
}
.card-header{
	display: -webkit-box;
	max-height: 3.2rem;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: normal;
	-webkit-line-clamp: 2
}
.card-description{
	display: -webkit-box;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: normal;
	-webkit-line-clamp: 8
}
</style>
<body>
	<%@ include file="layout/client-header.jsp"%>
	<main>
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">Danh
					sách nhà tổ chức gây quỹ</li>
			</ol>
		</nav>
		<c:forEach var="foundation" items="${foundationList}"
			varStatus="status">
			<c:if test="${status.index%3==0}">
				<div class="card-group m-3">
			</c:if>
			<div class="card" style="max-width: 380px">
				<div class="card-header ">
						<a href="#" class="">${foundation.name}</a>
				</div>
				<div class="card-body">
					<p class="card-text card-description">${foundation.description}</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">Email:${foundation.email}</small>
				</div>
			</div>
			<c:if test="${(status.index+1)%3==0||status.last}">
				</div>
			</c:if>
		</c:forEach>
 <jsp:include page="layout/client-footer.jsp"></jsp:include>
	</main>
</body>
</html>