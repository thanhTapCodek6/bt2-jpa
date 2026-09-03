<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>
<head>
<title>Đăng nhập</title>
</head>
<body>

	<div class="auth-wrap">
		<div class="panel">
			<h2>Đăng nhập</h2>
			<p class="tagline">Bước vào cổng không gian của bạn.</p>

			<c:if test="${registerSuccess == true}">
				<div class="msg-success">Đăng ký thành công! Vui lòng đăng
					nhập.</div>
			</c:if>
			<c:if test="${resetSuccess == true}">
				<div class="msg-success">Đổi mật khẩu thành công! Vui lòng
					đăng nhập lại.</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="msg-error">${error}</div>
			</c:if>

			<form method="post" action="${pageContext.request.contextPath}/login">
				<div class="field">
					<label>Username</label> <input type="text" name="username" required />
				</div>
				<div class="field">
					<label>Mật khẩu</label> <input type="password" name="password"
						required />
				</div>
				<div class="field">
					<label><input type="checkbox" name="rememberMe" />Ghi nhớ
						đăng nhập</label>
				</div>

				<button type="submit" class="btn">Đăng nhập</button>
			</form>

			<div class="auth-links">
				Chưa có tài khoản? <a
					href="${pageContext.request.contextPath}/register">Đăng ký</a>
				&nbsp;|&nbsp; <a
					href="${pageContext.request.contextPath}/forgot-password">Quên
					mật khẩu?</a>
			</div>
		</div>
</body>
</html>