package by.teachmeskills.dzeviatsen.models;

import java.util.Comparator;

public abstract class Show {

    private static class ComparatorByName implements Comparator<Show> {

        @Override
        public int compare(Show o1, Show o2) {
            return o1.getName().compareTo(o2.getName());
        }

    }

    private static class ComparatorByYear implements Comparator<Show> {

        @Override
        public int compare(Show o1, Show o2) {
            return Integer.compare(o1.getProductionYear(), o2.getProductionYear());
        }
    }

    private static class ComparatorByRating implements Comparator<Show> {

        @Override
        public int compare(Show o1, Show o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    }

    private static class ComparatorByVotes implements Comparator<Show> {

        @Override
        public int compare(Show o1, Show o2) {
            return Integer.compare(o1.numOfVotes, o2.numOfVotes);
        }
    }

    private String name;
    private int productionYear;
    private String country;
    private double rating;
    private int numOfVotes;


    public String getName() {
        return name;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String getCountry() {
        return country;
    }

    public double getRating() {
        return rating;
    }

    public int getNumOfVotes() {
        return numOfVotes;
    }

    public static final Comparator<Show> BY_NAME = new ComparatorByName();
    public static final Comparator<Show> BY_YEAR = new ComparatorByYear();
    public static final Comparator<Show> BY_RATING = new ComparatorByRating();
    public static final Comparator<Show> BY_VOTES = new ComparatorByVotes();
}
