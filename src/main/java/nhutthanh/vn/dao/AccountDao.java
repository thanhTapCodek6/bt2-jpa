package nhutthanh.vn.dao;

import java.util.List;
import nhutthanh.vn.entity.Account;

public interface AccountDao {
	Account insert(Account account);

	Account update(Account account);

	boolean delete(int id);

	Account findById(int id);

	Account findByUsername(String username);

	Account findByEmail(String email);

	List<Account> findAll();
}