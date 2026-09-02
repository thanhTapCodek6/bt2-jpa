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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Product> latestProducts = productDao.findLatest(10);
		request.setAttribute("latestProducts", latestProducts);
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
}