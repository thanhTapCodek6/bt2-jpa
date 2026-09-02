<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tất cả sản phẩm</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/galaxy-bg.jsp" />
	<div class="top-nav">
		<a class="brand" href="${pageContext.request.contextPath}/home">NEBULA
			STORE</a>
		<div class="nav-links">
			<c:choose>
				<c:when test="${sessionScope.account != null}">
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

		<h1>Tất cả sản phẩm</h1>
		<p class="tagline">Toàn bộ thiên thể hiện có trong thiên hà cửa
			hàng.</p>

		<div class="card-grid">
			<c:forEach var="p" items="${products}">
				<a class="product-card"
					href="${pageContext.request.contextPath}/product-detail?id=${p.productid}">
					<c:choose>
						<c:when test="${not empty p.images}">
							<img class="thumb"
								src="${pageContext.request.contextPath}/uploads/${p.images}" />
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

		<div class="pagination">
			<c:if test="${currentPage > 1}">
				<a
					href="${pageContext.request.contextPath}/product?page=${currentPage - 1}">«
					Trước</a>
			</c:if>

			<c:forEach begin="1" end="${totalPages}" var="i">
				<c:choose>
					<c:when test="${i == currentPage}">
						<span class="active">${i}</span>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/product?page=${i}">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage < totalPages}">
				<a
					href="${pageContext.request.contextPath}/product?page=${currentPage + 1}">Sau
					»</a>
			</c:if>
		</div>

	</div>

</body>
</html>