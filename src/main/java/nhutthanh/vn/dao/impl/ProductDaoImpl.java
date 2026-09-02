package nhutthanh.vn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import nhutthanh.vn.config.JpaConfig;
import nhutthanh.vn.dao.ProductDao;
import nhutthanh.vn.entity.Product;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Product insert(Product product) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(product);
			trans.commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public Product update(Product product) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Product merged = enma.merge(product);
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
			Product product = enma.find(Product.class, id);
			if (product != null) {
				enma.remove(product);
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
	public Product findById(int id) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			return enma.find(Product.class, id);
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Product> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Product> findLatest(int limit) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Product> query = enma.createNamedQuery("Product.findLatest", Product.class);
			query.setMaxResults(limit);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Product> findByPage(int pageIndex, int pageSize) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Product> query = enma.createNamedQuery("Product.findLatest", Product.class);
			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}

	@Override
	public long countAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Long> query = enma.createNamedQuery("Product.countAll", Long.class);
			return query.getSingleResult();
		} finally {
			enma.close();
		}
	}
}