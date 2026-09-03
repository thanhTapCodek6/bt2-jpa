<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
<title>${product != null ? 'Sửa sản phẩm' : 'Thêm sản phẩm'}</title>
</head>
<body>

	<h1>${product != null ? 'Sửa sản phẩm' : 'Thêm sản phẩm mới'}</h1>

	<div class="panel" style="max-width: 600px;">
		<form method="post"
			action="${pageContext.request.contextPath}/admin/products"
			enctype="multipart/form-data">

			<c:if test="${product != null}">
				<input type="hidden" name="productid" value="${product.productid}" />
			</c:if>

			<div class="field">
				<label>Tên sản phẩm</label> <input type="text" name="productname"
					value="${product != null ? product.productname : ''}" required />
			</div>

			<div class="field">
				<label>Giá</label> <input type="number" step="0.01" name="price"
					value="${product != null ? product.price : ''}" required />
			</div>

			<div class="field">
				<label>Mô tả</label>
				<textarea name="description" rows="4">${product != null ? product.description : ''}</textarea>
			</div>

			<div class="field">
				<c:if test="${product != null && not empty product.images}">
					<label>Ảnh hiện tại</label>
					<img
						src="${pageContext.request.contextPath}/image?fname=${product.images}"
						width="150"
						style="border-radius: 8px; border: 1px solid var(--border-glow); margin-bottom: 10px; display: block;" />
				</c:if>
				<label>Chọn ảnh${product != null ? ' mới (để trống nếu giữ ảnh cũ)' : ''}</label>
				<input type="file" name="imageFile" accept="image/*" />
			</div>

			<div class="field">
				<label>Số lượng</label> <input type="number" name="quantity"
					value="${product != null ? product.quantity : 0}" required />
			</div>

			<div class="field">
				<label>Trạng thái</label> <select name="status">
					<option value="1"
						${product != null && product.status == 1 ? 'selected' : ''}>Còn
						bán</option>
					<option value="0"
						${product != null && product.status == 0 ? 'selected' : ''}>Ngừng
						bán</option>
				</select>
			</div>

			<div class="field">
				<label>Danh mục</label> <select name="categoryId">
					<c:forEach var="c" items="${categories}">
						<option value="${c.categoryid}"
							${product != null && product.category.categoryid == c.categoryid ? 'selected' : ''}>
							${c.categoryname}</option>
					</c:forEach>
				</select>
			</div>

			<button type="submit" class="btn">Lưu</button>
			<a class="btn btn-outline"
				href="${pageContext.request.contextPath}/admin/products">Hủy</a>
		</form>
	</div>

</body>
</html>