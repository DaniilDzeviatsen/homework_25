package hw27.ShowRepository;

import hw27.models.Film;
import hw27.models.Serial;
import hw27.models.Show;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShowRepository {
    private final Path SERIES_PATH = Path.of(
            "series.csv");
    private final Path FILMS_PATH = Path.of("films.csv");

    public List<Show> listShows() {
        List<Show> listOfShows = new ArrayList<>();
        listOfShows.addAll(getFilmsList(FILMS_PATH));
        listOfShows.addAll(getSeriesList(SERIES_PATH));
        return listOfShows;
    }

    private List<Serial> getSeriesList(Path filePath) {
        List<Serial> listOfSeries = new ArrayList<>();
        try {
            List<String> csvLines = Files.readAllLines(
                    filePath,
                    StandardCharsets.UTF_8
            );
            for (String csvLine : csvLines) {
                Serial serial = parseSerial(csvLine);
                listOfSeries.add(serial);
            }
            return listOfSeries;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private List<Film> getFilmsList(Path filePath) {
        List<Film> listOfFilms = new ArrayList<>();
        try {
            List<String> csvLines = Files.readAllLines(
                    filePath,
                    StandardCharsets.UTF_8
            );
            for (String csvLine : csvLines) {
                Film film = parseFilm(csvLine);
                listOfFilms.add(film);
            }
            return listOfFilms;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Film parseFilm(String csvLine) {
        String[] parts = csvLine.split(",");
        String name = parts[0];
        int productionYear = Integer.valueOf(parts[1]);
        String country = parts[2];
        BigDecimal rating = BigDecimal.valueOf(Double.parseDouble(parts[3]));
        int numOfVotes = Integer.valueOf(parts[4]);
        return new Film(name, productionYear, country, rating, numOfVotes);
    }

    private Serial parseSerial(String csvLine) {
        String[] parts = csvLine.split(",");
        String name = parts[0];
        int productionYear = Integer.valueOf(parts[1]);
        String country = parts[3];
        BigDecimal rating = BigDecimal.valueOf(Double.parseDouble(parts[6]));
        int numOfVotes = Integer.valueOf(parts[7]);
        int lastEpisodeYear = Integer.valueOf(parts[2]);
        int numOfEpisodes = Integer.valueOf(parts[5]);
        int numOfSeasons = Integer.valueOf(parts[4]);
        return new Serial(name, productionYear, country, rating,
                numOfVotes, lastEpisodeYear, numOfEpisodes, numOfSeasons);
    }
}
