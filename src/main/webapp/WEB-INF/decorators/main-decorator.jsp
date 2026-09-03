<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title" /></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<sitemesh:write property="head" />
</head>
<body>
	<jsp:include page="/views/common/galaxy-bg.jsp" />
	<div class="top-nav">
		<a class="brand" href="${pageContext.request.contextPath}/home">NHỨT
			THẠNH STORE</a>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
			<c:choose>
				<c:when test="${sessionScope.account != null}">
					<c:if test="${sessionScope.account.role == 'ADMIN'}">
						<a href="${pageContext.request.contextPath}/admin/products">Quản
							lý sản phẩm</a>
					</c:if>

					<div class="avatar-menu">
						<button type="button" class="avatar-btn" onclick="toggleAvatarMenu()">
							<c:choose>
								<c:when test="${not empty sessionScope.account.images}">
									<img class="avatar-img"
										src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.images}" />
								</c:when>
								<c:otherwise>
									<div class="avatar-placeholder">
										${fn:substring(sessionScope.account.username, 0, 1)}
									</div>
								</c:otherwise>
							</c:choose>
						</button>
						<div class="avatar-dropdown" id="avatarDropdown">
							<div class="avatar-dropdown-name">
								${sessionScope.account.username}${sessionScope.account.role == 'ADMIN' ? ' (admin)' : ''}
							</div>
							<a href="${pageContext.request.contextPath}/profile">Thông tin tài khoản</a>
							<a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
					<a href="${pageContext.request.contextPath}/register">Đăng ký</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="page-wrap">
		<sitemesh:write property="body" />
	</div>

	<script>
		function toggleAvatarMenu() {
			document.getElementById('avatarDropdown').classList.toggle('show');
		}
		window.addEventListener('click', function(e) {
			if (!e.target.closest('.avatar-menu')) {
				var dd = document.getElementById('avatarDropdown');
				if (dd) dd.classList.remove('show');
			}
		});
	</script>

</body>
</html>