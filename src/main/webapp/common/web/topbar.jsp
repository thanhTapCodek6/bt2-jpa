<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="top-bar d-flex justify-content-end align-items-center">
	<c:choose>
		<c:when test="${sessionScope.account != null}">
			<div class="dropdown">
				<button class="btn dropdown-toggle d-flex align-items-center gap-2"
					type="button" data-bs-toggle="dropdown">
					<c:choose>
						<c:when test="${not empty sessionScope.account.images}">
							<img class="avatar-img"
								src="${pageContext.request.contextPath}/image?fname=${sessionScope.account.images}" />
						</c:when>
						<c:otherwise>
							<span class="avatar-placeholder">${fn:substring(sessionScope.account.username, 0, 1)}</span>
						</c:otherwise>
					</c:choose>
				</button>
				<ul class="dropdown-menu dropdown-menu-end">
					<li><span class="dropdown-item-text">${sessionScope.account.username}${sessionScope.account.role == 'ADMIN' ? ' (admin)' : ''}</span></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item"
						href="${pageContext.request.contextPath}/profile">Thông tin tài khoản</a></li>
					<li><a class="dropdown-item"
						href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<a class="btn btn-outline-light btn-sm me-2"
				href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			<a class="btn btn-sm"
				href="${pageContext.request.contextPath}/register">Đăng ký</a>
		</c:otherwise>
	</c:choose>
</div>