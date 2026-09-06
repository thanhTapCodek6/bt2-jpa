<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><sitemesh:write property="title" /></title>

<!-- Bootstrap 5 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<sitemesh:write property="head" />
</head>
<body>
	<jsp:include page="/common/web/galaxy-bg.jsp" />
	<%@ include file="/common/web/header.jsp"%>

	<div class="layout-with-sidebar">
		<%@ include file="/common/web/left.jsp"%>
		<div class="page-wrap">
			<sitemesh:write property="body" />
		</div>
	</div>

	<%@ include file="/common/web/footer.jsp"%>

	<!-- Bootstrap 5 JS bundle -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>