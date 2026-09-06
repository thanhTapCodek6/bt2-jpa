<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/admin/products">NHỨT
			THẠNH STORE — Quản trị</a>

		<button class="navbar-toggler" type="button"
			data-bs-toggle="collapse" data-bs-target="#adminNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse justify-content-end"
			id="adminNavbar">
			<ul class="navbar-nav align-items-lg-center">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/home">Về
						trang chính</a>
				</li>
				<c:if test="${sessionScope.account != null}">
					<li class="nav-item">
						<span class="nav-link username-tag">${sessionScope.account.username} (admin)</span>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng
							xuất</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>