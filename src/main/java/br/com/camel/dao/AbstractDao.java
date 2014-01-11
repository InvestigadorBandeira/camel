package br.com.camel.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDao<T> {

    protected final EntityManager em;

    private final Class<T> persistent;

    public AbstractDao(EntityManager em) {

	ParameterizedType type = (ParameterizedType) getClass()
		.getGenericSuperclass();

	this.persistent = (Class<T>) type.getActualTypeArguments()[0];

	this.em = em;
    }

    protected void begin() {
    }

    protected void commit() {
    }

    public void save(T t) {
	em.persist(t);
    }

    public void delete(T t) {
	em.remove(update(t));
    }

    public T update(T t) {
	return em.merge(t);
    }

    public T findById(Long id) {
	return em.find(persistent, id);
    }

    public List<T> findAll() {
	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
	cq.select(cq.from(persistent));
	return em.createQuery(cq).getResultList();
    }

}
