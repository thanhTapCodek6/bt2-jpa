<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
<title>Thông tin cá nhân</title>
</head>
<body>

	<h1>Thông tin cá nhân</h1>

	<c:if test="${not empty message}">
		<div class="msg-success">${message}</div>
	</c:if>
	<c:if test="${not empty error}">
		<div class="msg-error">${error}</div>
	</c:if>

	<div class="panel" style="max-width: 600px;">
		<form method="post"
			action="${pageContext.request.contextPath}/profile"
			enctype="multipart/form-data">

			<div class="field">
				<label>Username</label>
				<input type="text" value="${sessionScope.account.username}" disabled />
			</div>

			<div class="field">
				<label>Email</label>
				<input type="email" value="${sessionScope.account.email}" disabled />
			</div>

			<div class="field">
				<label>Họ tên</label>
				<input type="text" name="fullname"
					value="${sessionScope.account.fullname}" />
			</div>

			<div class="field">
				<label>Số điện thoại</label>
				<input type="text" name="phone"
					value="${sessionScope.account.phone}" />
			</div>

			<div class="field">
				<c:if test="${not empty sessionScope.account.images}">
					<label>Ảnh đại diện hiện tại</label>
					<img
						src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.images}"
						width="120"
						style="border-radius: 50%; border: 1px solid var(--border-glow); margin-bottom: 10px; display: block;" />
				</c:if>
				<label>Chọn ảnh mới (để trống nếu giữ ảnh cũ)</label>
				<input type="file" name="imageFile" accept="image/*" />
			</div>

			<button type="submit" class="btn">Lưu thay đổi</button>
		</form>
	</div>

</body>
</html>