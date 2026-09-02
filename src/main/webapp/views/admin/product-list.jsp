<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý sản phẩm</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/galaxy-bg.jsp" />
	<div class="top-nav">
		<a class="brand" href="${pageContext.request.contextPath}/home">NEBULA STORE</a>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/product">Xem cửa hàng</a>
			<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
		</div>
	</div>

	<div class="page-wrap">

		<h1>Quản lý sản phẩm</h1>
		<p class="tagline">Bảng điều khiển trung tâm chỉ huy của quản trị viên.</p>

		<a class="btn" href="${pageContext.request.contextPath}/admin/products?action=add">+ Thêm sản phẩm mới</a>

		<div class="panel" style="margin-top:24px;">
			<table>
				<tr>
					<th>Ảnh</th>
					<th>Tên sản phẩm</th>
					<th>Giá</th>
					<th>SL</th>
					<th>Danh mục</th>
					<th>Trạng thái</th>
					<th>Ngày tạo</th>
					<th>Hành động</th>
				</tr>
				<c:forEach var="p" items="${products}">
					<tr>
						<td>
							<c:choose>
								<c:when test="${not empty p.images}">
									<img class="thumb-sm" src="${pageContext.request.contextPath}/uploads/${p.images}" />
								</c:when>
								<c:otherwise>—</c:otherwise>
							</c:choose>
						</td>
						<td>${p.productname}</td>
						<td>${p.price}</td>
						<td>${p.quantity}</td>
						<td>${p.category.categoryname}</td>
						<td>${p.status == 1 ? 'Còn bán' : 'Ngừng bán'}</td>
						<td style="font-size:12px; color:var(--text-dim);">${p.createdDate}</td>
						<td>
							<a class="btn btn-outline btn-small"
								href="${pageContext.request.contextPath}/admin/products?action=edit&id=${p.productid}">Sửa</a>
							<a class="btn btn-danger btn-small"
								href="${pageContext.request.contextPath}/admin/products?action=delete&id=${p.productid}"
								onclick="return confirm('Xác nhận xóa sản phẩm này?');">Xóa</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>

	</div>

</body>
</html>