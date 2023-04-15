package by.teachmeskills.dzeviatsen.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public abstract class Show {
    private static class PredicateByVotes implements Predicate<Show> {
        private final int numFrom;
        private final int numTo;

        public PredicateByVotes(String numFrom, String numTo) {
            this.numFrom = Integer.valueOf(numFrom);
            this.numTo = Integer.valueOf(numTo);
        }

        public boolean test(Show show) {
            return show.getNumOfVotes() > numFrom && show.getNumOfVotes() < numTo;
        }
    }

    private static class PredicateByCountry implements Predicate<Show> {
        private final Pattern pattern;

        private PredicateByCountry(String query) {
            this.pattern = Pattern.compile("\\b" + Pattern.quote(query),
                    Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        }

        @Override
        public boolean test(Show show) {
            return pattern.matcher(show.getCountry().toString()).find();
        }
    }

    private static class PredicateByRating implements Predicate<Show> {
        private final BigDecimal rateFrom;
        private final BigDecimal rateTo;

        private PredicateByRating(String rateFrom, String rateTo) {
            this.rateFrom = BigDecimal.valueOf(Double.parseDouble(rateFrom));
            this.rateTo = BigDecimal.valueOf(Double.parseDouble(rateTo));
        }

        @Override
        public boolean test(Show show) {
            return show.getRating().compareTo(rateTo) == -1 && show.getRating().compareTo(rateFrom) == 1;
        }
    }


    private static class PredicateByYear implements Predicate<Show> {
        private final Pattern pattern;

        private PredicateByYear(String query) {
            this.pattern = Pattern.compile("\\b" + Pattern.quote(query),
                    Pattern.UNICODE_CHARACTER_CLASS | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        }

        @Override
        public boolean test(Show show) {
            return pattern.matcher(String.valueOf(show.productionYear)).find();
        }
    }

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
            return o1.rating.compareTo(o2.rating);
        }
    }

    private static class ComparatorByVotes implements Comparator<Show> {

        @Override
        public int compare(Show o1, Show o2) {
            return o1.numOfVotes - o2.numOfVotes;
        }
    }

    public static List<Comparator> getComparatorsList() {
        List<Comparator> comparators = new ArrayList<>();
        comparators.add(new ComparatorByVotes());
        comparators.add(new ComparatorByRating());
        comparators.add(new ComparatorByName());
        comparators.add(new ComparatorByYear());
        return comparators;
    }

    private static class TwoFactorComparator {
        public void sorter() {
            List<Comparator> comparators = getComparatorsList();
            for (Comparator comparator : comparators) {
                Comparator<Show> tt = comparator.thenComparing(comparator);
            }

        }
    }

    public String name;
    public int productionYear;
    public String country;
    public BigDecimal rating;
    public int numOfVotes;

    public String getName() {
        return name;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String getCountry() {
        return country;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public int getNumOfVotes() {
        return numOfVotes;
    }

    public static Predicate<Show> predicateByVotes(String numFrom, String numTo) {
        return new PredicateByVotes(numFrom, numTo);
    }

    public static Predicate<Show> predicateByRate(String rateFrom, String rateTo) {
        return new PredicateByRating(rateFrom, rateTo);
    }

    public static Predicate<Show> predicateByYear(String query) {
        return new PredicateByYear(query);
    }

    public static Predicate<Show> predicateByCountry(String query) {
        return new PredicateByCountry(query);
    }

    public static final Comparator<Show> BY_NAME = new ComparatorByName();
    public static final Comparator<Show> BY_YEAR = new ComparatorByYear();
    public static final Comparator<Show> BY_RATING = new ComparatorByRating();
    public static final Comparator<Show> BY_VOTES = new ComparatorByVotes();
}
