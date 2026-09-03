<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
	<jsp:include page="/views/common/galaxy-bg.jsp" />
	<div class="top-nav">
		<a class="brand" href="${pageContext.request.contextPath}/home">NHỨT THẠNH </a>
		<div class="nav-links">
			<a href="${pageContext.request.contextPath}/product">Sản phẩm</a>
			<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
		</div>
	</div>

	<div class="page-wrap">
		<div class="auth-wrap">
			<div class="panel">
				<h2>Đăng ký tài khoản</h2>
				<p class="tagline">Tạo một điểm neo mới trong thiên hà của chúng tôi.</p>

				<c:if test="${not empty error}">
					<div class="msg-error">${error}</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="msg-success">${message}</div>
				</c:if>

				<c:choose>

					<%-- BƯỚC 1: FORM ĐĂNG KÝ --%>
					<c:when test="${empty step}">
						<form method="post" action="${pageContext.request.contextPath}/register">
							<input type="hidden" name="action" value="register" />

							<div class="field">
								<label>Username</label>
								<input type="text" name="username" required />
							</div>
							<div class="field">
								<label>Email</label>
								<input type="email" name="email" required />
							</div>
							<div class="field">
								<label>Họ tên</label>
								<input type="text" name="fullname" />
							</div>
							<div class="field">
								<label>Mật khẩu</label>
								<input type="password" name="password" required />
							</div>
							<div class="field">
								<label>Xác nhận mật khẩu</label>
								<input type="password" name="confirmPassword" required />
							</div>

							<button type="submit" class="btn">Đăng ký</button>
						</form>
					</c:when>

					<%-- BƯỚC 2: FORM NHẬP OTP --%>
					<c:when test="${step == 'otp'}">
						<form method="post" action="${pageContext.request.contextPath}/register">
							<input type="hidden" name="action" value="verifyOtp" />
							<input type="hidden" name="username" value="${username}" />

							<div class="field">
								<label>Mã OTP đã gửi tới email của bạn</label>
								<input type="text" name="otp" required />
							</div>

							<button type="submit" class="btn">Xác nhận</button>
						</form>
					</c:when>

				</c:choose>

				<div class="auth-links">
					Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>