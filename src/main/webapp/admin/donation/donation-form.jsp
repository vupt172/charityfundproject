<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Donation Form</title>
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
<script src="../js/donation-submit.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/body-content.css">
<link rel="stylesheet" href="../css/submit-form.css">
</head>

<body>
	<c:if test="${param.result==false}">
		<script>
			alert("Thêm thất bại!")
		</script>
	</c:if>
	<%@ include file="../layout/admin-header.jsp"%>
	<div id="content">
		<div id="content-header">
			<c:if test="${param.action=='add'}">
				<h4>Create Donnation</h4>
			</c:if>
			<c:if test="${param.action=='edit'}">
				<h4>Edit Donnation</h4>
			</c:if>
		</div>
		<form class="create-form" method="post">
			<div class="form-group">
				<label class="form-label">ID</label> <input id="id" name="id"
					type="text" class="form-control" value="${donation.id}" readonly>
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Donation Amount</label> <input
					id="donation-amount" name="donation-amount" type="text"
					class="form-control" value="${donation.donationAmount}"> <span
					class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Donation Message</label> <input
					id="donation-message" name="donation-message" type="text"
					class="form-control" value="${donation.donationMessage}"> <span
					class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Created Date</label> <input
					id="created-date" name="created-date" type="date"
					class="form-control" value="${donation.createdDate}" readonly>
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">User</label> <select id="username"
					name="username">
					<option selected>Username</option>
					<c:forEach var="user" items="${userList}">
						<c:if test="${user.username==donation.user.username}">
							<option selected>${user.username}</option>
						</c:if>
						<c:if test="${user.username!=donation.user.username}">
							<option>${user.username}</option>
						</c:if>
					</c:forEach>
				</select> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Fund</label> <select id="fundname"
					name="fundname">
					<option selected>Fundname</option>
					<c:forEach var="fund" items="${fundList}">
						<c:if test="${fund.id==donation.fund.id}">
							<option value="${fund.id}" selected>${fund.name}</option>
						</c:if>
						<c:if test="${fund.id!=donation.fund.id}">
							<option value="${fund.id}">${fund.name}</option>
						</c:if>
					</c:forEach>
				</select> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<button id="btn-Save" type="submit" class="btn btn-success mx-1">
					<i class="fa-solid fa-floppy-disk"></i> Save
				</button>
				<button id="btn-Cancel" type="button" class="btn btn-primary mx-1">
					<i class="fa-solid fa-rotate-left"></i> Cancel
				</button>
			</div>
		</form>
	</div>
	</div>
	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>
</body>
</html>