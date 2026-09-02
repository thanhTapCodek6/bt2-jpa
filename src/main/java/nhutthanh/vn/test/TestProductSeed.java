package nhutthanh.vn.test;

import java.time.LocalDateTime;

import nhutthanh.vn.dao.CategoryDao;
import nhutthanh.vn.dao.ProductDao;
import nhutthanh.vn.dao.impl.CategoryDaoImpl;
import nhutthanh.vn.dao.impl.ProductDaoImpl;
import nhutthanh.vn.entity.Category;
import nhutthanh.vn.entity.Product;

public class TestProductSeed {

	public static void main(String[] args) {
		CategoryDao categoryDao = new CategoryDaoImpl();
		ProductDao productDao = new ProductDaoImpl();

		// Tạo 2 category mẫu
		Category laptop = new Category();
		laptop.setCategoryname("Laptop");
		laptop.setImages("laptop-cate.jpg");
		laptop.setStatus(1);
		laptop = categoryDao.insert(laptop);

		Category phone = new Category();
		phone.setCategoryname("Điện thoại");
		phone.setImages("phone-cate.jpg");
		phone.setStatus(1);
		phone = categoryDao.insert(phone);

		// Tạo 15 sản phẩm mẫu, xen kẽ 2 category, để test phân trang (6sp/trang)
		for (int i = 1; i <= 15; i++) {
			Product p = new Product();
			p.setProductname("Sản phẩm mẫu " + i);
			p.setPrice(1000000.0 * i);
			p.setDescription("Mô tả cho sản phẩm mẫu số " + i);
			p.setImages("product-" + i + ".jpg");
			p.setQuantity(10 + i);
			p.setCreatedDate(LocalDateTime.now().minusMinutes(15 - i)); // sp sau tạo sau -> mới hơn
			p.setStatus(1);
			p.setCategory(i % 2 == 0 ? phone : laptop);

			productDao.insert(p);
		}

		System.out.println("Đã seed xong dữ liệu mẫu!");
	}
}