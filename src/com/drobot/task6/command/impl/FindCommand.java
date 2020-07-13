package com.drobot.task6.command.impl;

import com.drobot.task6.command.ActionCommand;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;
import com.drobot.task6.parser.BookParser;
import com.drobot.task6.type.CustomTag;
import com.drobot.task6.util.TypeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FindCommand implements ActionCommand {

    private static final int TAG_POSITION = 0;
    private static final int FIELD_POSITION = 1;
    private static final String INPUT_EXCEPTION_MESSAGE = "Invalid input";

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws ServiceException, ValueException {
        if (params.length > 1) {
            CustomTag tag;
            Optional<Map<UUID, CustomBook>> result;
            TypeUtil util = new TypeUtil();

            if (util.containsTag(params[TAG_POSITION].toUpperCase())) {
                tag = CustomTag.valueOf(params[TAG_POSITION].toUpperCase());
            } else {
                throw new ValueException(INPUT_EXCEPTION_MESSAGE);
            }

            switch (tag) {
                case ID -> result = returnIdCase(params);
                case NAME -> result = returnNameCase(params);
                case RELEASE_YEAR -> result = returnReleaseYearCase(params);
                case PAGES -> result = returnPagesCase(params);
                case AUTHORS -> result = returnAuthorsCase(params);
                default -> result = Optional.empty();
            }
            return result;
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Optional<Map<UUID, CustomBook>> returnIdCase(String... params)
            throws ValueException {
        if (params.length < 3) {
            Optional<Map<UUID, CustomBook>> result;
            String stringId = params[FIELD_POSITION];
            UUID id = UUID.fromString(stringId);

            StorageService storageService = new StorageService();
            Map<UUID, CustomBook> books = storageService.findById(id);

            if (books.isEmpty()) {
                result = Optional.empty();
            } else {
                result = Optional.of(books);
            }
            return result;
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Optional<Map<UUID, CustomBook>> returnNameCase(String... params)
            throws ServiceException, ValueException {
        if (params.length < 3) {
            Optional<Map<UUID, CustomBook>> result;
            String name = params[FIELD_POSITION];

            StorageService storageService = new StorageService();
            Map<UUID, CustomBook> books = storageService.findByName(name);

            if (books.isEmpty()) {
                result = Optional.empty();
            } else {
                result = Optional.of(books);
            }
            return result;
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Optional<Map<UUID, CustomBook>> returnReleaseYearCase(String... params)
            throws ServiceException, ValueException {
        if (params.length < 3) {
            Optional<Map<UUID, CustomBook>> result;
            String stringReleaseYear = params[FIELD_POSITION];

            BookParser parser = new BookParser();
            int releaseYear = parser.parseInt(stringReleaseYear);

            StorageService storageService = new StorageService();
            Map<UUID, CustomBook> books = storageService.findByReleaseYear(releaseYear);

            if (books.isEmpty()) {
                result = Optional.empty();
            } else {
                result = Optional.of(books);
            }
            return result;
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Optional<Map<UUID, CustomBook>> returnPagesCase(String... params)
            throws ServiceException, ValueException {
        if (params.length < 3) {
            Optional<Map<UUID, CustomBook>> result;
            String stringPages = params[FIELD_POSITION];

            BookParser parser = new BookParser();
            int pages = parser.parseInt(stringPages);

            StorageService storageService = new StorageService();
            Map<UUID, CustomBook> books = storageService.findByPages(pages);

            if (books.isEmpty()) {
                result = Optional.empty();
            } else {
                result = Optional.of(books);
            }
            return result;
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }

    private Optional<Map<UUID, CustomBook>> returnAuthorsCase(String... params)
            throws ServiceException {
        Optional<Map<UUID, CustomBook>> result;
        List<String> authors = new ArrayList<>();
        StorageService storageService = new StorageService();

        for (int i = 1; i < params.length; i++) {
            authors.add(params[i]);
        }

        Map<UUID, CustomBook> books = storageService.findByAuthors(authors);

        if (books.isEmpty()) {
            result = Optional.empty();
        } else {
            result = Optional.of(books);
        }
        return result;
    }
}
