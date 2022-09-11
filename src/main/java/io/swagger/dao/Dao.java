package io.swagger.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;


@Component
public class Dao<T> {

    @Autowired
    protected final EntityManagerFactory factory;

    protected Dao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void execute(String sql, HashMap<String, Object> params, Boolean localTransaction){
        buildQuery(sql, params, localTransaction).executeUpdate();
    }

    public void execute(String sql, HashMap<String, Object> params){
        buildQuery(sql, params, true).executeUpdate();
    }
    public List<T> getList(String sql, HashMap<String, Object> params, Boolean localTransaction){
        return (List<T>) buildQuery(sql, params, localTransaction).getResultList();
    }

    public List<T> getList(String sql, HashMap<String, Object> params){
        return (List<T>) buildQuery(sql, params, true).getResultList();
    }

    public T getSingleResult(String sql, HashMap<String, Object> params, Boolean localTransaction){
        return (T) buildQuery(sql, params, localTransaction).getSingleResult();
    }

    public T getSingleResult(String sql, HashMap<String, Object> params){
        return (T) buildQuery(sql, params, true).getSingleResult();
    }
    protected Query buildQuery(String sql, HashMap<String, Object> params){
        return buildQuery(sql, params, true);
    }
    protected Query buildQuery(String sql, HashMap<String, Object> params, Boolean localTransaction) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        if (localTransaction) {
            transaction.begin();
        }
        Query query = entityManager.createNativeQuery(sql);
        try {
            if (params != null){
                for (String key : params.keySet()) {
                    Object value = params.get(key);
                    if (value != null) {
                        query.setParameter(key, value);
                    }
                }
            }


        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (transaction.isActive()) {
                transaction.commit();
            }
        }
        return query;

    }

    protected Query buildQuery(String sql, Boolean localTransaction) {
        return buildQuery(sql, null, localTransaction);
    }

    }
