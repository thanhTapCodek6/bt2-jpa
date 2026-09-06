<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="main-nav">
	<a class="brand" href="${pageContext.request.contextPath}/admin/products">NHỨT
		THẠNH STORE — Quản trị</a>
	<div class="nav-links">
		<a href="${pageContext.request.contextPath}/home">Về trang chính</a>
		<c:if test="${sessionScope.account != null}">
			<span class="username-tag">${sessionScope.account.username} (admin)</span>
			<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
		</c:if>
	</div>
</div>