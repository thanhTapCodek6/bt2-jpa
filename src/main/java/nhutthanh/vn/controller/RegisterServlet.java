package nhutthanh.vn.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import nhutthanh.vn.dao.AccountDao;
import nhutthanh.vn.dao.impl.AccountDaoImpl;
import nhutthanh.vn.entity.Account;
import nhutthanh.vn.utils.MailUtils;
import nhutthanh.vn.utils.OtpUtils;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao accountDao = new AccountDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("verifyOtp".equals(action)) {
			handleVerifyOtp(request, response);
		} else {
			handleRegister(request, response);
		}
	}

	private void handleRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		if (username == null || username.isBlank() || email == null || email.isBlank() || password == null
				|| password.isBlank()) {
			request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin bắt buộc.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		if (!password.equals(confirmPassword)) {
			request.setAttribute("error", "Mật khẩu xác nhận không khớp.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		if (accountDao.findByUsername(username) != null) {
			request.setAttribute("error", "Username đã tồn tại.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		if (accountDao.findByEmail(email) != null) {
			request.setAttribute("error", "Email đã được sử dụng.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		// Chưa insert DB — chỉ lưu tạm trong Session
		String otp = OtpUtils.generateOtp();
		LocalDateTime expiry = OtpUtils.generateExpiry();

		HttpSession session = request.getSession();
		session.setAttribute("pendingUsername", username);
		session.setAttribute("pendingEmail", email);
		session.setAttribute("pendingFullname", fullname);
		session.setAttribute("pendingPassword", password);
		session.setAttribute("pendingOtp", otp);
		session.setAttribute("pendingOtpExpiry", expiry);

		try {
			MailUtils.sendOtpMail(email, otp);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Không gửi được email OTP, vui lòng thử lại sau.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		request.setAttribute("step", "otp");
		request.setAttribute("username", username);
		request.setAttribute("message", "Mã OTP đã được gửi tới email của bạn.");
		request.getRequestDispatcher("/views/register.jsp").forward(request, response);
	}

	private void handleVerifyOtp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String otpInput = request.getParameter("otp");

		String pendingUsername = (String) session.getAttribute("pendingUsername");
		String pendingOtp = (String) session.getAttribute("pendingOtp");
		LocalDateTime pendingExpiry = (LocalDateTime) session.getAttribute("pendingOtpExpiry");

		if (pendingUsername == null) {
			request.setAttribute("error", "Phiên đăng ký đã hết hạn, vui lòng đăng ký lại.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		if (OtpUtils.isExpired(pendingExpiry)) {
			session.invalidate();
			request.setAttribute("error", "Mã OTP đã hết hạn, vui lòng đăng ký lại.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		if (!pendingOtp.equals(otpInput)) {
			request.setAttribute("step", "otp");
			request.setAttribute("username", pendingUsername);
			request.setAttribute("error", "Mã OTP không đúng.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		// OTP đúng -> tạo Account thật trong DB
		Account account = new Account();
		account.setUsername(pendingUsername);
		account.setEmail((String) session.getAttribute("pendingEmail"));
		account.setFullname((String) session.getAttribute("pendingFullname"));
		account.setPassword((String) session.getAttribute("pendingPassword"));
		account.setRole("USER");
		account.setActive(true);

		Account inserted = accountDao.insert(account);

		session.invalidate();

		if (inserted == null) {
			request.setAttribute("error", "Tạo tài khoản thất bại, vui lòng thử lại.");
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
			return;
		}

		request.setAttribute("registerSuccess", true);
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}
}