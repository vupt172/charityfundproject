<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Form</title>
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
<link rel="stylesheet" href="../css/body-content.css">
<link rel="stylesheet" href="../css/submit-form.css">
<script src="../js/user-submit.js"></script>
</head>
<body>

	<jsp:include page="../layout/admin-header.jsp"></jsp:include>
	<div id="content">
		<div id="content-header">
			<c:if test="${param.action=='add'}">
				<h4>Create User</h4>
			</c:if>
			<c:if test="${param.action=='edit'}">
				<h4>Update User</h4>
			</c:if>
		</div>
		<form action="" class="create-form" method="post">
			<div class="form-group">
				<label class="form-label">ID</label> <input id="user-id" name="id"
					type="text" class="form-control" readonly value="${user.id}">
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Username</label> <input id="user-username"
					name="username" type="text" class="form-control"
					value="${user.username}"> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Role</label> <select id="user-role"
					name="role" class="form-select">
					<c:choose>
						<c:when test="${user.role==1}">
							<option selected value="1">Admin</option>
							<option value="2" disabled>User</option>
						</c:when>
						<c:when test="${user.role==2 }">
							<option selected value="2">User</option>
							<option value="1">Admin</option>
						</c:when>
						<c:otherwise>
							<option value="1">Admin</option>
							<option value="2">User</option>
						</c:otherwise>
					</c:choose>

				</select> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Fullnane</label> <input id="user-fullname"
					name="fullname" type="text" class="form-control"
					value="${user.fullName}"> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">SDT</label> <input id="user-sdt"
					name="sdt" type="text" class="form-control" value="${user.sdt}">
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Email</label> <input id="user-email"
					name="email" type="text" class="form-control" value="${user.email}">
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Địa Chỉ</label> <input id="user-address"
					name="address" type="text" class="form-control"
					value="${user.diachi}"> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Status</label> <select id="user-status"
					name="status" class="form-select">
					<c:forEach var="status" items="${statusList}">
						<c:choose>
							<c:when test="${status==user.status}">
								<option selected>${status}</option>
							</c:when>
							<c:otherwise>
								<option>${status}</option>
							</c:otherwise>
						</c:choose>
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
				<c:if test="${param.action=='edit'}">
					<button id="btn-ResetPassword" type="submit"
						class="btn btn-warning mx-1">
						<i class="fa-solid fa-unlock"></i> </i> Reset Password
					</button>
				</c:if>

			</div>
		</form>
	</div>
	</div>
	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>

</body>
</html>