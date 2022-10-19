<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Fund Create</title>
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
<script src="https://cdn.ckeditor.com/4.19.1/full/ckeditor.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/body-content.css">
<link rel="stylesheet" href="../css/submit-form.css">
<script src="../js/fund-submit.js"></script>

</head>

<body>
	<c:if test="${param.result==false}">
		<script>
			alert("Thêm thất bại!")
		</script>
	</c:if>
<%@ include file="../layout/admin-header.jsp" %> 
	<div id="content">
		<div id="content-header">
			<h4>Create Fund</h4>
		</div>
		<form action="" class="create-form " method="POST">

			<div class="form-group">
				<label class="form-label">ID</label> <input id="fund-id" type="text"
					class="form-control" readonly name="id" value="${fund.id}">
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Name</label> <input id="fund-name"
					type="text" class="form-control" name="name" value="${fund.name}">
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Description</label>
				<textarea id="fund-description" rows="3" class="form-control"
					name="description">${fund.description}</textarea>
				<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Content</label>
				<textarea rows="5" id="fund-content" 
					name="content" class="ckeditor form-control">${fund.content}</textarea>
					<span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Image URL</label> <input
					id="fund-image-url" type="text" class="form-control"
					name="image_url" value="${fund.image_url}"> <span
					class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Expected Result</label> <input
					id="fund-expected-result" type="text" class="form-control"
					name="expected-result" value="${fund.expectedResult}"> <span
					class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Status</label> <select id="fund-status"
					class="form-select" name="status">
					<c:forEach var="status" items="${statusList}">
						<c:choose>
							<c:when test="${status==fund.status}">
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
				<label class="form-label">Created Date</label> <input
					id="fund-created-date" type="date" class="form-control"
					name="created_date" readonly value="${fund.createdDate}"> <span
					class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">End Date</label> <input id="fund-end-date"
					type="date" class="form-control" name="end_date"
					value="${fund.endDate}"> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Category</label> <select
					id="fund-category" class="form-select" name="category-name">
					<option>Category</option>
					<c:forEach var="category" items="${categoryList}">
						<c:choose>
							<c:when test="${category.name==fund.category.name}">
								<option selected>${category.name}</option>
							</c:when>
							<c:otherwise>
								<option>${category.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> <span class="form-message"></span>
			</div>
			<div class="form-group">
				<label class="form-label">Foundation</label> <select
					id="fund-foundation" class="form-select" name="foundation-name">
					<option selected>Foundation</option>
					<c:forEach var="foundation" items="${foundationList}">
						<c:choose>
							<c:when test="${foundation.name==fund.foundation.name}">
								<option selected>${foundation.name}</option>
							</c:when>
							<c:otherwise>
								<option>${foundation.name}</option>
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
			</div>



		</form>
	</div>
	</div>
	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>

</body>
</html>