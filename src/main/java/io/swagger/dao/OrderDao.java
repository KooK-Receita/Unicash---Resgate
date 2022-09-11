package io.swagger.dao;

import io.swagger.model.Order;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.sql.*;

@Component
public class OrderDao extends Dao<Order>{

    protected OrderDao(EntityManagerFactory factory) {
        super(factory);
    }

    public Order insertOrder(Order order) throws Exception {
        Session session = factory.createEntityManager().unwrap(Session.class);

        String sql =
                "INSERT INTO \"ORDER\" (COUPON_CODE, SHOP_ID, USER_ID, TOTAL, CREATED_AT, VALID_UNTIL, ACTIVE)  " +
                        "VALUES (?,?,?,?,?,?,?)";

        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = null;
                ResultSet generatedKeys = null;

                try {
                    preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, order.getCouponCode());
                    preparedStatement.setLong(2, order.getShopId());
                    preparedStatement.setLong(3, order.getUserId());
                    preparedStatement.setDouble(4, order.getTotal());
                    preparedStatement.setDate(5, new Date(order.getCreatedAt().getTime()));
                    preparedStatement.setDate(6, new Date(order.getValidUntil().getTime()));
                    preparedStatement.setBoolean(7, order.isActive());

                    int affectRows = preparedStatement.executeUpdate();
                    if (affectRows == 0){
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
                } finally {
                    if (generatedKeys != null) {
                        generatedKeys.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    session.close();
                }

            }
        });
        if (order == null || order.getOrderId() == null){
            throw new Exception("Nao foi possivel criar o pedido");
        }
        return order;
    }
}
