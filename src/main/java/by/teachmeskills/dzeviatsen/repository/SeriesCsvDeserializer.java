package by.teachmeskills.dzeviatsen.repository;

import by.teachmeskills.dzeviatsen.models.Serial;

public class SeriesCsvDeserializer implements ShowDeserializer {
    public static final SeriesCsvDeserializer INSTANCE = new SeriesCsvDeserializer();

    public SeriesCsvDeserializer() {
    }

    @Override
    public Serial deserialize(String line) {
        String[] parts = line.split(",");
        return new Serial(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[3],
                Double.parseDouble(parts[6]),
                Integer.parseInt(parts[7]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[5]),
                Integer.parseInt(parts[4])
        );
    }
}
