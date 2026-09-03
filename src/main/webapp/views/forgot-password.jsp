<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
<title>Quên mật khẩu</title>
</head>
<body>

	<div class="auth-wrap">
		<div class="panel">
			<h2>Quên mật khẩu</h2>
			<p class="tagline">Khôi phục lại điểm neo đã lạc mất trong không gian.</p>

			<c:if test="${not empty error}">
				<div class="msg-error">${error}</div>
			</c:if>
			<c:if test="${not empty message}">
				<div class="msg-success">${message}</div>
			</c:if>

			<c:choose>

				<%-- BƯỚC 1: NHẬP USERNAME --%>
				<c:when test="${empty step}">
					<form method="post" action="${pageContext.request.contextPath}/forgot-password">
						<input type="hidden" name="action" value="sendOtp" />

						<div class="field">
							<label>Username</label>
							<input type="text" name="username" required />
						</div>

						<button type="submit" class="btn">Gửi mã OTP</button>
					</form>
				</c:when>

				<%-- BƯỚC 2: NHẬP OTP + MẬT KHẨU MỚI --%>
				<c:when test="${step == 'reset'}">
					<form method="post" action="${pageContext.request.contextPath}/forgot-password">
						<input type="hidden" name="action" value="resetPassword" />
						<input type="hidden" name="username" value="${username}" />

						<div class="field">
							<label>Mã OTP</label>
							<input type="text" name="otp" required />
						</div>
						<div class="field">
							<label>Mật khẩu mới</label>
							<input type="password" name="newPassword" required />
						</div>
						<div class="field">
							<label>Xác nhận mật khẩu mới</label>
							<input type="password" name="confirmPassword" required />
						</div>

						<button type="submit" class="btn">Đặt lại mật khẩu</button>
					</form>
				</c:when>

			</c:choose>

			<div class="auth-links">
				<a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
			</div>
		</div>
	</div>

</body>
</html>