package by.teachmeskills.dzeviatsen.repository;

import by.teachmeskills.dzeviatsen.models.Film;

public class FilmCsvDeserializer implements ShowDeserializer {
    public static final FilmCsvDeserializer INSTANCE = new FilmCsvDeserializer();

    public FilmCsvDeserializer() {
    }

    @Override
    public Film deserialize(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) throw new IllegalArgumentException();
        return new Film(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[2],
                Double.parseDouble(parts[3]),
                Integer.parseInt(parts[4])
        );
    }
}

