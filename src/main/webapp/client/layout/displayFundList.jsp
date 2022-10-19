<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/displayFundCard.css">
<c:forEach var="fund" items="${retrieveFundList}" varStatus="status">
	<c:if test="${status.index%3==0}">
		<div class="row m-3">
	</c:if>
	<div class="col-md-4">
		<div class="card" style="">
			<img src="${fund.image_url}" class="card-img-top fund-image"
				alt="..." style="width: inherit;">
			<div class="card-body">
				<h5 class="card-title fund-name">${fund.name}</h5>
				<div class="card-info1 card-text d-flex justify-content-between">
					<span class="card-foundation">${fund.foundation.name}</span> <span
						class="card-date">${fund.endDate}</span>
				</div>
				<div>
					<b>${donationDAO.getTotalDonations(fund.id)} đ</b>/${fund.expectedResult}
					đ
				</div>
				<div class="progress" style="height: 5px">
					<div class="progress-bar" role="progressbar" aria-valuenow="0"
						aria-valuemin="0" aria-valuemax="100"
						style="width:${100.0*donationDAO.getTotalDonations(fund.id)/fund.expectedResult}%"></div>
				</div>
				<div class=" d-flex justify-content-between mt-2">
					<span class="py-2">Lượt quyên góp : <b>${donationDAO.getTotalDonationTimes(fund.id)}</b></span>
					<c:if test="${fund.status=='Opening'}">
						<a role="button" class="btn btn-outline-danger"
							href="${pageContext.request.contextPath}/fund?id=${fund.id}">Xem chi tiết</a>
					</c:if>
					<c:if test="${fund.status=='Finish'}">
						<a role="button" class="btn btn-outline-info"
							href="${pageContext.request.contextPath}/fund?id=${fund.id}">Xem chi tiết</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${(status.index+1)%3==0 || status.last}">
		</div>
	</c:if>
</c:forEach>