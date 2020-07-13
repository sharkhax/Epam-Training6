package com.drobot.task6.command.impl;

import com.drobot.task6.command.ActionCommand;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;
import com.drobot.task6.parser.BookParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AddCommand implements ActionCommand {

    private static final int NAME_POSITION = 0;
    private static final int RELEASE_YEAR_POSITION = 1;
    private static final int PAGES_POSITION = 2;
    private static final int AUTHORS_POSITION = 3;
    private static final String INPUT_EXCEPTION_MESSAGE = "Invalid input";

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws ServiceException, ValueException {
        if (params.length >= 4) {
            String name = params[NAME_POSITION];
            String stringReleaseYear = params[RELEASE_YEAR_POSITION];
            String stringPages = params[PAGES_POSITION];
            List<String> authors = new ArrayList<>();

            for (int i = AUTHORS_POSITION; i < params.length; i++) {
                authors.add(params[i]);
            }

            BookParser parser = new BookParser();
            int releaseYear = parser.parseInt(stringReleaseYear);
            int pages = parser.parseInt(stringPages);

            StorageService storageService = new StorageService();
            if (storageService.addBook(name, releaseYear, pages, authors)) {
                return Optional.of(Map.of());
            } else {
                return Optional.empty();
            }
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }
}
