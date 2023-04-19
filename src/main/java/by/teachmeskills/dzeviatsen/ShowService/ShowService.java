package by.teachmeskills.dzeviatsen.ShowService;

import by.teachmeskills.dzeviatsen.Predicators.PredicateByTitle;
import by.teachmeskills.dzeviatsen.models.Show;
import by.teachmeskills.dzeviatsen.repository.ShowRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = Objects.requireNonNull(showRepository);
    }

    public List<Show> getSortedListOfShows(String command) {
        Comparator<Show> comparator = null;
        switch (command) {
            case "by name" -> comparator = Show.BY_NAME;
            case "by rating" -> comparator = Show.BY_RATING;
            case "by year" -> comparator = Show.BY_YEAR;
            case "by votes" -> comparator = Show.BY_VOTES;
        }
        return getSortedShows(comparator);
    }

    private List<Show> getSortedShows(Comparator<Show> comparator) {
        List<Show> shows = showRepository.listShows();
        shows.sort(comparator);
        return shows;
    }

    public List<Show> getFilteredListOfShows(String command, String query) {
        Predicate<Show> predicate = null;
        switch (command) {
            case "by title" -> predicate = new PredicateByTitle(query);
        }
        return getFilteredShows(predicate);
    }

    private List<Show> getFilteredShows(Predicate<Show> predicate) {
        List<Show> shows = showRepository.listShows();
        List<Show> tmpShows = new ArrayList<>();
        for (Show show : shows) {
            if (predicate.test(show)) {
                tmpShows.add(show);
            }
        }
        return tmpShows;
    }

    public List<Show> handleCountryFilter(String countryCode) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(Show.predicateByCountry(countryCode).negate());
        return shows;
    }

    public List<Show> handleYearFilter(int year) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(Show.predicateByYear(String.valueOf(year)).negate());
        return shows;
    }

    public List<Show> handleRateFilter(String rateFrom, String rateTo) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(Show.predicateByRate(rateFrom, rateTo).negate());
        return shows;
    }

    public List<Show> handleVotesFilter(String numFrom, String numTo) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(Show.predicateByVotes(numFrom, numTo).negate());
        return shows;
    }


}


