package io.swagger.dao;

import io.swagger.model.Order;
import io.swagger.model.Product;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.*;
import java.util.List;

@Component
public class OrderDao extends Dao<Order> {

    protected OrderDao(EntityManagerFactory factory) {
        super(factory);
    }

    public List<Order> getOrderByUser(Long userId) {
        String hql = "SELECT DISTINCT ordr FROM Order ordr " +
                "JOIN FETCH ordr.products products " +
                " WHERE ordr.userId = :userId";

        List<Order> resultList = null;
        EntityManager entityManager = factory.createEntityManager();
        try {
            Query query = entityManager.createQuery(hql);
            query.setParameter("userId", userId);
            resultList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return resultList;
    }

    public Order createOrder(Order order) throws SQLException {
        Session session = factory.createEntityManager().unwrap(Session.class);

        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    connection.setAutoCommit(false);

                    insertOrder(connection, order);
                    insertOrderProduct(connection, order);
                    connection.commit();
                } catch (Exception e) {
                    System.out.println("Houve um erro ao criar o pedido");
                    e.printStackTrace();
                    connection.rollback();
                    order.setOrderId(null);
                } finally {
                    session.close();
                }

            }
        });
        if (order == null || order.getOrderId() == null) {
            throw new SQLException("Nao foi possivel criar o pedido");
        }
        return order;
    }

    private void insertOrder(Connection connection, Order order) throws Exception {
        String orderSql =
                "INSERT INTO RES_ORDER (COUPON_CODE, SHOP_ID, USER_ID, TOTAL, CREATED_AT, VALID_UNTIL, ACTIVE)  " +
                        "VALUES (?,?,?,?,?,?,?)";


        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;

        try {
            preparedStatement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getCouponCode());
            preparedStatement.setLong(2, order.getShopId());
            preparedStatement.setLong(3, order.getUserId());
            preparedStatement.setDouble(4, order.getTotal());
            preparedStatement.setDate(5, new Date(order.getCreatedAt().getTime()));
            preparedStatement.setDate(6, new Date(order.getValidUntil().getTime()));
            preparedStatement.setBoolean(7, order.isActive());

            int affectRows = preparedStatement.executeUpdate();
            if (affectRows == 0) {
                throw new Exception("Erro ao criar Pedido");
            }

            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys != null) {
                while (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getLong(1));
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void insertOrderProduct(Connection connection, Order order) throws Exception {
        String orderSql =
                "INSERT INTO RES_ORDER_PRODUCT (ORDER_ID, PRODUCT_ID, PRODUCT_QUANTITY, PRODUCT_PRICE)  " +
                        "VALUES (?,?,?,?)";


        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(orderSql);
            for (Product product : order.getProducts()) {
                preparedStatement.setLong(1, order.getOrderId());
                preparedStatement.setLong(2, product.getProductId());
                preparedStatement.setInt(3, product.getQuantity());
                preparedStatement.setDouble(4, product.getPrice());

                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();


        } catch (Exception e) {
            System.out.println("Houve um erro ao inserir o pedido");
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public boolean deleteOrder(Long orderId, Long userId) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        boolean deleted = false;
        try {
            transaction.begin();
            Query query = entityManager.createQuery(
                    "DELETE FROM Order ordr WHERE ordr.orderId = :orderId");

            query.setParameter("orderId", orderId);

            deleted = query.executeUpdate() > 0;
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return deleted;
    }

    public Order findOrder(Long orderId, Long userId) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT ordr FROM Order ordr " +
                        "JOIN FETCH ordr.products products " +
                        "WHERE ordr.orderId = :orderId AND ordr.userId = :userId");
        query.setParameter("orderId", orderId);
        query.setParameter("userId", userId);

        Order order = (Order) query.getSingleResult();
        entityManager.close();

        return order;
    }
}
