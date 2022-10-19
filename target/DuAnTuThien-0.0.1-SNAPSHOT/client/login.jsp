<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
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
<link
	href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap"
	rel="stylesheet">
<script src="client/js/login.js"></script>
<style>
body {
	font-size: 13px;
	background: #f1f1f1;
}

.login-form {
	margin: auto;
	margin-top: 50px;
	width: 800px;
	display: flex;
	height: 400px;
	background: #fff;
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
	font-family: Arial;
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
	font-size: 1.3rem;
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

#btn-Login {
	width: 80%;
	margin: auto;
	display: block;
	font-size: 1.2rem;
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="login-form">
		<div class="sidebar">
			<h5 class="p-1 sidebar-logo">
				<i class="fa-solid fa-house"></i> Duantuthien.com
			</h5>
			<div class="sidebar-title">
				<span class="subtitle">Nice to see you again</span>
				<h3 class="maintitle">WELCOME BACK</h3>
				<hr>
				<q>Triệu người chung tay quyên góp vì một việt nam tốt đẹp hơn</q>
			</div>
		</div>
		<div class="content">
			<h2 class="mb-5">ACCOUNT LOGIN</h2>
			<form id="login-form" method="post"
				action="${pageContext.request.contextPath}/login">
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-user"></i></span> <input id="username" type="text"
							class="form-control" placeholder="Username" aria-label="Username"
							aria-describedby="basic-addon1" name="username"
							value="${cookie['ckUser'].getValue()}">
					</div>
					<span class="form-text text-muted form-message"></span>
				</div>
				<div class="form-group">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1"><i
							class="fa-solid fa-lock"></i></span> <input id="password"
							type="password" class="form-control" placeholder="Password"
							aria-label="Password" aria-describedby="basic-addon1"
							name="password" value="${cookie['ckPassword'].getValue()}">
					</div>
					<span class="form-text text-muted form-message"></span>
				</div>

				<div class="d-flex justify-content-between">
					<div>
						<input id="rememberme" type="checkbox" name="rememberme">
						<label for="rememberme">Remember me</label>
					</div>
					<a data-toggle="modal" href="#modalForgotPassword" role="button">Forgot
						Password</a>
					<!-- Modal -->
					<div class="modal fade" id="modalForgotPassword" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLongTitle">Reset
										Password</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
								<p>Mật khẩu mới sẽ được gửi qua email nhập vào</p>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"><i
												class="fa-solid fa-envelope"></i></span>
										</div>
										<input id="receivedPasswordEmail"name="email" type="text" class="form-control" placeholder="Email"
											aria-label="email" aria-describedby="basic-addon1">
									</div>
									<span class="form-message form-text text-muted"></span>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button id="btn-SendPasswordEmail" type="button" class="btn btn-primary">Send</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="collapse" id="collapseForgotPassword">ABC</div>
				<span class="response-message form-message">${requestScope.message}</span>
				<button id="btn-Login" type="submit" class="btn btn-info">Login</button>
			</form>

		</div>

	</div>
</body>
</html>