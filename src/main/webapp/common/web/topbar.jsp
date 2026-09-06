<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="top-bar">
	<c:choose>
		<c:when test="${sessionScope.account != null}">
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