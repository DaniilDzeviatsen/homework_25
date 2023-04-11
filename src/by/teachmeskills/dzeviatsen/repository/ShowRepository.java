package by.teachmeskills.dzeviatsen.repository;

import by.teachmeskills.dzeviatsen.models.Film;
import by.teachmeskills.dzeviatsen.models.Serial;
import by.teachmeskills.dzeviatsen.models.Show;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShowRepository {
    final Path SERIES_PATH= Path.of("series.csv");
    final Path FILMS_PATH = Path.of("films.csv");
    public List<Show> listShows(){
        List<Show> listOfFilms=getShowsList(FILMS_PATH);
        List<Show>listOfSeries=getShowsList(SERIES_PATH);
        List<Show>listOfShows=new ArrayList<>();
        listOfShows.addAll(listOfSeries);
        listOfShows.addAll(listOfFilms);
        return listOfShows;
    }

    private List<Show> getShowsList(Path filePath) {
        List<Show> listOfShows = new ArrayList<>();
        try {
            List<String> csvLines = Files.readAllLines(
                    filePath,
                    StandardCharsets.UTF_8
            );
            for (String csvLine : csvLines) {
                Show show= parseShow(csvLine, filePath);
                listOfShows.add(show);
            }
            return listOfShows;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Show parseShow(String csvLine, Path filePath) {
        String[] parts = csvLine.split(",");
        if (filePath.equals("films.csv")) {
            String name = parts[0];
            int productionYear = Integer.valueOf(parts[1]);
            String country = parts[2];
            BigDecimal rating = BigDecimal.valueOf(Double.parseDouble(parts[3]));
            int numOfVotes = Integer.valueOf(parts[4]);
            return new Film(name, productionYear, country, rating, numOfVotes);
        } else if (filePath.equals("series.csv")) {
            String name = parts[0];
            int productionYear = Integer.valueOf(parts[1]);
            String country = parts[2];
            BigDecimal rating = BigDecimal.valueOf(Double.parseDouble(parts[3]));
            int numOfVotes = Integer.valueOf(parts[4]);
            int lastEpisodeYear = Integer.valueOf(parts[5]);
            int numOfEpisodes = Integer.valueOf(parts[6]);
            int numOfSeasons = Integer.valueOf(parts[7]);
            return new Serial(name, productionYear, country, rating,
                    numOfVotes, lastEpisodeYear, numOfEpisodes, numOfSeasons);
        } return null;
    }
}
