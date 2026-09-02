package nhutthanh.vn.utils;

import java.time.LocalDateTime;
import java.util.Random;

public class OtpUtils {

	public static final int OTP_EXPIRY_MINUTES = 5;

	public static String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000); // luôn ra 6 chữ số
		return String.valueOf(otp);
	}

	public static LocalDateTime generateExpiry() {
		return LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);
	}

	public static boolean isExpired(LocalDateTime expiry) {
		if (expiry == null) {
			return true;
		}
		return LocalDateTime.now().isAfter(expiry);
	}
}