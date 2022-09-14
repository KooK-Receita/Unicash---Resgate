package io.swagger.dao;

import io.swagger.model.Order;
import io.swagger.model.Product;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.*;
import java.util.List;

@Component
public class OrderDao extends Dao<Order> {

    protected OrderDao(EntityManagerFactory factory) {
        super(factory);
    }

    public List<Order> getOrderByUser(Long userId) {
        String hql = "SELECT DISTINCT o FROM Order o " +
                "JOIN FETCH o.products products " +
                " WHERE o.userId = :userId";

        List<Order> resultList = null;
        try {
            EntityManager entityManager = factory.createEntityManager();
            Query query = entityManager.createQuery(hql);
            query.setParameter("userId", userId);
            resultList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println("Houve um erro ao inserir o pedido");
            e.printStackTrace();
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
            throw new Exception(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
