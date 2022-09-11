/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.28).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.math.BigDecimal;
import io.swagger.model.Order;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T22:37:01.163Z")

@Validated
@Api(value = "orders", description = "the orders API")
@RequestMapping(value = "/orders/")
public interface OrdersApi {

    @ApiOperation(value = "Retorna os pedidos de um usuario", nickname = "ordersUserIdGet", notes = "", response = Order.class, responseContainer = "List", authorizations = {
        @Authorization(value = "Bearer")
    }, tags={ "orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Sucesso", response = Order.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Sem autorizacao") })
    @RequestMapping(value = "{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> ordersUserIdGet(@ApiParam(value = "ID do usuário",required=true) @PathVariable("userId") Long userId);


    @ApiOperation(value = "Cria um cupom para um usuario", nickname = "ordersUserIdPost", notes = "", authorizations = {
        @Authorization(value = "Bearer")
    }, tags={ "orders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Criado"),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "{userId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> ordersUserIdPost(@ApiParam(value = "",required=true) @PathVariable("userId") BigDecimal userId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Order body);

}
