package io.swagger.migrations;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class MigrationHandler {

    private static final Logger log = LoggerFactory.getLogger(MigrationHandler.class);

    protected final EntityManagerFactory factory;

    public MigrationHandler(EntityManagerFactory factory){
        this.factory = factory;
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        createOrderTable(entityManager);
        transaction.commit();
    }

    public boolean tableExists(String tableName, String columnName){
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createNativeQuery(
                "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = :tableName AND COLUMN_NAME = :columnName"
        );
        query.setParameter("tableName", tableName);
        query.setParameter("columnName", columnName);

        Object result = query.getSingleResult();

        return result != null;
    }
    protected void createOrderTable(EntityManager entityManger){
        log.debug("CREATING ORDER TABLE");


        String sql = "CREATE TABLE IF NOT EXISTS \"ORDER\" (" +
                "ORDER_ID SERIAL PRIMARY KEY, " +
                "CREATED_AT TIMESTAMP NOT NULL, " +
                "SHOP_ID BIGINT NOT NULL, " +
                "USER_ID BIGINT NOT NULL, " +
                "COUPON_CODE VARCHAR(8) NOT NULL, " +
                "ACTIVE BIT NOT NULL, " +
                "VALID_UNTIL TIMESTAMP NOT NULL, " +
                "TOTAL DECIMAL(6, 2) NOT NULL" +
                ");";

        entityManger.createNativeQuery(sql).executeUpdate();

        log.debug("ORDER TABLE CREATED");

    }
}