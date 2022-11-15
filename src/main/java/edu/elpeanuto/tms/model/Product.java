package edu.elpeanuto.tms.model;

import edu.elpeanuto.tms.model.enums.Bool;
import edu.elpeanuto.tms.model.enums.HotelType;
import edu.elpeanuto.tms.model.enums.ProductCategory;
import edu.elpeanuto.tms.model.enums.ProductType;

/**
 * Product model class, contains information about tour
 */
public class Product extends Entity<Product> {
    private Long id;
    private String name;
    private String description;
    private ProductCategory category;
    private ProductType type;
    private Integer price;
    private Bool active;
    private String hotelName;
    private HotelType hotelType;
    private String arrivalDate;
    private String departureDate;
    private String arrivalPlace;
    private String departurePlace;
    private String country;
    private String city;
    private Bool foodInPrice;
    private Bool flightInPrice;
    private Integer amountOfDays;
    private Integer numberOfTourists;

    public Product() {

    }

    public Product(Long id, String name, String description, ProductCategory category, ProductType type, Integer price,
                   Bool active, String hotelName, HotelType hotelType, String arrivalDate, String departureDate, String arrivalPlace,
                   String departurePlace, String country, String city, Bool foodInPrice, Bool flightInPrice, Integer amountOfDays, Integer numberOfTourists) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.price = price;
        this.active = active;
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.arrivalPlace = arrivalPlace;
        this.departurePlace = departurePlace;
        this.country = country;
        this.city = city;
        this.foodInPrice = foodInPrice;
        this.flightInPrice = flightInPrice;
        this.amountOfDays = amountOfDays;
        this.numberOfTourists = numberOfTourists;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Bool getActive() {
        return active;
    }

    public void setActive(Bool active) {
        this.active = active;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
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

    public Bool getFoodInPrice() {
        return foodInPrice;
    }

    public void setFoodInPrice(Bool foodInPrice) {
        this.foodInPrice = foodInPrice;
    }

    public Bool getFlightInPrice() {
        return flightInPrice;
    }

    public void setFlightInPrice(Bool flightInPrice) {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", type=" + type +
                ", price=" + price +
                ", active=" + active +
                ", hotelName='" + hotelName + '\'' +
                ", hotelType=" + hotelType +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalPlace='" + arrivalPlace + '\'' +
                ", departurePlace='" + departurePlace + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", foodInPrice=" + foodInPrice +
                ", flightInPrice=" + flightInPrice +
                ", amountOfDays=" + amountOfDays +
                ", numberOfTourists=" + numberOfTourists +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return this.type.name().length() - o.getType().name().length();
    }
}
