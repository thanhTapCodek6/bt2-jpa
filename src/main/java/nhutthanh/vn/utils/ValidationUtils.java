package nhutthanh.vn.utils;

public class ValidationUtils {

	private static final String EMAIL_REGEX = "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";
	private static final String PHONE_REGEX = "^0\\d{9}$"; // 10 số, bắt đầu bằng 0

	public static boolean isValidEmail(String email) {
		return email != null && email.matches(EMAIL_REGEX);
	}

	public static boolean isValidPhone(String phone) {
		// Cho phép rỗng (không bắt buộc nhập), nhưng nếu có nhập thì phải đúng định dạng
		return phone == null || phone.isBlank() || phone.matches(PHONE_REGEX);
	}

	public static boolean isValidUsername(String username) {
		return username != null && username.length() >= 4 && !username.contains(" ");
	}

	public static boolean isValidPassword(String password) {
		return password != null && password.length() >= 6;
	}

	public static boolean isPositive(Double value) {
		return value != null && value > 0;
	}

	public static boolean isNonNegative(Integer value) {
		return value != null && value >= 0;
	}
}