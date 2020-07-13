package com.drobot.task6.command.impl;

import com.drobot.task6.command.ActionCommand;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.model.service.StorageService;
import com.drobot.task6.type.CustomTag;
import com.drobot.task6.util.TypeUtil;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SortCommand implements ActionCommand {

    private static final int TAG_POSITION = 0;
    private static final String INPUT_EXCEPTION_MESSAGE = "Invalid input";

    @Override
    public Optional<Map<UUID, CustomBook>> execute(String... params) throws ServiceException, ValueException {
        if (params.length == 1) {
            CustomTag tag;
            TypeUtil util = new TypeUtil();

            if (util.containsTag(params[TAG_POSITION].toUpperCase())) {
                tag = CustomTag.valueOf(params[TAG_POSITION].toUpperCase());
            } else {
                throw new ValueException(INPUT_EXCEPTION_MESSAGE);
            }

            StorageService storageService = new StorageService();

            Map<UUID, CustomBook> books = storageService.sort(tag);
            return Optional.of(books);
        } else {
            throw new ValueException(INPUT_EXCEPTION_MESSAGE);
        }
    }
}
