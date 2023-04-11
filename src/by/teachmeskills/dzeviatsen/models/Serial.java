package by.teachmeskills.dzeviatsen.models;

import java.math.BigDecimal;

public class Serial extends Show{
    public int lastEpisodeYear;
    public int numOfEpisodes;
    public int numOfSeasons;

    public Serial(String name, int productionYear, String country, BigDecimal rating, int numOfVotes, int lastEpisodeYear, int numOfEpisodes, int numOfSeasons) {
        super();
    }

    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", country='" + country + '\'' +
                ", rating=" + rating +
                ", numOfVotes=" + numOfVotes +'\''+
                ", lastEpisodeYear"+lastEpisodeYear+'\''+
                ",numOfEpisodes"+numOfEpisodes+'\''+
                ",numOfSeasons"+
                '}';
    }
}
