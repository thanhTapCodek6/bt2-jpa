package nhutthanh.vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nhutthanh.vn.dao.ProductDao;
import nhutthanh.vn.dao.impl.ProductDaoImpl;
import nhutthanh.vn.entity.Product;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");

		if (idParam == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		try {
			int id = Integer.parseInt(idParam);
			Product product = productDao.findById(id);

			if (product == null) {
				request.setAttribute("error", "Không tìm thấy sản phẩm.");
				request.getRequestDispatcher("/views/product-detail.jsp").forward(request, response);
				return;
			}

			request.setAttribute("product", product);
			request.getRequestDispatcher("/views/product-detail.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/");
		}
	}
}