package by.teachmeskills.dzeviatsen.Predicators;

import by.teachmeskills.dzeviatsen.models.Show;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PredicateByTitle implements Predicate<Show> {
    private final Pattern pattern;

    public PredicateByTitle(String query) {
        this.pattern = Pattern.compile("\\b" + Pattern.quote(query), Pattern.UNICODE_CHARACTER_CLASS
                | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    }

    @Override
    public boolean test(Show show) {
        return pattern.matcher(show.getName()).find();
    }
}

