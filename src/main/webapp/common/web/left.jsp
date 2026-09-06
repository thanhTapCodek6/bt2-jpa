<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="sidebar-filter">
	<h3>Lọc theo</h3>

	<form method="get" action="${pageContext.request.contextPath}/product">
		<div class="mb-3">
			<label class="form-label">Tên sản phẩm</label>
			<input type="text" class="form-control" name="keyword"
				placeholder="Nhập tên sản phẩm..." value="${param.keyword}" />
		</div>

		<div class="mb-3">
			<label class="form-label">Danh mục</label>
			<c:forEach var="cate" items="${allCategories}">
				<div class="form-check">
					<input class="form-check-input" type="checkbox"
						name="categoryId" value="${cate.categoryid}"
						id="cate${cate.categoryid}"
						${categorySelected[cate.categoryid] ? 'checked' : ''} />
					<label class="form-check-label" for="cate${cate.categoryid}">${cate.categoryname}</label>
				</div>
			</c:forEach>
		</div>

		<div class="d-grid gap-2">
			<button type="submit" class="btn btn-warning btn-sm">Áp
				dụng</button>
			<a class="btn btn-outline-warning btn-sm"
				href="${pageContext.request.contextPath}/product">Xóa lọc</a>
		</div>
	</form>
</div>