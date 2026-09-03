<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

	<jsp:include page="/views/common/galaxy-bg.jsp" />

	<div class="top-nav">
		<a class="brand" href="${pageContext.request.contextPath}/">NHỨT
			THẠNH STORE</a>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
			<c:choose>
				<c:when test="${sessionScope.account != null}">
					<c:if test="${sessionScope.account.role == 'ADMIN'}">
						<a href="${pageContext.request.contextPath}/admin/products">Quản
							lý sản phẩm</a>
					</c:if>
					<span class="username-tag" style="color: var(--text-dim);">
						${sessionScope.account.username}${sessionScope.account.role == 'ADMIN' ? ' (admin)' : ''}
					</span>
					<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
					<a href="${pageContext.request.contextPath}/register">Đăng ký</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="page-wrap">

		<h1>Chào mừng quý khách đến cửa hàng</h1>
		<p class="tagline">Rất hân hạnh được phục vụ quý khách !</p>

		<h2>Các sản phẩm mới</h2>

		<div class="card-grid">
			<c:forEach var="p" items="${latestProducts}">
				<a class="product-card"
					href="${pageContext.request.contextPath}/product-detail?id=${p.productid}">
					<c:choose>
						<c:when test="${not empty p.images}">
							<img class="thumb"
								src="${pageContext.request.contextPath}/image?fname=${p.images}" />
						</c:when>
						<c:otherwise>
							<div class="thumb-empty">Chưa có ảnh</div>
						</c:otherwise>
					</c:choose>
					<div class="info">
						<div class="name">${p.productname}</div>
						<div class="price">${p.price}</div>
						<div class="cat">${p.category.categoryname}</div>
					</div>
				</a>
			</c:forEach>
		</div>

	</div>

</body>
</html>