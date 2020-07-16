package com.drobot.task6.controller.command.impl;

import com.drobot.task6.controller.command.ActionCommand;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AddCommand implements ActionCommand {

    private static final int NAME_POSITION = 0;
    private static final int RELEASE_YEAR_POSITION = 1;
    private static final int PAGES_POSITION = 2;
    private static final int AUTHORS_POSITION = 3;

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws CommandException {
        if (params.length >= 4) {
            Optional<Map<UUID, CustomBook>> result = Optional.empty();
            StorageService storageService;
            int releaseYear;
            int pages;
            String name = params[NAME_POSITION];
            String stringReleaseYear = params[RELEASE_YEAR_POSITION];
            String stringPages = params[PAGES_POSITION];
            List<String> authors = Arrays.asList(params).subList(AUTHORS_POSITION, params.length);
            try {
                releaseYear = Integer.parseInt(stringReleaseYear);
                pages = Integer.parseInt(stringPages);
            } catch (NumberFormatException e) {
                return result;
            }
            storageService = new StorageService();
            try {
                if (storageService.addBook(name, releaseYear, pages, authors)) {
                    result = Optional.of(Map.of());
                }
            } catch (ServiceException e) {
                throw new CommandException(e.getMessage());
            }
            return result;
        } else {
            throw new CommandException("Invalid input");
        }
    }
}
