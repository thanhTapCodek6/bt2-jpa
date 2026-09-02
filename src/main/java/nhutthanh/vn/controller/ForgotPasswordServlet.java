package nhutthanh.vn.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nhutthanh.vn.dao.AccountDao;
import nhutthanh.vn.dao.impl.AccountDaoImpl;
import nhutthanh.vn.entity.Account;
import nhutthanh.vn.utils.MailUtils;
import nhutthanh.vn.utils.OtpUtils;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao accountDao = new AccountDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("resetPassword".equals(action)) {
			handleResetPassword(request, response);
		} else {
			handleSendOtp(request, response);
		}
	}

	private void handleSendOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");

		if (username == null || username.isBlank()) {
			request.setAttribute("error", "Vui lòng nhập username.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		Account account = accountDao.findByUsername(username);

		if (account == null) {
			request.setAttribute("error", "Không tìm thấy tài khoản với username này.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		String otp = OtpUtils.generateOtp();
		LocalDateTime expiry = OtpUtils.generateExpiry();
		account.setOtpCode(otp);
		account.setOtpExpiry(expiry);
		accountDao.update(account);

		try {
			MailUtils.sendOtpMail(account.getEmail(), otp);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Không gửi được email OTP, vui lòng thử lại sau.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		request.setAttribute("step", "reset");
		request.setAttribute("username", username);
		request.setAttribute("message", "Mã OTP đã được gửi tới email đã đăng ký.");
		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	}

	private void handleResetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String otpInput = request.getParameter("otp");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		Account account = accountDao.findByUsername(username);

		if (account == null) {
			request.setAttribute("error", "Tài khoản không tồn tại.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		if (OtpUtils.isExpired(account.getOtpExpiry())) {
			request.setAttribute("error", "Mã OTP đã hết hạn, vui lòng thực hiện lại.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		if (account.getOtpCode() == null || !account.getOtpCode().equals(otpInput)) {
			request.setAttribute("step", "reset");
			request.setAttribute("username", username);
			request.setAttribute("error", "Mã OTP không đúng.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		if (newPassword == null || newPassword.isBlank() || !newPassword.equals(confirmPassword)) {
			request.setAttribute("step", "reset");
			request.setAttribute("username", username);
			request.setAttribute("error", "Mật khẩu xác nhận không khớp.");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
			return;
		}

		account.setPassword(newPassword);
		account.setOtpCode(null);
		account.setOtpExpiry(null);
		accountDao.update(account);

		request.setAttribute("resetSuccess", true);
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}
}