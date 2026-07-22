package com.expensetracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocalJsonRepository {
    private static final Path STORAGE_FILE = Path.of("expenses.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Expense> findAll() {
        if (Files.notExists(STORAGE_FILE)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(STORAGE_FILE);
            if (json.isBlank()) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(json, new TypeReference<List<Expense>>() {});
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not read expenses from " + STORAGE_FILE.toAbsolutePath(), e);
        }
    }

    public void saveAll(List<Expense> expenses) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(STORAGE_FILE.toFile(), expenses);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not save expenses to " + STORAGE_FILE.toAbsolutePath(), e);
        }
    }
}
