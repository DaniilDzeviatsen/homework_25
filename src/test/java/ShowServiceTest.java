import by.teachmeskills.dzeviatsen.Predicators.PredicateByCountry;
import by.teachmeskills.dzeviatsen.Predicators.PredicateByRating;
import by.teachmeskills.dzeviatsen.Predicators.PredicateByTitle;
import by.teachmeskills.dzeviatsen.ShowService.ShowService;
import by.teachmeskills.dzeviatsen.models.Film;
import by.teachmeskills.dzeviatsen.models.Serial;
import by.teachmeskills.dzeviatsen.models.Show;
import by.teachmeskills.dzeviatsen.repository.ShowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowServiceTest {
    ShowService service;
    ShowRepository repository;

    private final Show SHOW1 = new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5);
    private final Show SHOW2 = new Film("Зеленая миля", 1999, "US", 9.1, 127148);

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ShowRepository.class);
        service = new ShowService(repository);
    }

    @Test
    void shouldReturnSortedByYearList() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_YEAR);
        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(SHOW2);
        expectedShows.add(SHOW1);
        assertEquals(shows1, expectedShows);
    }

    @Test
    void shouldReturnSortedByNameList() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_NAME);
        List<Show> shows = new ArrayList<>();
        shows.add(SHOW2);
        shows.add(SHOW1);
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(SHOW1);
        expectedShows.add(SHOW2);
        assertEquals(shows1, expectedShows);
    }

    @Test
    void shouldReturnSortedByRatingList() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_RATING);
        List<Show> shows = new ArrayList<>();
        shows.add(SHOW2);
        shows.add(SHOW1);
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(SHOW1);
        expectedShows.add(SHOW2);
        assertEquals(shows1, expectedShows);
    }

    @Test
    void shouldReturnSortedByVotes() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_VOTES);
        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(SHOW2);
        expectedShows.add(SHOW1);
        assertEquals(shows1, expectedShows);
    }

    @Test
    void shouldFilterByTitle() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String title = "тя";
        predicatorsList.add(new PredicateByTitle(title));

        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(SHOW1);

        assertEquals(shows1, expected);

    }

    @Test
    void shouldFilterByRating() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        double rateFrom = 8.0;
        double rateTo = 9.0;
        predicatorsList.add(new PredicateByRating(rateFrom, rateTo));

        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(SHOW1);
        assertEquals(shows1, expected);
    }

    @Test
    void shouldFilterByRatingBorders() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        double rateFrom = 8.9;
        double rateTo = 8.9;
        predicatorsList.add(new PredicateByRating(rateFrom, rateTo));

        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(SHOW1);
        assertEquals(shows1, expected);
    }

    @Test
    void shouldFilterByCountry() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String country = "US";
        predicatorsList.add(new PredicateByCountry(country));

        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(SHOW1);
        expected.add(SHOW2);
        assertEquals(shows1, expected);
    }

    @Test
    void shouldFilterByCountryEmpty() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String country = "GB";
        predicatorsList.add(new PredicateByCountry(country));

        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        assertEquals(shows1, expected);
    }

    @Test
    void shouldSortByTwoCriteria() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> preedicatorsList = new ArrayList<>();
        list.add(Show.BY_RATING);
        list.add(Show.BY_YEAR);
        List<Show> shows = new ArrayList<>();
        shows.add(SHOW2);
        shows.add(SHOW1);
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(preedicatorsList, list);
        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(SHOW1);
        expectedShows.add(SHOW2);
        assertEquals(shows1, expectedShows);
    }

    @Test
    void shouldFilterByTwoCriteria() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String country = "US";
        String title = "wrnvejr";
        predicatorsList.add(new PredicateByCountry(country));
        predicatorsList.add(new PredicateByTitle(title));
        List<Show> shows = new ArrayList<>();
        shows.add(SHOW1);
        shows.add(SHOW2);
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expectedShows = new ArrayList<>();
        assertEquals(shows1, expectedShows);
    }
}
