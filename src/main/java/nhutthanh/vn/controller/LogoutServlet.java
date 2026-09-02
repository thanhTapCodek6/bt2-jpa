package nhutthanh.vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Xóa cookie remember me nếu có
		Cookie cookie = new Cookie("rememberedUsername", "");
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath() + "/");
		response.addCookie(cookie);

		response.sendRedirect(request.getContextPath() + "/login");
	}
}