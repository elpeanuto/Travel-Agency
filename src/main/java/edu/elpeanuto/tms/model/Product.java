package edu.elpeanuto.tms.model;

import edu.elpeanuto.tms.model.enums.Bool;
import edu.elpeanuto.tms.model.enums.HotelType;
import edu.elpeanuto.tms.model.enums.ProductCategory;
import edu.elpeanuto.tms.model.enums.ProductType;
import edu.elpeanuto.tms.servies.exception.EnumParseException;

import java.util.Arrays;

/**
 * Product model class, contains information about tour
 */
public class Product extends Entity<Product> {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String type;
    private Integer price;
    private String active;
    private String hotelName;
    private String hotelType;
    private String arrivalDate;
    private String departureDate;
    private String arrivalPlace;
    private String departurePlace;
    private String country;
    private String city;
    private String foodInPrice;
    private String flightInPrice;
    private Integer amountOfDays;
    private Integer numberOfTourists;

    public Product() {

    }

    public Product(Long id, String name, String description, String category, String type, Integer price, String active,
                   String hotelName, String hotelType, String arrivalDate, String departureDate, String arrivalPlace,
                   String departurePlace, String country, String city, String foodInPrice, String flightInPrice,
                   Integer amountOfDays, Integer numberOfTourists) throws EnumParseException {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hotelName = hotelName;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.arrivalPlace = arrivalPlace;
        this.departurePlace = departurePlace;
        this.country = country;
        this.city = city;
        this.amountOfDays = amountOfDays;
        this.numberOfTourists = numberOfTourists;

        if (isCategoryCorrect(category)) {
            this.category = category;
        } else {
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        }
        if (isTypeCorrect(type)) {
            this.type = type;
        } else {
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        }
        if (isHotelTypeCorrect(hotelType)) {
            this.hotelType = hotelType;
        } else {
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        }
        if (isBooleanCorrect(active)) {
            this.active = active;
        } else {
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        }
        if (isBooleanCorrect(foodInPrice)) {
            this.foodInPrice = foodInPrice;
        } else {
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        }
        if (isBooleanCorrect(flightInPrice)) {
            this.flightInPrice = flightInPrice;
        } else {
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFoodInPrice() {
        return foodInPrice;
    }

    public void setFoodInPrice(String foodInPrice) {
        this.foodInPrice = foodInPrice;
    }

    public String getFlightInPrice() {
        return flightInPrice;
    }

    public void setFlightInPrice(String flightInPrice) {
        this.flightInPrice = flightInPrice;
    }

    public Integer getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(Integer amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public Integer getNumberOfTourists() {
        return numberOfTourists;
    }

    public void setNumberOfTourists(Integer numberOfTourists) {
        this.numberOfTourists = numberOfTourists;
    }

    private boolean isHotelTypeCorrect(String str) {
        return Arrays.stream(HotelType.values()).anyMatch(v -> v.name().equals(str));
    }

    private boolean isCategoryCorrect(String str) {
        return Arrays.stream(ProductCategory.values()).anyMatch(v -> v.name().equals(str));
    }

    private boolean isTypeCorrect(String str) {
        return Arrays.stream(ProductType.values()).anyMatch(v -> v.name().equals(str));
    }

    private boolean isBooleanCorrect(String str) {
        return Arrays.stream(Bool.values()).anyMatch(v -> v.name().equals(str));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", active='" + active + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelType='" + hotelType + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalPlace='" + arrivalPlace + '\'' +
                ", departurePlace='" + departurePlace + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", foodInPrice='" + foodInPrice + '\'' +
                ", flightInPrice='" + flightInPrice + '\'' +
                ", amountOfDays=" + amountOfDays +
                ", numberOfTourists=" + numberOfTourists +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return this.type.length() - o.getType().length();
    }
}
