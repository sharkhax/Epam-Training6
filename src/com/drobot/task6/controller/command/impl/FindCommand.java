package com.drobot.task6.controller.command.impl;

import com.drobot.task6.controller.command.ActionCommand;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;
import com.drobot.task6.type.CustomTag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class FindCommand implements ActionCommand {

    private static final int TAG_POSITION = 0;
    private static final int FIELD_POSITION = 1;

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws CommandException {
        if (params.length > 1) {
            CustomTag tag;
            Optional<Map<UUID, CustomBook>> result;
            try {
                tag = CustomTag.valueOf(params[TAG_POSITION].toUpperCase());
            } catch (IllegalArgumentException e){
                throw new CommandException("Invalid tag");
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
            throw new CommandException("Invalid input");
        }
    }

    private Optional<Map<UUID, CustomBook>> returnIdCase(String... params) throws CommandException {
        if (params.length == 2) {
            Optional<Map<UUID, CustomBook>> result = Optional.empty();
            String stringId = params[FIELD_POSITION];
            UUID id;
            StorageService storageService;
            Map<UUID, CustomBook> books;
            try {
                id = UUID.fromString(stringId);
            } catch (IllegalArgumentException e) {
                return result;
            }
            storageService = new StorageService();
            books = storageService.findById(id);
            if (!books.isEmpty()) {
                result = Optional.of(books);
            }
            return result;
        } else {
            throw new CommandException("Invalid input");
        }
    }

    private Optional<Map<UUID, CustomBook>> returnNameCase(String... params) throws CommandException {
        if (params.length == 2) {
            Optional<Map<UUID, CustomBook>> result = Optional.empty();
            String name = params[FIELD_POSITION];
            StorageService storageService = new StorageService();
            Map<UUID, CustomBook> books = storageService.findByName(name);
            if (!books.isEmpty()) {
                result = Optional.of(books);
            }
            return result;
        } else {
            throw new CommandException("Invalid input");
        }
    }

    private Optional<Map<UUID, CustomBook>> returnReleaseYearCase(String... params) throws CommandException {
        if (params.length == 2) {
            Optional<Map<UUID, CustomBook>> result = Optional.empty();
            Optional<Integer> optional = toIntValue(params);
            int releaseYear;
            if (optional.isPresent()) {
                releaseYear = optional.get();
                StorageService storageService = new StorageService();
                Map<UUID, CustomBook> books = storageService.findByReleaseYear(releaseYear);
                if (!books.isEmpty()) {
                    result = Optional.of(books);
                }
            }
            return result;
        } else {
            throw new CommandException("Invalid input");
        }
    }

    private Optional<Map<UUID, CustomBook>> returnPagesCase(String... params) throws CommandException {
        if (params.length == 2) {
            Optional<Map<UUID, CustomBook>> result = Optional.empty();
            Optional<Integer> optional = toIntValue(params);
            int pages;
            if (optional.isPresent()) {
                pages = optional.get();
                StorageService storageService = new StorageService();
                Map<UUID, CustomBook> books = storageService.findByPages(pages);
                if (!books.isEmpty()) {
                    result = Optional.of(books);
                }
            }
            return result;
        } else {
            throw new CommandException("Invalid input");
        }
    }

    private Optional<Map<UUID, CustomBook>> returnAuthorsCase(String... params) {
        Optional<Map<UUID, CustomBook>> result = Optional.empty();
        List<String> authors = Arrays.asList(params).subList(FIELD_POSITION, params.length);
        StorageService storageService = new StorageService();
        Map<UUID, CustomBook> books = storageService.findByAuthors(authors);
        if (!books.isEmpty()) {
            result = Optional.of(books);
        }
        return result;
    }

    private Optional<Integer> toIntValue(String[] params) {
        String stringReleaseYear = params[FIELD_POSITION];
        int value;
        try {
            value = Integer.parseInt(stringReleaseYear);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
        return Optional.of(value);
    }
}
