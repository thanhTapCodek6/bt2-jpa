package nhutthanh.vn.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import nhutthanh.vn.dao.CategoryDao;
import nhutthanh.vn.dao.ProductDao;
import nhutthanh.vn.dao.impl.CategoryDaoImpl;
import nhutthanh.vn.dao.impl.ProductDaoImpl;
import nhutthanh.vn.entity.Account;
import nhutthanh.vn.entity.Category;
import nhutthanh.vn.entity.Product;
import nhutthanh.vn.utils.Constants;
import nhutthanh.vn.utils.FileUploadUtils;
import nhutthanh.vn.utils.ValidationUtils;

@WebServlet("/admin/products")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProductManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();
	private CategoryDao categoryDao = new CategoryDaoImpl();

	private boolean isAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		Account account = (Account) session.getAttribute("account");
		return account != null && "ADMIN".equals(account.getRole());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!isAdmin(request)) {
			request.setAttribute("error", "Bạn không có quyền truy cập trang này.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			showForm(request, response, null);
		} else if ("edit".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = productDao.findById(id);
			showForm(request, response, product);
		} else if ("delete".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			productDao.delete(id);
			response.sendRedirect(request.getContextPath() + "/admin/products");
		} else {
			showList(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!isAdmin(request)) {
			request.setAttribute("error", "Bạn không có quyền truy cập trang này.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}

		String idParam = request.getParameter("productid");
		String productname = request.getParameter("productname");
		String priceStr = request.getParameter("price");
		String description = request.getParameter("description");
		String quantityStr = request.getParameter("quantity");
		String statusStr = request.getParameter("status");
		String categoryIdStr = request.getParameter("categoryId");

		boolean isNew = (idParam == null || idParam.isBlank());

		// --- Validate dữ liệu bắt buộc ---
		if (productname == null || productname.isBlank()) {
			forwardFormWithError(request, response, isNew, idParam, "Tên sản phẩm không được để trống.");
			return;
		}

		if (productname.length() > 200) {
			forwardFormWithError(request, response, isNew, idParam, "Tên sản phẩm không được vượt quá 200 ký tự.");
			return;
		}

		Double price = parseDoubleSafe(priceStr);
		if (!ValidationUtils.isPositive(price)) {
			forwardFormWithError(request, response, isNew, idParam, "Giá sản phẩm phải là số lớn hơn 0.");
			return;
		}

		Integer quantity = parseIntSafe(quantityStr);
		if (!ValidationUtils.isNonNegative(quantity)) {
			forwardFormWithError(request, response, isNew, idParam, "Số lượng phải là số nguyên không âm.");
			return;
		}

		Integer status = parseIntSafe(statusStr);
		if (status == null || (status != 0 && status != 1)) {
			forwardFormWithError(request, response, isNew, idParam, "Trạng thái không hợp lệ.");
			return;
		}

		Integer categoryId = parseIntSafe(categoryIdStr);
		Category category = (categoryId != null) ? categoryDao.findById(categoryId) : null;
		if (category == null) {
			forwardFormWithError(request, response, isNew, idParam, "Vui lòng chọn danh mục hợp lệ.");
			return;
		}

		Product product;
		if (isNew) {
			product = new Product();
			product.setCreatedDate(LocalDateTime.now());
		} else {
			product = productDao.findById(Integer.parseInt(idParam));
			if (product == null) {
				forwardFormWithError(request, response, isNew, idParam, "Không tìm thấy sản phẩm để cập nhật.");
				return;
			}
		}

		product.setProductname(productname);
		product.setPrice(price);
		product.setDescription(description);
		product.setQuantity(quantity);
		product.setStatus(status);
		product.setCategory(category);

		// Xử lý upload ảnh (nếu người dùng có chọn file mới)
		Part filePart = request.getPart("imageFile");
		String savedFileName = FileUploadUtils.handleUpload(filePart, Constants.UPLOAD_DIR);
		if (savedFileName != null) {
			product.setImages(savedFileName);
		}

		if (isNew) {
			productDao.insert(product);
		} else {
			productDao.update(product);
		}

		response.sendRedirect(request.getContextPath() + "/admin/products");
	}

	private Double parseDoubleSafe(String value) {
		try {
			return (value == null || value.isBlank()) ? null : Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Integer parseIntSafe(String value) {
		try {
			return (value == null || value.isBlank()) ? null : Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private void forwardFormWithError(HttpServletRequest request, HttpServletResponse response, boolean isNew,
			String idParam, String errorMessage) throws ServletException, IOException {

		Product product = null;
		if (!isNew) {
			try {
				product = productDao.findById(Integer.parseInt(idParam));
			} catch (NumberFormatException ignored) {
				// giữ product = null
			}
		}

		var categories = categoryDao.findAll();
		request.setAttribute("categories", categories);
		request.setAttribute("product", product);
		request.setAttribute("error", errorMessage);
		request.getRequestDispatcher("/views/admin/product-form.jsp").forward(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var products = productDao.findAll();
		request.setAttribute("products", products);
		request.getRequestDispatcher("/views/admin/product-list.jsp").forward(request, response);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response, Product product)
			throws ServletException, IOException {
		var categories = categoryDao.findAll();
		request.setAttribute("categories", categories);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/views/admin/product-form.jsp").forward(request, response);
	}
}