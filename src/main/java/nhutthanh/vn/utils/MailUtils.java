package nhutthanh.vn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailUtils {

	private static Properties loadConfig() {
		Properties prop = new Properties();
		try (InputStream input = MailUtils.class.getClassLoader()
				.getResourceAsStream("mail.properties")) {
			if (input == null) {
				throw new RuntimeException("Không tìm thấy file mail.properties");
			}
			prop.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Lỗi đọc mail.properties", e);
		}
		return prop;
	}

	public static void sendOtpMail(String toEmail, String otpCode) {
		Properties config = loadConfig();
		String username = config.getProperty("mail.username");
		String password = config.getProperty("mail.password");

		Properties props = new Properties();
		props.put("mail.smtp.host", config.getProperty("mail.smtp.host"));
		props.put("mail.smtp.port", config.getProperty("mail.smtp.port"));
		props.put("mail.smtp.auth", config.getProperty("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", config.getProperty("mail.smtp.starttls.enable"));

		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Mã xác thực OTP");
			message.setText("Mã OTP của bạn là: " + otpCode + "\nMã có hiệu lực trong 5 phút.");

			Transport.send(message);
			System.out.println("Gửi mail OTP thành công tới: " + toEmail);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException("Gửi mail thất bại", e);
		}
	}
}