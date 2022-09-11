package io.swagger.service;

import io.swagger.dao.Dao;
import io.swagger.dao.OrderDao;
import io.swagger.model.Order;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    public Order createOrder(Order orderToInsert) throws Exception {
        Date createdDate = new Date();
        Date validDate = getCouponValidateDate(createdDate);

        orderToInsert.setCreatedAt(createdDate);
        orderToInsert.setValidUntil(validDate);
        orderToInsert.active(true);
        orderToInsert.setCouponCode(RandomStringUtils.randomAlphanumeric(8).toUpperCase(Locale.ROOT));
        BigDecimal bigDecimalTotal = BigDecimal.valueOf(orderToInsert.getTotal()).setScale(2, RoundingMode.HALF_UP);
        orderToInsert.setTotal(bigDecimalTotal.doubleValue());
        return dao.insertOrder(orderToInsert);
    }


    public void setDao(OrderDao dao) {
        this.dao = dao;
    }
}
