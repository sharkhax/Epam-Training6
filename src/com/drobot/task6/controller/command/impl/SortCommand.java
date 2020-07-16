package com.drobot.task6.controller.command.impl;

import com.drobot.task6.controller.command.ActionCommand;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;
import com.drobot.task6.type.CustomTag;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SortCommand implements ActionCommand {

    private static final int TAG_POSITION = 0;

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws CommandException {
        if (params.length == 1) {
            CustomTag tag;
            Map<UUID, CustomBook> books;
            StorageService storageService;
            try {
                tag = CustomTag.valueOf(params[TAG_POSITION].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new CommandException("Invalid tag");
            }
            storageService = new StorageService();
            try {
                books = storageService.sort(tag);
            } catch (ServiceException e) {
                throw new CommandException(e.getMessage());
            }
            return Optional.of(books);
        } else {
            throw new CommandException("Invalid input");
        }
    }
}
