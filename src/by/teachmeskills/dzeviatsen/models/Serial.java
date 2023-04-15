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
        validateName(name);
        validateRating(rating);
        validateYear(productionYear);
        validateCountry(country);
        validateNumOfVotes(numOfVotes);
        validateNumOfVotes(lastEpisodeYear);
        validateNumOfSeasonsAndEpisodes(numOfEpisodes);
        validateNumOfSeasonsAndEpisodes(numOfSeasons);
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

    public static void validateNumOfSeasonsAndEpisodes(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Number of episodes/seasons must be over 0");
        }
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
