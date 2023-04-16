package hw27.models;

import java.math.BigDecimal;

public class Film extends Show {
    private final String name;
    private final int productionYear;
    private final BigDecimal rating;
    private final String country;
    private final int numOfVotes;

    public Film(String name, int productionYear, String country, BigDecimal rating, int numOfVotes) {
        validateName(name);
        validateRating(rating);
        validateYear(productionYear);
        validateCountry(country);
        validateNumOfVotes(numOfVotes);
        this.name = name;
        this.productionYear = productionYear;
        this.country = country;
        this.rating = rating;
        this.numOfVotes = numOfVotes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getProductionYear() {
        return productionYear;
    }

    @Override
    public BigDecimal getRating() {
        return rating;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public int getNumOfVotes() {
        return numOfVotes;
    }

    public static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }
    }

    public static void validateYear(int year) {
        if (year < 0 || year > 2023) {
            throw new IllegalArgumentException("Year must be positive");
        }
    }

    public static void validateRating(BigDecimal rating) {
        if (rating.compareTo(BigDecimal.ZERO) == -1
                || rating.compareTo(BigDecimal.TEN) == 1) {
            throw new IllegalArgumentException("Rating must have value between 0  and 10");
        }
    }

    public static void validateCountry(String countryCode) {
        if (countryCode.length() != 2) {
            throw new IllegalArgumentException("Country code must contain 2 letters");
        }
    }

    public static void validateNumOfVotes(int numOfVotes) {
        if (numOfVotes < 0) {
            throw new IllegalArgumentException("Number of votes must be over -1");
        }
    }

    public String toString() {
        return "Show{" +
                "name= " + getName() +
                ", productionYear= " + getProductionYear() +
                ", country= " + getCountry() +
                ", rating= " + getRating() +
                ", numOfVotes= " + getNumOfVotes() +
                '}';
    }
}
