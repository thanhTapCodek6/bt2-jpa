package nhutthanh.vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import nhutthanh.vn.dao.AccountDao;
import nhutthanh.vn.dao.impl.AccountDaoImpl;
import nhutthanh.vn.entity.Account;
import nhutthanh.vn.utils.Constants;
import nhutthanh.vn.utils.FileUploadUtils;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AccountDao accountDao = new AccountDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("account") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("account") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		Account sessionAccount = (Account) session.getAttribute("account");
		Account account = accountDao.findById(sessionAccount.getAccountid());

		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");

		account.setFullname(fullname);
		account.setPhone(phone);

		Part filePart = request.getPart("imageFile");
		String savedFileName = FileUploadUtils.handleUpload(filePart, Constants.UPLOAD_DIR);
		if (savedFileName != null) {
			account.setImages(savedFileName);
		}

		Account updated = accountDao.update(account);

		// Cập nhật lại session để giao diện hiển thị đúng ngay lập tức
		session.setAttribute("account", updated);

		request.setAttribute("message", "Cập nhật thông tin thành công.");
		request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
	}
}