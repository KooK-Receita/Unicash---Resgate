package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Order;
import io.swagger.model.OrderResponseDTO;
import io.swagger.service.OrderService;
import io.swagger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T22:37:01.163Z")

@Controller
public class OrderApiController implements OrderApi {

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> orderOrderIdDelete(@ApiParam(value = "Id do pedido a ser deletado", required = true) @PathVariable("orderId") Long orderId) {
        HashSet<String> errors = new HashSet<>();
        try {
            boolean deleted = orderService.deleteOrder(orderId, userService.getUserIdByToken(""));
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                errors.add("This order doesn't exists or you are unabled to delete it");
            }
            return new ResponseEntity<>(new ApiError(errors), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> orderOrderIdGet(@ApiParam(value = "ID do usuário", required = true) @PathVariable("orderId") Long orderId) {
        try {
            Order order = orderService.findOrderByOrderId(orderId, userService.getUserIdByToken(""));
            if (order != null) {
                return new ResponseEntity<>(new OrderResponseDTO(order), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> orderOrderIdPut(@ApiParam(value = "", required = true) @PathVariable("orderId") Long orderId, @ApiParam(value = "", required = true) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Order> orderCouponValidGet(@ApiParam(value = "Código do cupom",required=true) @PathVariable("couponCode") String couponCode) {
        // Verifica se o cupom existe no banco e é valido
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> orderCouponFinishGet(@ApiParam(value = "Código do cupom",required=true) @PathVariable("couponCode") String couponCode) {
        // Dá baixa no cupom, finalizando o pedido e chama a api para debitar do usuário
        return null;
    }

}
