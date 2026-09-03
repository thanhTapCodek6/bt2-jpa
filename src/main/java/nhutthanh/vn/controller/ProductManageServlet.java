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

		Product product;
		boolean isNew = (idParam == null || idParam.isBlank());

		if (isNew) {
			product = new Product();
			product.setCreatedDate(LocalDateTime.now());
		} else {
			product = productDao.findById(Integer.parseInt(idParam));
		}

		product.setProductname(productname);
		product.setPrice(Double.parseDouble(priceStr));
		product.setDescription(description);
		product.setQuantity(Integer.parseInt(quantityStr));
		product.setStatus(Integer.parseInt(statusStr));

		Category category = categoryDao.findById(Integer.parseInt(categoryIdStr));
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