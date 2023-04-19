import by.teachmeskills.dzeviatsen.ShowService.ShowService;
import by.teachmeskills.dzeviatsen.models.Show;
import by.teachmeskills.dzeviatsen.repository.ShowRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<String> commands = List.of("by name", "by rating", "by year", "by votes");
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        log.info("Программа запущена");
        ShowRepository showRepository = new ShowRepository();
        ShowService showService = new ShowService(showRepository);
        try {
            List<Show> showlist = showRepository.listShows();
            for (Show show : showlist) {
                System.out.println(show.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        System.out.println("Which  sorting do you  want to implement?");
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Choose operation");
            System.out.println("1) Sorting");
            System.out.println("2) Filtration");
            int operation = Integer.parseInt(sc.nextLine());
            List<Show> shows = showRepository.listShows();
            if (operation == 1) {
                System.out.println("Write type of sorting");
                String typeOfSorting = sc.nextLine();
                switch (typeOfSorting) {
                    case "by name" -> {
                        shows = showService.getSortedListOfShows(commands.get(0));
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case "by rating" -> {
                        shows = showService.getSortedListOfShows(commands.get(1));
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case "by year" -> {
                        shows = showService.getSortedListOfShows(commands.get(2));
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case "by votes" -> {
                        shows = showService.getSortedListOfShows(commands.get(3));
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case "without sorting" -> showRepository.listShows();
                }
            }
            if (operation == 2) {
                System.out.println("Choose filters");
                System.out.println("1)Country");
                System.out.println("2)Rating");
                System.out.println("3)Year");
                System.out.println("4)Number of votes");
                System.out.println("5)Key letters");
                int filter = Integer.parseInt(sc.nextLine());
                switch (filter) {
                    case 1 -> {
                        System.out.println("Enter country code");
                        String countryCode = sc.nextLine();
                        shows = showService.handleCountryFilter(countryCode);
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter rating from");
                        String rateFrom = sc.nextLine();
                        System.out.println("Enter rating to");
                        String rateTo = sc.nextLine();
                        shows = showService.handleRateFilter(rateFrom, rateTo);
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter year");
                        int year = Integer.valueOf(sc.nextLine());
                        shows = showService.handleYearFilter(year);
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter number of votes from");
                        String numFrom = sc.nextLine();
                        System.out.println("Enter number of votes to");
                        String numTo = sc.nextLine();
                        shows = showService.handleVotesFilter(numFrom, numTo);
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter line");
                        String query = sc.nextLine();
                        shows = showService.getFilteredListOfShows("by title", query);
                        for (Show show : shows) {
                            System.out.println(show.toString());
                        }
                    }
                }
            }
        } while (true);
    }

}


