package io.swagger.migrations;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class MigrationHandler {

    private static final Logger log = LoggerFactory.getLogger(MigrationHandler.class);

    protected final EntityManagerFactory factory;

    public MigrationHandler(EntityManagerFactory factory){
        this.factory = factory;
        Session session = factory.createEntityManager().unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                createOrderTable(connection);
                createOrderProductTable(connection);
            }
        });


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
    protected void createOrderTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS RES_ORDER (" +
                "ORDER_ID SERIAL PRIMARY KEY, " +
                "CREATED_AT TIMESTAMP NOT NULL, " +
                "SHOP_ID BIGINT NOT NULL, " +
                "USER_ID BIGINT NOT NULL, " +
                "COUPON_CODE VARCHAR(8) NOT NULL, " +
                "ACTIVE BOOLEAN NOT NULL, " +
                "VALID_UNTIL TIMESTAMP NOT NULL, " +
                "TOTAL DECIMAL(10, 2) NOT NULL" +
                ");";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }

    protected void createOrderProductTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS RES_ORDER_PRODUCT (" +
                "ORDER_PRODUCT_ID SERIAL PRIMARY KEY, " +
                "ORDER_ID BIGINT, PRODUCT_ID BIGINT, " +
                "PRODUCT_QUANTITY  INTEGER NOT NULL, " +
                "PRODUCT_PRICE DECIMAL(10, 2) NOT NULL "+
                " ); ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
