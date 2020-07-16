package com.drobot.task6.type;

import com.drobot.task6.controller.command.ActionCommand;
import com.drobot.task6.controller.command.impl.AddCommand;
import com.drobot.task6.controller.command.impl.FindCommand;
import com.drobot.task6.controller.command.impl.RemoveCommand;
import com.drobot.task6.controller.command.impl.SortCommand;

public enum CommandType {
    ADD_BOOK(new AddCommand()), REMOVE_BOOK(new RemoveCommand()),
    FIND_BOOK(new FindCommand()), SORT_BOOKS(new SortCommand());

    private final ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
