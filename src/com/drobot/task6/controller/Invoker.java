package com.drobot.task6.controller;

import com.drobot.task6.controller.command.ActionCommand;
import com.drobot.task6.exception.CommandException;
import com.drobot.task6.model.entity.CustomBook;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Invoker {
    public Optional<Map<UUID, CustomBook>> processRequest(String commandName, String... params)
            throws CommandException {
        Optional<ActionCommand> optionalCommand = CommandProvider.defineCommand(commandName);
        Optional<Map<UUID, CustomBook>> result = Optional.empty();
        if (optionalCommand.isPresent()) {
            ActionCommand command = optionalCommand.get();
            result = command.execute(params);
        }
        return result;
    }
}
