package nhutthanh.vn.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import nhutthanh.vn.config.JpaConfig;
import nhutthanh.vn.entity.Account;

public class TestAccount {

	public static void main(String[] args) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		Account acc = new Account();
		acc.setUsername("thanhnv");
		acc.setPassword("123456");
		acc.setEmail("thanhnv@example.com");
		acc.setFullname("Nhut Thanh");
		acc.setRole("USER");
		acc.setActive(false);

		try {
			trans.begin();
			enma.persist(acc);
			trans.commit();
			System.out.println("Insert thành công! ID = " + acc.getAccountid());
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}
}