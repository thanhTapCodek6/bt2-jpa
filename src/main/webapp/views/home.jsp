<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
<title>Trang chủ</title>
</head>
<body>

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

</body>
</html>