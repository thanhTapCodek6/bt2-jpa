<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/galaxy-bg.jsp" />
	<div class="top-nav">
		<a class="brand" href="${pageContext.request.contextPath}/home">NEBULA
			STORE</a>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
			<c:choose>
				<c:when test="${sessionScope.account != null}">
					<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="page-wrap">

		<c:if test="${not empty error}">
			<div class="msg-error">${error}</div>
		</c:if>

		<c:if test="${product != null}">
			<div class="panel" style="display: flex; gap: 32px; flex-wrap: wrap;">

				<div style="flex: 1 1 300px;">
					<c:choose>
						<c:when test="${not empty product.images}">
							<img
								src="${pageContext.request.contextPath}/uploads/${product.images}"
								style="width: 100%; border-radius: 12px; border: 1px solid var(--border-glow);" />
						</c:when>
						<c:otherwise>
							<div class="thumb-empty"
								style="height: 300px; border-radius: 12px;">Chưa có ảnh</div>
						</c:otherwise>
					</c:choose>
				</div>

				<div style="flex: 1 1 320px;">
					<h1>${product.productname}</h1>
					<p class="price"
						style="font-size: 22px; color: var(--amber); font-family: 'Orbitron', sans-serif;">
						${product.price}</p>
					<p class="cat" style="color: var(--text-dim);">Danh mục:
						${product.category.categoryname}</p>
					<p>Số lượng còn: ${product.quantity}</p>
					<p>Trạng thái: ${product.status == 1 ? 'Còn bán' : 'Ngừng bán'}</p>
					<p style="color: var(--text-dim); font-size: 12px;">Ngày tạo:
						${product.createdDate}</p>

					<h3>Mô tả</h3>
					<p>${product.description}</p>
				</div>

			</div>
		</c:if>

	</div>

</body>
</html>