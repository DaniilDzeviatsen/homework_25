package by.teachmeskills.dzeviatsen.repository;

import by.teachmeskills.dzeviatsen.models.Show;

public interface ShowDeserializer {
    Show deserialize(String line);
}
