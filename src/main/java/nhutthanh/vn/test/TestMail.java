package nhutthanh.vn.test;

import nhutthanh.vn.utils.MailUtils;

public class TestMail {

	public static void main(String[] args) {
		String toEmail = "thanh.hoang.gaming1505@gmail.com"; // đổi thành email bạn muốn nhận thử
		String otpCode = "123456";

		MailUtils.sendOtpMail(toEmail, otpCode);
	}
}