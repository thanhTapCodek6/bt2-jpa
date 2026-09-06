<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="main-nav">
	<a class="brand" href="${pageContext.request.contextPath}/home">NHỨT
		THẠNH STORE</a>
	<div class="nav-links">
		<a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
		<c:if test="${sessionScope.account != null && sessionScope.account.role == 'ADMIN'}">
			<a href="${pageContext.request.contextPath}/admin/products">Quản lý sản phẩm</a>
		</c:if>
	</div>
</div>