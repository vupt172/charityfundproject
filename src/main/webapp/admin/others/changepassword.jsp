<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Foundation</title>
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
<script src="admin-header.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/body-content.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/submit-form.css">
<script
	src="${pageContext.request.contextPath}/client/js/changepassword.js"></script>
</head>
<body>

	<%@ include file="/admin/layout/admin-header.jsp"%>
	<div id="content">
		<div id="content-header">
			<h4>Change Password</h4>
		</div>
		<form
			action="${pageContext.request.contextPath}/password?action=changepassword"
			class="create-form" method="post">

			<div class="form-group">
				<label class="form-label">Username</label> <input id="user-username"
					type="text" class="form-control" name="username"
					value="${sessionScope.user.username}" readonly> <span
					class="form-message""></span>
			</div>
			<div class="form-group">
				<label class="form-label">Old Password</label> <input
					id="user-oldpassword" type="password" class="form-control"
					name="oldpassword" value=""> <span class="form-message"></span>
			</div>

			<div class="form-group">
				<label class="form-label">New password</label> <input
					id="user-newpassword" type="password" class="form-control"
					name="newpassword" value=""> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Confirm password</label> <input
					id="user-confirmpassword" type="password" class="form-control"
					name="confirmpassword" value=""> <span class="form-message"></span>
			</div>
			<span class="form-message form-text text-muted">${message}</span>
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
	<jsp:include page="/admin/layout/admin-footer.jsp"></jsp:include>

</body>
</html>