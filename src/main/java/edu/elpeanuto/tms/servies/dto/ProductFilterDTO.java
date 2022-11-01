package edu.elpeanuto.tms.servies.dto;

import edu.elpeanuto.tms.servies.exception.EnumParseException;

import java.util.Arrays;

/**
 * Product filter DTO class which carries data between JSP page and ProductDAO
 */
public class ProductFilterDTO {
    public enum HOTEL_TYPE {All, Resort, Motel, Hostel, Timeshare}
    public enum CATEGORY {All, Rest, Excursion, Shopping}

    private String category;
    private String hotelType;
    private String searchPattern;
    private String Type;
    private Integer numberOfTourists;
    private Integer minPrice;
    private Integer maxPrice;

    public ProductFilterDTO(String category, String hotelType, String searchPattern, Integer numberOfTourists, Integer minPrice, Integer maxPrice) {
        this.searchPattern = searchPattern;
        this.numberOfTourists = numberOfTourists;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;

        if (hotelType != null) {
            if (isHotelTypeCorrect(hotelType))
                this.hotelType = hotelType;
            else
                throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        } else {
            this.hotelType = null;
        }

        if (category != null) {
            if (isCategoryCorrect(category))
                this.category = category;
            else
                throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        } else {
            this.category = null;
        }

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "ProductFilterDTO{" +
                "category='" + category + '\'' +
                ", hotelType='" + hotelType + '\'' +
                ", searchPattern='" + searchPattern + '\'' +
                ", Type='" + Type + '\'' +
                ", numberOfTourists=" + numberOfTourists +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }

    private boolean isHotelTypeCorrect(String str) {
        return Arrays.stream(HOTEL_TYPE.values()).anyMatch(v -> v.name().equals(str));
    }

    private boolean isCategoryCorrect(String str) {
        return Arrays.stream(CATEGORY.values()).anyMatch(v -> v.name().equals(str));
    }
}
