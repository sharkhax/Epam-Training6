package com.drobot.task6.controller.command;

import com.drobot.task6.exception.CommandException;
import com.drobot.task6.model.entity.CustomBook;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface ActionCommand {
        Optional<Map<UUID, CustomBook>> execute(String ... params) throws CommandException;
}
