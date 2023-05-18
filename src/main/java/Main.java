import by.teachmeskills.dzeviatsen.Predicators.PredicateByCountry;
import by.teachmeskills.dzeviatsen.Predicators.PredicateByRating;
import by.teachmeskills.dzeviatsen.Predicators.PredicateByTitle;
import by.teachmeskills.dzeviatsen.ShowService.ShowService;
import by.teachmeskills.dzeviatsen.models.Show;
import by.teachmeskills.dzeviatsen.repository.ShowRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    private static final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        log.info("Программа запущена");
        ShowRepository showRepository = new ShowRepository();
        ShowService showService = new ShowService(showRepository);
        List<Predicate<Show>> filters = requestFilters();
        List<Comparator<Show>> sorting = requestSorting();
        List<Show> shows = showService.query(filters, sorting);
        if (shows.isEmpty()) {
            System.out.println("Ничего не найдено");

        } else {
            for (Show show : shows) {
                System.out.println(show.toString());
            }
        }
    }

    private static List<Predicate<Show>> requestFilters() {
        List<Predicate<Show>> filters = new ArrayList<>();
        System.out.println("""
                Add filter:
                  byCountry <countryCode>             
                  byRating <from> <to>
                  byTitle <query>
                  end""");
        while (true) {
            String command = sc.nextLine();
            String[] parts = command.split(" ");
            if (parts[0].equals("end")) return filters;
            Predicate<Show> filter = switch (parts[0]) {
                case "byCountry" -> new PredicateByCountry(parts[1]);
                case "byRating" -> new PredicateByRating(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                case "byTitle" -> new PredicateByTitle(parts[1]);
                default -> throw new IllegalArgumentException("Unknown command");
            };
            filters.add(filter);
        }
    }

    private static List<Comparator<Show>> requestSorting() {
        List<Comparator<Show>> sorting = new ArrayList<>();
        System.out.println("""
                Add sorting:
                  byVotes
                  byRating
                  byYear
                  byTitle
                  end""");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("end")) return sorting;
            Comparator<Show> comparator = switch (command) {
                case "byVotes" -> Show.BY_VOTES;
                case "byRating" -> Show.BY_RATING;
                case "byYear" -> Show.BY_YEAR;
                case "byTitle" -> Show.BY_NAME;
                default -> throw new IllegalArgumentException("Unknown command");
            };
            sorting.add(comparator);
        }
    }
}





