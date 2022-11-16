package edu.elpeanuto.tms.servies.dto;

import edu.elpeanuto.tms.model.Order;

/**
 * Order DTO class which carries data between processes
 */
public class OrderDTO {
    private Order order;
    private String productName;
    private String productCountry;
    private String productCity;
    private Integer totalPrice;

    public OrderDTO() {

    }

    public OrderDTO(Order order, String productName, String productCountry, String productCity, Integer totalPrice) {
        this.order = order;
        this.productName = productName;
        this.productCountry = productCountry;
        this.productCity = productCity;
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCountry() {
        return productCountry;
    }

    public void setProductCountry(String productCountry) {
        this.productCountry = productCountry;
    }

    public String getProductCity() {
        return productCity;
    }

    public void setProductCity(String productCity) {
        this.productCity = productCity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getOrderId(){
        return order.getId();
    }

    public String getOrderStatus(){
        return order.getStatus().name();
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "order=" + order +
                ", productName='" + productName + '\'' +
                ", productCountry='" + productCountry + '\'' +
                ", productCity='" + productCity + '\'' +
                ", productPrice=" + totalPrice +
                '}';
    }
}
