package nhutthanh.vn.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.http.Part;

public class FileUploadUtils {

	public static String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		for (String content : contentDisposition.split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName = content.substring(content.indexOf("=") + 2, content.length() - 1);
				return fileName.substring(fileName.lastIndexOf(File.separator) + 1)
						.substring(fileName.lastIndexOf('/') + 1);
			}
		}
		return Constants.DEFAULT_FILENAME;
	}

	public static String saveFile(Part part, String uploadRealPath) throws IOException {
		String originalFileName = getFileName(part);

		if (originalFileName == null || originalFileName.isBlank()) {
			return null; // không có file được chọn
		}

		File uploadDir = new File(uploadRealPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Đặt tên file duy nhất để tránh trùng lặp/ghi đè
		String extension = "";
		int dotIndex = originalFileName.lastIndexOf('.');
		if (dotIndex > 0) {
			extension = originalFileName.substring(dotIndex);
		}
		String newFileName = UUID.randomUUID().toString() + extension;

		part.write(uploadRealPath + File.separator + newFileName);

		return newFileName;
	}
}