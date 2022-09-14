package io.swagger.service;

import io.swagger.dao.OrderDao;
import io.swagger.model.CreateOrderDTO;
import io.swagger.model.Order;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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

    public Order createOrder(CreateOrderDTO orderToInsert) throws Exception {
        Order order = new Order(orderToInsert);
        if (Objects.equals(System.getenv("AMBIENTE"), "DEV")){
            order.setUserId(1L);
        }
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
