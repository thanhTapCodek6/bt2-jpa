package nhutthanh.vn.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import nhutthanh.vn.config.JpaConfig;
import nhutthanh.vn.entity.Category;

public class TestCategory {

	public static void main(String[] args) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		Category cate = new Category();
		cate.setCategoryname("Điện thoại");
		cate.setImages("dienthoai.jpg");
		cate.setStatus(1);

		try {
			trans.begin();
			enma.persist(cate);
			trans.commit();
			System.out.println("Insert thành công! ID = " + cate.getCategoryid());
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}
}

