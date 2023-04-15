package by.teachmeskills.dzeviatsen.models;

import java.math.BigDecimal;

public class Film extends Show {
    private final String name;
    private final int productionYear;
    private final BigDecimal rating;
    private final String country;
    private final int numOfVotes;

    public Film(String name, int productionYear, String country, BigDecimal rating, int numOfVotes) {
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
