<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Account</title>
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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<script src="client/js/signup.js"></script>
<style>
body {
	font-size: 13px;
	background: #f1f1f1;
	font-family: Arial;
}

.register-form {
	margin: 40px auto; 
	display: flex;
	background: #fff;
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
	max-width: 800px;
}

.content {
	padding: 20px;
	flex: auto;
}

.sidebar {
	width: 400px;
	background: #066578;
	color: white;
	font-weigtht: bold;
	font-family: 'Roboto Condensed', sans-serif;
}

.sidebar-logo {
	font-size: .9rem;
	font-style: italic;
}

.sidebar-title {
	margin-top: 50px;
	text-align: center;
}

.sidebar-title .subtitle {
	font-size: 1.2rem;
}

.sidebar-title .maintitle {
	font-size: 2rem;
}

hr {
	border: 1px solid white;
	width: 50%;
	text-align: center;
}

.content h2 {
	text-align: center;
	font-weight: bold;
}


.redirect-login {
	font-size: 1rem;
}

#btn-Signup {
	width: 80%;
	margin: auto;
	display: block;
	font-size: 1.2rem;
	margin-top: 10px;
}
@media only screen and (max-width: 768px){
.sidebar{
display:none;
}
</style>
</head>
<body>
	<div class="register-form">
		<div class="sidebar">
			<h5 class="p-1 sidebar-logo">
				<i class="fa-solid fa-house"></i> Duantuthien.com
			</h5>
			<div class="sidebar-title">

				<h3 class="maintitle">WELCOME TO MY WEBSITE</h3>
				<span class="subtitle">Enter your personal details to
					continue</span>
				<hr>
				<q>Mỗi người một hành động nhỏ, chúng ta sẽ có điều kỳ diệu</q>
			</div>
		</div>
		<div class="content">

			<h2 class="mb-5">ACCOUNT REGISTER</h2>
			<form method="post" action="${pageContext.request.contextPath}/register">	
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-user"></i></span> <input id="username"
							name="username" type="text" class="form-control"
							placeholder="Username" aria-label="Username"
							aria-describedby="basic-addon1">
					</div>
					<span class="form-message form-text text-muted"></span>
				</div>
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-file-signature"></i></span> <input id="fullname"
							name="fullname" type="text" class="form-control"
							placeholder="Fullname" aria-label="Fullname"
							aria-describedby="basic-addon1">
					</div>
					<span class="form-message form-text text-muted"></span>
				</div>
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-envelope"></i></span> <input id="email" name="email"
							type="text" class="form-control" placeholder="Email"
							aria-label="Email" aria-describedby="basic-addon1">
					</div>
					<span class="form-message form-text text-muted"></span>
				</div>
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-phone"></i></span> <input id="sdt" name="sdt"
							type="text" class="form-control" placeholder="SDT"
							aria-label="SDT" aria-describedby="basic-addon1">
					</div>
					<span class="form-message form-text text-muted"></span>
				</div>
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-location-dot"></i></span> <input id="address"
							name="address" type="text" class="form-control"
							placeholder="Địa chỉ" aria-label="Địa chỉ"
							aria-describedby="basic-addon1">
					</div>
					<span class="form-message form-text text-muted"></span>
				</div>
                <span class="form-message form-text text-red">${message}</span>
				<button id="btn-Signup" class="btn btn-info" type="submit">Sign up</button>
				<div class="text-center redirect-login">
					Already a member ? <a
						href="${pageContext.request.contextPath}/login">Login here</a>
				</div>
			</form>
		</div>
		


	</div>
</body>
</html>