<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin quỹ từ thiện</title>
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
<script src="${pageContext.request.contextPath}/client/js/fundDetail.js"></script>
<style>
main {
	padding: 12px;
}

.fund-name {
	font-family: 'Roboto Condensed', sans-serif;
}

.fund-image {
	width: inherit;
}

.fund-summary, .fund-users {
	box-shadow: 0 2px 5px 0 rgba(51, 62, 73, 0.1);
}

.fund-content {
	text-align: justify;
	text-justify: inter-word;
}

.card-foundation {
	text-decoration: none;
	font-weight: 550;
}
</style>
</head>
<body>
	<jsp:useBean id="donationDAO" class="Dao.DonationDAO"></jsp:useBean>
	<%@ include file="layout/client-header.jsp"%>
	<main>
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active"><a
					href="${pageContext.request.contextPath}/home">${fund.category.name}</a></li>
				<li class="breadcrumb-item active" aria-current="page">Quỹ
					quyên góp</li>
			</ol>
		</nav>
		<div class="content row">
			<div class="fund-story col-md-8 p-3">
				<h3 class="fund-name">${fund.name}</h3>
				<p class="card-text text-muted">${fund.description}</p>
				<p class="card-text text-muted text-right">${fund.createdDate}</p>
				<img class="fund-image" src="${fund.image_url}">

				<div class="fund-content border border-warning rounded m-3 p-3">${fund.content}</div>
			</div>
			<div class="fund-info col-md-4 p-3">
				<div class="fund-summary card">
					<div class="card-header">
						<h5>Thông tin quyên góp</h5>
					</div>
					<div class="card-body">
						<div>
							Tổng số tiền đạt được :<b>${donationDAO.getTotalDonations(fund.id)}
								đ</b>/${fund.expectedResult} đ
						</div>
						<div class="progress my-2" style="height: 5px">
							<div class="progress-bar" role="progressbar" aria-valuenow="0"
								aria-valuemin="0" aria-valuemax="100"
								style="width:${100.0*donationDAO.getTotalDonations(fund.id)/fund.expectedResult}%"></div>
						</div>
						<div class=" d-flex justify-content-between my-2">
							<span class="py-2">Lượt quyên góp : <br> <b>${donationDAO.getTotalDonationTimes(fund.id)}</b></span>
							<span class="py-2">Ngày kết thúc:<br> <b>${fund.endDate}</b></span>
						</div>
						<div>
							<c:if test="${fund.status=='Opening'}">
								<c:if test="${sessionScope.user==null}">
									<a id="btn-LoginToDonate" type="button"
										class="btn btn-danger w-75 mx-auto d-block"
										href="${pageContext.request.contextPath}/login">Đăng nhập
										để quyên góp</a>
								</c:if>
								<c:if test="${sessionScope.user!=null }">
									<button id="btn-Donate" type="button"
										class="btn btn-danger w-75 mx-auto d-block"
										data-toggle="modal" data-target="#modalDonate">Quyên
										góp</button>

									<!-- Modal -->
									<div class="modal fade" id="modalDonate" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Thông
														tin quyên góp</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<form id="frm-SendDonate" method="post"
													action="${pageContext.request.contextPath}/pay">
													<div class="modal-body">

														<div class="input-group mb-3">
															<div class="input-group-prepend">
																<span class="input-group-text" id="basic-addon3">Quỹ
																	quyên góp</span>
															</div>
															<input id="fund-id" type="hidden" name="fund-id"
																value="${fund.id}"> <input type="text"
																class="form-control" id="fund-name" name="fund-name"
																aria-describedby="basic-addon3" value="${fund.name}"
																readonly>
														</div>
														<div class="form-group">
															<div class="input-group mb-3">
																<div class="input-group-prepend">
																	<span class="input-group-text" id="basic-addon3">Tiền
																		quyên góp</span>
																</div>
																<input type="text" class="form-control"
																	id="donation-amount" name="donation-amount"
																	aria-describedby="basic-addon3">
																<div class="input-group-append">
																	<span class="input-group-text">.vnd</span>
																</div>
															</div>
															<span class="form-message form-text text-muted"></span>
														</div>
														<div class="input-group mb-3">
															<div class="input-group-prepend">
																<span class="input-group-text" id="basic-addon3">Lời
																	nhắn</span>
															</div>
															<input type="text" class="form-control"
																id="donation-message" name="donation-message"
																aria-describedby="basic-addon3" list="sample-message">
																<datalist id="sample-message">
																<option>Chúc chương trình nhận được nhiều quyên góp</option>
																<option>Mọi người ơi hãy mau giúp đỡ hoàn cảnh đáng thương này</option>
																<option>Mình có một chút tấm lòng nhỏ mong chương trình nhận</option>
																<option>Mỗi người chung tay quyên góp vì một tương lai tốt đẹp hơn</option>
																<option>Chúc chương trình này sớm được hoàn thành</option>
																<option>Giữ gìn truyền nét đẹp truyền thống Lá lành đùm lá rách</option>
																</datalist>
																
														</div>

													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">Close</button>
														<button id="btn-SendDonate" type="submit"
															class="btn btn-danger">Gửi quyên góp</button>
													</div>
												</form>
												<script>
													$("#frm-SendDonate")
															.submit(
																	function() {
																		var postData = $(
																				"#frm-SendDonate")
																				.serialize();
																		var submitUrl = $(
																				"#frm-SendDonate")
																				.attr(
																						"action");
																		$
																				.ajax({
																					type : "POST",
																					url : submitUrl,
																					data : postData,
																					dataType : 'JSON',
																					success : function(
																							x) {
																						if (x.code === '00') {
																					
																							if (window.vnpay) {
																								vnpay
																										.open({
																											width : 768,
																											height : 600,
																											url : x.data
																										});
																							} else {
																								location.href = x.data;
																							}
																							return false;
																						} else {
																							alert(x.Message);
																						}
																					}
																				});
																		return false;
																	});
												</script>
											</div>
										</div>
									</div>
								</c:if>
							</c:if>
							<c:if test="${fund.status=='Finish'}">
								<button type="button" class="btn btn-info w-75 mx-auto d-block">Hoàn
									thành</button>
							</c:if>
						</div>
					</div>
					<div class="card-footer">
						<a class="card-foundation text-primary" href="#">${fund.foundation.name}</a>
					</div>
				</div>
				<div class="fund-users card mt-5">
					<div class="card-header">
						<h5>Top 20 quyên góp mới nhất</h5>
					</div>
					<ul class="list-group list-group-flush">
						<c:forEach var="donation" items="${donationList}">
							<li class="list-group-item ">
							<div class="d-flex justify-content-between" style="font-weight:bold">
							${donation.user.username}
							<span>${donation.donationAmount} đ</span>
							</div>
							<div class="card-text">
							<q>${donation.donationMessage}</q>
							</div>
							<div class="text-muted" style="text-align:right;font-size:11px">
							${donation.createdDate}
							</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="layout/client-footer.jsp"></jsp:include>
</body>
</html>