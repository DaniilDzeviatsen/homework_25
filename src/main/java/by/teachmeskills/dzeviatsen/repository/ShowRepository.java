package by.teachmeskills.dzeviatsen.repository;

import by.teachmeskills.dzeviatsen.models.Film;
import by.teachmeskills.dzeviatsen.models.Serial;
import by.teachmeskills.dzeviatsen.models.Show;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShowRepository {
    private final Path SERIES_PATH = Path.of(
            "series.csv");
    private final Path FILMS_PATH = Path.of("films.csv");

    public List<Show> listShows() {
        List<Show> showsList;
        List<Film> filmsList = getFilms(FILMS_PATH);
        List<Serial> serialList = getSerials(SERIES_PATH);
        showsList = Stream.concat(filmsList.stream(), serialList.stream()).collect(Collectors.toList());
        return showsList;
    }

    private List<Film> getFilms(Path filePath) {
        try (Stream<String> csvLines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            List<Film> films = csvLines
                    .map(csvLine -> csvLine.split(","))
                    .map(csvColumns -> new Film(
                            csvColumns[0],
                            Integer.parseInt(csvColumns[1]),
                            csvColumns[2],
                            Double.parseDouble(csvColumns[3]),
                            Integer.parseInt(csvColumns[4])
                    ))
                    .toList();
            return films;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private List<Serial> getSerials(Path filePath) {
        try (Stream<String> csvLines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            List<Serial> serials = csvLines
                    .map(csvLine -> csvLine.split(","))
                    .map(csvColumns -> new Serial(
                            csvColumns[0],
                            Integer.parseInt(csvColumns[1]),
                            csvColumns[3],
                            Double.parseDouble(csvColumns[6]),
                            Integer.parseInt(csvColumns[7]),
                            Integer.parseInt(csvColumns[2]),
                            Integer.parseInt(csvColumns[5]),
                            Integer.parseInt(csvColumns[4])
                    ))
                    .toList();
            return serials;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

 /*   private List<Serial> getSeriesList(Path filePath) {
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
        Double rating = Double.parseDouble(parts[3]);
        int numOfVotes = Integer.valueOf(parts[4]);
        return new Film(name, productionYear, country, rating, numOfVotes);
    }

    private Serial parseSerial(String csvLine) {
        String[] parts = csvLine.split(",");
        String name = parts[0];
        int productionYear = Integer.valueOf(parts[1]);
        String country = parts[3];
        double rating = Double.parseDouble(parts[6]);
        int numOfVotes = Integer.valueOf(parts[7]);
        int lastEpisodeYear = Integer.valueOf(parts[2]);
        int numOfEpisodes = Integer.valueOf(parts[5]);
        int numOfSeasons = Integer.valueOf(parts[4]);
        return new Serial(name, productionYear, country, rating,
                numOfVotes, lastEpisodeYear, numOfEpisodes, numOfSeasons);
    }*/
}

