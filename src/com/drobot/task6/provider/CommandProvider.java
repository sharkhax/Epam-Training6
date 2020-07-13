package com.drobot.task6.provider;

import com.drobot.task6.command.ActionCommand;
import com.drobot.task6.type.CommandType;
import com.drobot.task6.util.TypeUtil;

import java.util.Optional;

public class CommandProvider {

    private CommandProvider() {
    }

    public static Optional<ActionCommand> defineCommand(String commandName) {
        TypeUtil util = new TypeUtil();

        if (util.containsCommand(commandName.toUpperCase())) {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            ActionCommand command = type.getCommand();
            return Optional.of(command);
        } else {
            return Optional.empty();
        }
    }
}
