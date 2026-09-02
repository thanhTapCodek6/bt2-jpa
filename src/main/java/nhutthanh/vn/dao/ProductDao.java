package nhutthanh.vn.dao;

import java.util.List;
import nhutthanh.vn.entity.Product;

public interface ProductDao {
	Product insert(Product product);

	Product update(Product product);

	boolean delete(int id);

	Product findById(int id);

	List<Product> findAll();

	List<Product> findLatest(int limit);

	List<Product> findByPage(int pageIndex, int pageSize);

	long countAll();
}