package edu.elpeanuto.tms.servies.dto;

import edu.elpeanuto.tms.model.enums.ProductType;

/**
 * Product filter DTO class which carries data between JSP page and ProductDAO
 */
public class ProductFilterDTO {
    public enum HOTEL_TYPE {All, Resort, Motel, Hostel, Timeshare}
    public enum CATEGORY {All, Rest, Excursion, Shopping}

    private CATEGORY category;
    private HOTEL_TYPE hotelType;
    private ProductType type;
    private String searchPattern;
    private Integer numberOfTourists;
    private Integer minPrice;
    private Integer maxPrice;

    public ProductFilterDTO(CATEGORY category, HOTEL_TYPE hotelType, String searchPattern, Integer numberOfTourists, Integer minPrice, Integer maxPrice) {
        this.category = category;
        this.hotelType = hotelType;
        this.searchPattern = searchPattern;
        this.numberOfTourists = numberOfTourists;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public HOTEL_TYPE getHotelType() {
        return hotelType;
    }

    public void setHotelType(HOTEL_TYPE hotelType) {
        this.hotelType = hotelType;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getNumberOfTourists() {
        return numberOfTourists;
    }

    public void setNumberOfTourists(Integer numberOfTourists) {
        this.numberOfTourists = numberOfTourists;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "ProductFilterDTO{" +
                "category=" + category +
                ", hotelType=" + hotelType +
                ", searchPattern='" + searchPattern + '\'' +
                ", numberOfTourists=" + numberOfTourists +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
