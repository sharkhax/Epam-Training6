package com.drobot.task6.util;

import com.drobot.task6.type.CommandType;
import com.drobot.task6.type.CustomTag;

public class TypeUtil {

    public boolean containsCommand(String stringCommand) {
        CommandType[] values = CommandType.values();
        boolean result = false;

        for (CommandType v : values) {
            if (v.toString().equals(stringCommand)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean containsTag(String stringTag) {
        CustomTag[] tags = CustomTag.values();
        boolean result = false;

        for (CustomTag t : tags) {
            if (t.toString().equals(stringTag)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
