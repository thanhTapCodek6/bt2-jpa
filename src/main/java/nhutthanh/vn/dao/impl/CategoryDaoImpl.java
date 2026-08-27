package nhutthanh.vn.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import nhutthanh.vn.config.JpaConfig;
import nhutthanh.vn.dao.CategoryDao;
import nhutthanh.vn.entity.Category;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public Category insert(Category category) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category update(Category category) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category merged = enma.merge(category);
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
			Category category = enma.find(Category.class, id);
			if (category != null) {
				enma.remove(category);
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
	public Category findById(int id) {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			return enma.find(Category.class, id);
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		try {
			TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
			return query.getResultList();
		} finally {
			enma.close();
		}
	}
}