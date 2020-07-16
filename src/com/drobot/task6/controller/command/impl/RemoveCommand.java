package com.drobot.task6.controller.command.impl;

import com.drobot.task6.controller.command.ActionCommand;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RemoveCommand implements ActionCommand {

    private static final int ID_POSITION = 0;

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws CommandException {
        if (params.length == 1) {
            Optional<Map<UUID, CustomBook>> result;
            String stringId = params[ID_POSITION];
            UUID id;
            StorageService storageService;
            try {
                id = UUID.fromString(stringId);
            } catch (IllegalArgumentException e) {
                throw new CommandException("Not found");
            }
            storageService = new StorageService();
            try {
                if (storageService.removeBook(id)) {
                    result = Optional.of(Map.of());
                } else {
                    result = Optional.empty();
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
