package com.drobot.task6.command.impl;

import com.drobot.task6.command.ActionCommand;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RemoveCommand implements ActionCommand {

    private static final int ID_POSITION = 0;
    private static final String INPUT_EXCEPTION_MESSAGE = "Invalid input";

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws ServiceException, ValueException {
        if (params.length == 1) {
            String stringId = params[ID_POSITION];
            UUID id = UUID.fromString(stringId);
            StorageService storageService = new StorageService();

            if (storageService.removeBook(id)) {
                return Optional.of(Map.of());
            } else {
                return Optional.empty();
            }
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }
}
