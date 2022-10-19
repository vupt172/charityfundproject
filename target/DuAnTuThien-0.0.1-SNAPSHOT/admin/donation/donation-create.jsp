<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Foundation Create</title>
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
<link rel="stylesheet" href="../../css/body-content.css">
</head>
<style>
.create-form {
	width: 700px;
	background: #fff;
	padding: 12px;
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
}

.form-group {
	display: flex;
}

.form-group .form-control {
	margin-left: 5px;
}

.form-group .form-label {
	min-width: 150px;
}
.form-group select{
 width:200px;
 margin-left:5px;
}
</style>
<body>
	<jsp:include page="../layout/admin-header.jsp"></jsp:include>
	<div id="content">
		<div id="content-header">
			<h4>Add Donnation</h4>
		</div>
		<form action="" class="create-form">
			<div class="form-group">
				<label class="form-label">ID</label> <input type="text"
					class="form-control" disabled readonly>
			</div>
			<div class="form-group">
				<label class="form-label">Username</label> <input type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label class="form-label">Fund ID</label>
				 <input type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label class="form-label">Donation Amount</label>
				 <input type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label class="form-label">Donation Message</label>
				 <input type="text"
					class="form-control">
			</div>
			<div class="form-group">
				<label class="form-label">Created Date</label>
				 <input type="date"
					class="form-control">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success mx-1">
					<i class="fa-solid fa-floppy-disk"></i> Save
				</button>
				<button type="button" class="btn btn-primary mx-1">
					<i class="fa-solid fa-rotate-left"></i> Reset
				</button>
			</div>
		</form>
	</div>
	</div>
	<jsp:include page="../layout/admin-footer.jsp"></jsp:include>

</body>
</html>