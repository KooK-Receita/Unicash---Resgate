package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderResponseDTO {

    @JsonProperty("orderId")
    public Long orderId;

    @JsonProperty("shopId")
    public Long shopId;

    @JsonProperty("userId")
    public Long userId;

    @JsonProperty("couponCode")
    public String couponCode;

    @JsonProperty("total")
    public Double total;

    @JsonProperty("createdAt")
    public Date createdAt;

    @JsonProperty("validUtil")
    public Date validUntil;

    @JsonProperty("active")
    public Boolean active;

    @JsonProperty("products")
    public List<ProductResponseDTO> products = new ArrayList<>();


    public OrderResponseDTO(Order order) {
        this.orderId = order.getOrderId();
        this.active = order.isActive();
        this.couponCode = order.getCouponCode();
        this.createdAt = order.getCreatedAt();
        this.validUntil = order.getValidUntil();
        this.shopId = order.getShopId();
        this.total = order.getTotal();
        this.userId = order.getUserId();

        for (Product product : order.getProducts()) {
            this.products.add(new ProductResponseDTO(product));
        }
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }
}
