<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<title>Quản lý sản phẩm</title>
</head>
<body>

	<h1>Quản lý sản phẩm</h1>

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
								<img class="thumb-sm" src="${pageContext.request.contextPath}/image?fname=${p.images}" />
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

</body>
</html>