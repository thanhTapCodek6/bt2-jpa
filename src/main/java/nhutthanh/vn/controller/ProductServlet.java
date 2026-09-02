package nhutthanh.vn.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nhutthanh.vn.dao.ProductDao;
import nhutthanh.vn.dao.impl.ProductDaoImpl;
import nhutthanh.vn.entity.Product;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = 6;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageIndex = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			try {
				pageIndex = Integer.parseInt(pageParam);
				if (pageIndex < 1) {
					pageIndex = 1;
				}
			} catch (NumberFormatException e) {
				pageIndex = 1;
			}
		}

		long totalProducts = productDao.countAll();
		int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
		if (totalPages == 0) {
			totalPages = 1;
		}
		if (pageIndex > totalPages) {
			pageIndex = totalPages;
		}

		List<Product> products = productDao.findByPage(pageIndex, PAGE_SIZE);

		request.setAttribute("products", products);
		request.setAttribute("currentPage", pageIndex);
		request.setAttribute("totalPages", totalPages);

		request.getRequestDispatcher("/views/product-list.jsp").forward(request, response);
	}
}