package by.teachmeskills.dzeviatsen.models;

import java.math.BigDecimal;

public class Film extends Show {
    public Film(String name, int productionYear, String country, BigDecimal rating, int numOfVotes) {

    }
    public String toString() {
        return "Show{" +
                "name='" + name + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", country='" + country + '\'' +
                ", rating=" + rating +
                ", numOfVotes=" + numOfVotes +
                '}';
    }
}
