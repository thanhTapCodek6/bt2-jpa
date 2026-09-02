package nhutthanh.vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import nhutthanh.vn.dao.AccountDao;
import nhutthanh.vn.dao.impl.AccountDaoImpl;
import nhutthanh.vn.entity.Account;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao accountDao = new AccountDaoImpl();
	private static final String REMEMBER_COOKIE = "rememberedUsername";
	private static final int REMEMBER_MAX_AGE = 30 * 24 * 60 * 60; // 30 ngày, tính bằng giây

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Nếu đã đăng nhập rồi thì chuyển thẳng về trang chủ
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		// Kiểm tra cookie "Remember me"
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (REMEMBER_COOKIE.equals(cookie.getName())) {
					Account account = accountDao.findByUsername(cookie.getValue());
					if (account != null) {
						request.getSession().setAttribute("account", account);
						response.sendRedirect(request.getContextPath() + "/");
						return;
					}
				}
			}
		}

		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");

		Account account = accountDao.findByUsername(username);

		if (account == null || !account.getPassword().equals(password)) {
			request.setAttribute("error", "Sai username hoặc mật khẩu.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}

		if (!account.isActive()) {
			request.setAttribute("error", "Tài khoản chưa được kích hoạt.");
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			return;
		}

		// Đăng nhập thành công -> lưu session
		HttpSession session = request.getSession();
		session.setAttribute("account", account);

		// Xử lý Remember me
		if ("on".equals(rememberMe)) {
			Cookie cookie = new Cookie(REMEMBER_COOKIE, username);
			cookie.setMaxAge(REMEMBER_MAX_AGE);
			cookie.setPath(request.getContextPath() + "/");
			response.addCookie(cookie);
		}

		response.sendRedirect(request.getContextPath() + "/");
	}
}