package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.CreateOrderDTO;
import io.swagger.model.Order;
import io.swagger.model.OrderResponseDTO;
import io.swagger.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T22:37:01.163Z")

@Controller
public class OrdersApiController implements OrdersApi {

    private static final Logger log = LoggerFactory.getLogger(OrdersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private OrderService orderService;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    public ResponseEntity<List<OrderResponseDTO>> ordersUserGet() {
        List<Order> orders = orderService.getOrderByUser(1l);
        List<OrderResponseDTO> responseDTOS = new ArrayList<>();

        for (Order order : orders) {
            responseDTOS.add(new OrderResponseDTO(order));
        }
        return new ResponseEntity<List<OrderResponseDTO>>(responseDTOS, HttpStatus.OK);

    }

    public ResponseEntity<?> ordersUserPost(@Valid @RequestBody CreateOrderDTO body) {
        HashSet<String> errors = new HashSet<>();
        if (body != null){
            try {
                if (body.products == null) {
                    errors.add("Products are required");
                }

                if (body.shopId == null) {
                    errors.add("ShopId is required");
                }

                if (!errors.isEmpty()) {
                    return new ResponseEntity<ApiError>(new ApiError(errors), HttpStatus.BAD_REQUEST);
                }

                Order order = orderService.createOrder(body);
                return new ResponseEntity<Order>(order, HttpStatus.CREATED);
            } catch (ApiException ae){
                return new ResponseEntity<ApiError>(new ApiError(ae.erros), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<String>("Teste erro", HttpStatus.BAD_REQUEST);
    }
}
