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
import java.util.List;

public class ShowRepository {
    final Path SERIES_PATH = Path.of(
            "series.csv");
    final Path FILMS_PATH = Path.of("films.csv");

    public List<Show> listShows() {
        List<Film> listOfFilms = getFilmsList(FILMS_PATH);
        List<Serial> listOfSeries = getSeriesList(SERIES_PATH);
        List<Show> listOfShows = new ArrayList<>();
        listOfShows.addAll(listOfSeries);
        listOfShows.addAll(listOfFilms);
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

