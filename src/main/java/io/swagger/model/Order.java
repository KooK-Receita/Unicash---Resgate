package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T19:42:49.684Z")


public class Order   {
  @JsonProperty("orderId")
  private BigDecimal orderId = null;

  @JsonProperty("products")
  @Valid
  private List<Product> products = null;

  @JsonProperty("createdAt")
  private OffsetDateTime createdAt = null;

  @JsonProperty("shopId")
  private Long shopId = null;

  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("couponCode")
  private String couponCode = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("validUntil")
  private OffsetDateTime validUntil = null;

  @JsonProperty("total")
  private Float total = null;

  public Order orderId(BigDecimal orderId) {
    this.orderId = orderId;
    return this;
  }

  /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getOrderId() {
    return orderId;
  }

  public void setOrderId(BigDecimal orderId) {
    this.orderId = orderId;
  }

  public Order products(List<Product> products) {
    this.products = products;
    return this;
  }

  public Order addProductsItem(Product productsItem) {
    if (this.products == null) {
      this.products = new ArrayList<Product>();
    }
    this.products.add(productsItem);
    return this;
  }

  /**
   * Get products
   * @return products
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Order createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Order shopId(Long shopId) {
    this.shopId = shopId;
    return this;
  }

  /**
   * Get shopId
   * @return shopId
  **/
  @ApiModelProperty(value = "")


  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public Order userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Order couponCode(String couponCode) {
    this.couponCode = couponCode;
    return this;
  }

  /**
   * Get couponCode
   * @return couponCode
  **/
  @ApiModelProperty(value = "")


  public String getCouponCode() {
    return couponCode;
  }

  public void setCouponCode(String couponCode) {
    this.couponCode = couponCode;
  }

  public Order active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  **/
  @ApiModelProperty(value = "")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Order validUntil(OffsetDateTime validUntil) {
    this.validUntil = validUntil;
    return this;
  }

  /**
   * Get validUntil
   * @return validUntil
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getValidUntil() {
    return validUntil;
  }

  public void setValidUntil(OffsetDateTime validUntil) {
    this.validUntil = validUntil;
  }

  public Order total(Float total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(value = "")


  public Float getTotal() {
    return total;
  }

  public void setTotal(Float total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.orderId, order.orderId) &&
        Objects.equals(this.products, order.products) &&
        Objects.equals(this.createdAt, order.createdAt) &&
        Objects.equals(this.shopId, order.shopId) &&
        Objects.equals(this.userId, order.userId) &&
        Objects.equals(this.couponCode, order.couponCode) &&
        Objects.equals(this.active, order.active) &&
        Objects.equals(this.validUntil, order.validUntil) &&
        Objects.equals(this.total, order.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, products, createdAt, shopId, userId, couponCode, active, validUntil, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    shopId: ").append(toIndentedString(shopId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    couponCode: ").append(toIndentedString(couponCode)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    validUntil: ").append(toIndentedString(validUntil)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

