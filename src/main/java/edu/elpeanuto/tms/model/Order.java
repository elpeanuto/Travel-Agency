package edu.elpeanuto.tms.model;

import edu.elpeanuto.tms.model.enums.Gender;
import edu.elpeanuto.tms.model.enums.OrderStatus;

/**
 * Order model class, contains information about order
 */
public class Order extends Entity<Order> {
    Long id;
    Long userId;
    Long productId;
    OrderStatus status;
    String date;
    String name;
    String phoneNumber;
    String email;
    String realName;
    String realSurName;
    Gender gender;
    String dateOfBirth;
    String citizenship;
    String passportSerial;
    String passportNumber;
    String passportValidDate;
    Integer totalPrice;

    public Order(Long id, Long userId, Long productId, OrderStatus status, String date, String name, String phoneNumber,
                 String email, String realName, String realSurName, Gender gender, String dateOfBirth, String citizenship,
                 String passportSerial, String passportNumber, String passportValidDate, Integer totalPrice) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.status = status;
        this.date = date;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.realName = realName;
        this.realSurName = realSurName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.passportValidDate = passportValidDate;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealSurName() {
        return realSurName;
    }

    public void setRealSurName(String realSurName) {
        this.realSurName = realSurName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportValidDate() {
        return passportValidDate;
    }

    public void setPassportValidDate(String passportValidDate) {
        this.passportValidDate = passportValidDate;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", status=" + status +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                ", realSurName='" + realSurName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", passportSerial='" + passportSerial + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportValidDate='" + passportValidDate + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return 0;
    }
}
