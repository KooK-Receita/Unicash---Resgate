package io.swagger.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public abstract class Dao {

    @Autowired
    protected EntityManager entityManager;
}
