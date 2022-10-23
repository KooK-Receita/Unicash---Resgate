package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.dao.OrderDao;
import io.swagger.model.CreateOrderDTO;
import io.swagger.model.Order;
import io.swagger.model.Product;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderDao dao;

    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public Date getCouponValidateDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 10);
        return calendar.getTime();
    }

    private void validateOrderToInsert(CreateOrderDTO orderDTO) throws ApiException {
        HashSet<String> erros = new HashSet<>();
        int index = 0;
        for (Product product : orderDTO.products) {
            String productIdentification = "Product[" + index + "] ";
            if (product == null){
                erros.add(productIdentification + " is null");
                continue;
            }
            if (product.getProductId() == null){
                erros.add(productIdentification + "ID is required");
            }

            if (product.getQuantity() == null || product.getQuantity() <= 0){
                erros.add(productIdentification + "Quantity is required and must be bigger than 0");
            }
            index++;
        }

        if (!erros.isEmpty()){
            throw new ApiException(erros);
        }
    }

    public Order createOrder(CreateOrderDTO orderToInsert) throws ApiException, SQLException {
        Order order = new Order(orderToInsert);
        order.setUserId(1L);


        validateOrderToInsert(orderToInsert);

        Date createdDate = new Date();
        Date validDate = getCouponValidateDate(createdDate);

        order.setCreatedAt(createdDate);
        order.setValidUntil(validDate);
        order.active(true);
        order.setCouponCode(RandomStringUtils.randomAlphanumeric(8).toUpperCase(Locale.ROOT));
        BigDecimal bigDecimalTotal = BigDecimal.valueOf(order.getTotal()).setScale(2, RoundingMode.HALF_UP);
        order.setTotal(bigDecimalTotal.doubleValue());
        return dao.createOrder(order);
    }



    public void setDao(OrderDao dao) {
        this.dao = dao;
    }
}
