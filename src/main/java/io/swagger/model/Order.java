package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T19:42:49.684Z")

@Entity
@Table(name = "RES_ORDER")
public class Order   {

  @Id
  @GeneratedValue
  @Column(name = "ORDER_ID")
  @JsonProperty("orderId")
  private Long orderId = null;

  @ManyToMany
  @JoinTable(
          name = "RES_ORDER_PRODUCT",
          joinColumns = { @JoinColumn(name = "ORDER_ID") },
          inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID")}
  )
  @JsonProperty("products")
  @Valid
  private List<Product> products = new ArrayList<>();

  @Column(name = "CREATED_AT")
  @JsonProperty("createdAt")
  private Date createdAt = null;

  @JsonProperty("shopId")
  private Long shopId = null;

  @JsonProperty("userId")
  private Long userId = null;

  @Column(name = "COUPON_CODE", nullable = false)
  @JsonProperty("couponCode")
  private String couponCode = null;

  @Column(name = "ACTIVE")
  @JsonProperty("active")
  private Boolean active = null;

  @Column(name = "VALID_UNTIL")
  @JsonProperty("validUntil")
  private Date validUntil = null;

  @Column(name = "TOTAL")
  @JsonProperty("total")
  private Double total = null;

  public Order() {
  }

  public Order(CreateOrderDTO orderDTO) {
      this.products = new ArrayList<>(orderDTO.products);
      this.shopId = orderDTO.shopId;
  }

  public Order orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  /**
   * Get orderId
   * @return orderId
  **/
  @ApiModelProperty(value = "")
  @Valid
  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
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

  public Order createdAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
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

  public Order validUntil(Date validUntil) {
    this.validUntil = validUntil;
    return this;
  }

  /**
   * Get validUntil
   * @return validUntil
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Date getValidUntil() {
    return validUntil;
  }

  public void setValidUntil(Date validUntil) {
    this.validUntil = validUntil;
  }

  public Order total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(value = "")


  public Double getTotal() {

      if (products != null && !products.isEmpty()){
        double total = 0;
        for (Product product: products){
          total += product.getPrice() * product.getQuantity();
        }

        return total;
      }
      return 0d;
  }

  public void setTotal(Double total) {
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

