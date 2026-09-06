<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home">NHỨT
			THẠNH STORE</a>

		<button class="navbar-toggler" type="button"
			data-bs-toggle="collapse" data-bs-target="#mainNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="mainNavbar">
			<ul class="navbar-nav me-auto">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/product">Sản
						phẩm</a>
				</li>
				<c:if
					test="${sessionScope.account != null && sessionScope.account.role == 'ADMIN'}">
					<li class="nav-item">
						<a class="nav-link"
							href="${pageContext.request.contextPath}/admin/products">Quản
							lý sản phẩm</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>