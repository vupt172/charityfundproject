<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="admin-header.js"></script>
<link rel="stylesheet" href="../css/body-content.css">
<link rel="stylesheet" href="../css/submit-form.css">
<script src="../js/foundation-submit.js"></script>
</head>
<body>
 <c:if test="${param.action=='add' && param.result==false}"><script>alert("Thêm thất bại !");</script></c:if>
	<jsp:include page="../layout/admin-header.jsp"></jsp:include>
	<div id="content">
		<div id="content-header">
			<h4>Create Foundation</h4>
		</div>
		<form action="" class="create-form" method="post">
			<div class="form-group">
				<label class="form-label">ID</label> <input id="foundation-id" type="text"
					class="form-control"  readonly name="id" value="${foundation.id}"> <span
					class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Name</label> <input id="foundation-name" type="text"
					class="form-control" name="name" value="${foundation.name}"> <span class="form-message" "></span>
			</div>
			<div class="form-group">
				<label class="form-label">Description</label>
				<textarea id="foundation-description" rows="3" class="form-control" name="description" >${foundation.description}</textarea>
				<span class="form-message" name="description"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Email</label> <input id="foundation-email" type="text"
					class="form-control" name="email" value="${foundation.email}"> <span class="form-message"></span>
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