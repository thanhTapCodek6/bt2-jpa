<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
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
	<jsp:include page="/common/web/galaxy-bg.jsp" />
	<%@ include file="/common/admin/header.jsp"%>

	<div class="layout-with-sidebar">
		<%@ include file="/common/admin/left.jsp"%>
		<div class="page-wrap">
			<sitemesh:write property="body" />
		</div>
	</div>

<%@ include file="/common/admin/footer.jsp"%>
</body>
</html>