package nhutthanh.vn.dao;

import java.util.List;
import nhutthanh.vn.entity.Category;

public interface CategoryDao {
	Category insert(Category category);

	Category update(Category category);

	boolean delete(int id);

	Category findById(int id);

	List<Category> findAll();
}