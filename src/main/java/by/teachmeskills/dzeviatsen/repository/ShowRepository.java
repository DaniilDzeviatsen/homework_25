package by.teachmeskills.dzeviatsen.repository;

import by.teachmeskills.dzeviatsen.models.Show;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ShowRepository {
    private final Path SERIES_PATH = Path.of(
            "series.csv");
    private final Path FILMS_PATH = Path.of("films.csv");

    public List<Show> listShows() {
        List<Show> showsList = new ArrayList<>();
        readAllShows(showsList, FILMS_PATH, new FilmCsvDeserializer());
        readAllShows(showsList, SERIES_PATH, new SeriesCsvDeserializer());
        return showsList;
    }

    public void readAllShows(List<Show> shows, Path path, ShowDeserializer deserializer) {
        Path showPath = Path.of("/Users/macbook/IdeaProjects/homework_25").resolve(path);
        try (BufferedReader reader = Files.newBufferedReader(showPath)) {
            String csvLine = reader.readLine();
            while (csvLine != null) {
                Show show = deserializer.deserialize(csvLine);
                shows.add(show);
                csvLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


