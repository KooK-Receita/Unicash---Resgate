package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Product
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-09-10T19:42:49.684Z")


public class Product   {
  @JsonProperty("productId")
  private Long productId = null;

  @JsonProperty("shopId")
  private Long shopId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("price")
  private Float price = null;

  @JsonProperty("iconUrl")
  private String iconUrl = null;

  public Product productId(Long productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  **/
  @ApiModelProperty(value = "")


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Product shopId(Long shopId) {
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

  public Product name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Product price(Float price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(value = "")


  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Product iconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
    return this;
  }

  /**
   * Get iconUrl
   * @return iconUrl
  **/
  @ApiModelProperty(value = "")


  public String getIconUrl() {
    return iconUrl;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.productId, product.productId) &&
        Objects.equals(this.shopId, product.shopId) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.iconUrl, product.iconUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, shopId, name, price, iconUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    shopId: ").append(toIndentedString(shopId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    iconUrl: ").append(toIndentedString(iconUrl)).append("\n");
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

