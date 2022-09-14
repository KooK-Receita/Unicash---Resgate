package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {

    @JsonProperty(value = "products", required = true)
    public List<Product> products = new ArrayList<>();

    @JsonProperty(value = "shopId", required = true)
    public Long shopId;


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
