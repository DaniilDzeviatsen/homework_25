package by.teachmeskills.dzeviatsen.ShowService;

import by.teachmeskills.dzeviatsen.models.Show;
import by.teachmeskills.dzeviatsen.repository.ShowRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = Objects.requireNonNull(showRepository);
    }

    public List<Show> sorter(Comparator comparator) {
        List<Show> shows = showRepository.listShows();
        shows.sort(Show.BY_NAME.reversed());
        return shows;
    }

    public void handleSortByName() {
        List<Show> shows = showRepository.listShows();
        shows.sort(Show.BY_NAME);
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }

    public void handleSortByRating() {
        List<Show> shows = showRepository.listShows();
        shows.sort(Show.BY_RATING);
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }

    public void handleSortByYear() {
        List<Show> shows = showRepository.listShows();
        shows.sort(Show.BY_YEAR);
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }

    public void handleSortByVotes() {
        List<Show> shows = showRepository.listShows();
        shows.sort(Show.BY_VOTES);
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }

    public void handleTwoFactorComparison() {
        List<Show> shows = showRepository.listShows();
        for (Show show : shows) {
            show.getComparatorsList();
        }
    }

    public void handleCountryFilter(String countryCode) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(Show.predicateByCountry(countryCode).negate());
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }

    public void handleYearFilter(int year) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(Show.predicateByYear(String.valueOf(year)).negate());
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }

    public void handleRatingFilter(String rateFrom, String rateTo) {
        List<Show> shows = showRepository.listShows();
        shows.removeIf(e -> e.getRating().compareTo(BigDecimal.valueOf(Double.parseDouble(rateFrom))) == -1);
        shows.removeIf(e -> e.getRating().compareTo(BigDecimal.valueOf(Double.parseDouble(rateTo))) == 1);
        for (Show show : shows) {
            System.out.println(show.toString());
        }
    }
}


