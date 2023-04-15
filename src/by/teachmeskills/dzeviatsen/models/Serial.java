package by.teachmeskills.dzeviatsen.models;

import java.math.BigDecimal;

public class Serial extends Show {
    private final String name;
    private final int productionYear;
    private final int lastEpisodeYear;
    private final int numOfEpisodes;
    private final int numOfSeasons;
    private final String country;
    private final BigDecimal rating;
    private final int numOfVotes;

    public Serial(String name, int productionYear, String country, BigDecimal rating, int numOfVotes, int lastEpisodeYear, int numOfEpisodes, int numOfSeasons) {
        this.name = name;
        this.productionYear = productionYear;
        this.country = country;
        this.rating = rating;
        this.numOfVotes = numOfVotes;
        this.lastEpisodeYear = lastEpisodeYear;
        this.numOfEpisodes = numOfEpisodes;
        this.numOfSeasons = numOfSeasons;
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
    public String getCountry() {
        return country;
    }

    @Override
    public BigDecimal getRating() {
        return rating;
    }

    @Override
    public int getNumOfVotes() {
        return numOfVotes;
    }

    public int getLastEpisodeYear() {
        return lastEpisodeYear;
    }

    public int getNumOfEpisodes() {
        return numOfEpisodes;
    }

    public int getNumOfSeasons() {
        return numOfSeasons;
    }

    public String toString() {
        return "Show{" +
                "name= " + getName() +
                ", productionYear= " + getProductionYear() +
                ", country= " + getCountry() +
                ", rating= " + getRating() +
                ", numOfVotes= " + getNumOfVotes() +
                ", lastEpisodeYear " + getLastEpisodeYear() +
                ",numOfEpisodes " + getNumOfEpisodes() +
                ",numOfSeasons " + getNumOfSeasons() +
                '}';
    }
}
