package com.drobot.task6.controller;

import com.drobot.task6.command.ActionCommand;
import com.drobot.task6.exception.ServiceException;
import com.drobot.task6.exception.ValueException;
import com.drobot.task6.model.entity.CustomBook;
import com.drobot.task6.provider.CommandProvider;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Invoker {
    public Optional<Map<UUID, CustomBook>> processRequest(String commandName, String... params)
            throws ValueException, ServiceException {
        Optional<ActionCommand> optionalCommand = CommandProvider.defineCommand(commandName);
        Optional<Map<UUID, CustomBook>> result;

        if (optionalCommand.isEmpty()) {
            result = Optional.empty();
        } else {
            ActionCommand command = optionalCommand.get();
            result = command.execute(params);
        }
        return result;
    }
}
