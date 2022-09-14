package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponseDTO {

    @JsonProperty("productId")
    public Long productId;

    @JsonProperty("quantity")
    public Integer quantity;

    @JsonProperty("price")
    public Double price;


    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Product product) {
        this.productId = product.getProductId();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
