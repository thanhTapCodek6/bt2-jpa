<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="sidebar-filter">
	<h3>Lọc theo</h3>

	<form method="get" action="${pageContext.request.contextPath}/product">
		<div class="field">
			<label>Tên sản phẩm</label>
			<input type="text" name="keyword" placeholder="Nhập tên sản phẩm..."
				value="${param.keyword}" />
		</div>

		<div class="field">
			<label>Danh mục</label>
			<c:forEach var="cate" items="${allCategories}">
				<label style="display:block; font-weight: normal; margin-bottom: 6px;">
					<input type="checkbox" name="categoryId" value="${cate.categoryid}"
						${categorySelected[cate.categoryid] ? 'checked' : ''} />
					${cate.categoryname}
				</label>
			</c:forEach>
		</div>

		<button type="submit" class="btn btn-small">Áp dụng</button>
		<a class="btn btn-outline btn-small" href="${pageContext.request.contextPath}/product">Xóa lọc</a>
	</form>
</div>