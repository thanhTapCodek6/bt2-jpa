package nhutthanh.vn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import nhutthanh.vn.config.JpaConfig;
import nhutthanh.vn.dao.AccountDao;
import nhutthanh.vn.entity.Account;

public class AccountDaoImpl implements AccountDao {

	@Override
	public Account insert(Account account) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(account);
			trans.commit();
			return account;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public Account update(Account account) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Account merged = enma.merge(account);
			trans.commit();
			return merged;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public boolean delete(int id) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Account account = enma.find(Account.class, id);
			if (account != null) {
				enma.remove(account);
			}
			trans.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		} finally {
			enma.close();
		}
	}

	@Override
	public Account findById(int id) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			return enma.find(Account.class, id);
		} finally {
			enma.close();
		}
	}

	@Override
	public Account findByUsername(String username) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Account> query = enma.createNamedQuery("Account.findByUsername", Account.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public Account findByEmail(String email) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Account> query = enma.createNamedQuery("Account.findByEmail", Account.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Account> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Account> query = enma.createNamedQuery("Account.findAll", Account.class);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}
}