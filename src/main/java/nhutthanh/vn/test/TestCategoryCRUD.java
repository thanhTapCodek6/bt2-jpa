package nhutthanh.vn.test;

import java.util.List;

import nhutthanh.vn.dao.CategoryDao;
import nhutthanh.vn.dao.impl.CategoryDaoImpl;
import nhutthanh.vn.entity.Category;

public class TestCategoryCRUD {

	public static void main(String[] args) {
		CategoryDao dao = new CategoryDaoImpl();

		// 1. CREATE
		Category newCate = new Category();
		newCate.setCategoryname("Laptop");
		newCate.setImages("laptop.jpg");
		newCate.setStatus(1);
		Category inserted = dao.insert(newCate);
		System.out.println("Đã thêm, ID = " + inserted.getCategoryid());

		// 2. READ - tìm theo ID
		Category found = dao.findById(inserted.getCategoryid());
		System.out.println("Tìm thấy: " + found);

		// 3. UPDATE
		found.setCategoryname("Laptop Gaming");
		Category updated = dao.update(found);
		System.out.println("Đã cập nhật: " + updated);

		// 4. READ ALL
		List<Category> list = dao.findAll();
		System.out.println("Danh sách tất cả category:");
		for (Category c : list) {
			System.out.println(c);
		}

		// 5. DELETE
		boolean deleted = dao.delete(inserted.getCategoryid());
		System.out.println("Xóa thành công: " + deleted);

		// Kiểm tra lại sau khi xóa
		List<Category> listAfterDelete = dao.findAll();
		System.out.println("Danh sách sau khi xóa:");
		for (Category c : listAfterDelete) {
			System.out.println(c);
		}
	}
}