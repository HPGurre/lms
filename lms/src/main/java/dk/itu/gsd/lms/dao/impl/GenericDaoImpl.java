package dk.itu.gsd.lms.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import dk.itu.gsd.lms.dao.GenericDao;
import dk.itu.gsd.lms.model.HibernateModel;

/**
 * An abstract implementation of the Hibernate <code>GenericDao<T></code>
 * interface
 * 
 * @see <code>GenericDao<T></code>
 * 
 * @param <T> where T is the object name
 */
public abstract class GenericDaoImpl<T extends HibernateModel> implements GenericDao<T> {

	protected static Logger logger = Logger.getLogger(GenericDaoImpl.class);
	
	private Class<T> clazz;

	@PersistenceContext
	protected
	EntityManager entityManager;

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	EntityManager extendedEntityManager;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl(){
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	@Transactional
	public T find(Long id) {
		return this.entityManager.find(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.clazz.getSimpleName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Long> findAllIds() {
		return this.entityManager.createQuery("select id from " + this.clazz.getSimpleName()).getResultList();
	}

	@Override
	@Transactional
	public T save(T entity) {
		try {
			if(BeanUtils.getProperty(entity, "id") == null){
				this.entityManager.persist(entity);
			} else {
				entity = this.update(entity);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return entity;
	}

	@Override
	@Transactional
	public T update(T entity) {
		return this.entityManager.merge(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.entityManager.remove(this.find(id));
	}
	
//	public Query doHibernateNamedQuery(String query) {
//		return ((Session)this.extendedEntityManager.getDelegate()).getNamedQuery(query);
//	}
//
//	public Session getHibernateSession() {
//		return ((Session)this.extendedEntityManager.getDelegate());
//	}
//
//	
//	public Query doHibernateQuery(String query) {
//		return ((Session)this.extendedEntityManager.getDelegate()).createQuery(query);
//	}
//	
//	public SQLQuery doHibernateSQLQuery(String query) {
//		return ((Session)this.extendedEntityManager.getDelegate()).createSQLQuery(query);
//	}
	
	protected <V> List<V> returnAsList(List<V> l) {
		if (l != null) {
			return l;
		} else {
			return new ArrayList<V>();
		}
	}
	
	protected <V> V returnFirstEntity(List<V> l) {
		if (l == null || l.size() == 0) {
			return null;
		} else {
			return l.get(0);
		}
	}

}
