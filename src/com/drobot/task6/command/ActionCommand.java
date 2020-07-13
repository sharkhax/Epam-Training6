package com.drobot.task6.command;

import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ActionCommand {
        Optional<Map<UUID, CustomBook>> execute(String ... params) throws ServiceException, ValueException;
}
