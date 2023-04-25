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
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ShowServiceTest {
    ShowService service;
    ShowRepository repository;

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
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        assertEquals(shows, shows1);
    }

    @Test
    void shouldReturnSortedByNameList() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_NAME);
        List<Show> shows = new ArrayList<>();
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        assertEquals(shows, shows1);
    }

    @Test
    void shouldReturnSortedByRatingList() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_RATING);
        List<Show> shows = new ArrayList<>();
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        assertEquals(shows, shows1);
    }

    @Test
    void shouldReturnSortedByVotes() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> listPredicates = new ArrayList<>();
        list.add(Show.BY_VOTES);
        List<Show> shows = new ArrayList<>();
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows()).thenReturn(shows);
        List<Show> shows1 = service.query(listPredicates, list);
        assertEquals(shows, shows1);
    }

    @Test
    void shouldFilterByTitle() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String title = "тя";
        predicatorsList.add(new PredicateByTitle(title));

        List<Show> shows = new ArrayList<>();
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(shows.get(0));

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
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(shows.get(0));
        assertIterableEquals(shows1, expected);
    }

    @Test
    void shouldFilterByCountry() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String country = "US";
        predicatorsList.add(new PredicateByCountry(country));

        List<Show> shows = new ArrayList<>();
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        expected.add(shows.get(0));
        expected.add(shows.get(1));
        assertEquals(shows1, expected);
    }

    @Test
    void shouldFilterByCountryEmpty() {
        List<Comparator<Show>> list = new ArrayList<>();
        List<Predicate<Show>> predicatorsList = new ArrayList<>();
        String country = "GB";
        predicatorsList.add(new PredicateByCountry(country));

        List<Show> shows = new ArrayList<>();
        shows.add(new Serial("Во все тяжкие", 2008, "US", 8.9, 533325, 2013, 62, 5));
        shows.add(new Film("Зеленая миля", 1999, "US", 9.1, 127148));
        Mockito.when(repository.listShows())
                .thenReturn(shows);

        List<Show> shows1 = service.query(predicatorsList, list);
        List<Show> expected = new ArrayList<>();
        assertEquals(shows1, expected);
    }
}
