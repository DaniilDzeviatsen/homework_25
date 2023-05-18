package by.teachmeskills.dzeviatsen.Predicators;

import by.teachmeskills.dzeviatsen.models.Show;

import java.util.function.Predicate;

public class PredicateByRating implements Predicate<Show> {
    private final double rateFrom;
    private final double rateTo;

    public PredicateByRating(double rateFrom, double rateTo) {
        this.rateFrom = rateFrom;
        this.rateTo = rateTo;
    }

    @Override
    public boolean test(Show show) {
        return show.getRating() >= rateFrom && show.getRating() <= rateTo;
    }
}
