package com.drobot.task6.parser;

import com.drobot.task6.exception.ValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookParser {

    private static final String NUMBER_REGEX = "^[-]?\\d+";
    private static final String PARSE_EXCEPTION_MESSAGE = "Wrong data type";
    private static final String NULL_EXCEPTION_MESSAGE = "Null";

    public int parseInt(String stringValue) throws ValueException {
        if (stringValue == null) {
            throw new ValueException(NULL_EXCEPTION_MESSAGE);
        }

        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(stringValue);

        if (matcher.matches()) {
            return Integer.parseInt(stringValue);
        } else {
            throw new ValueException(PARSE_EXCEPTION_MESSAGE);
        }
    }
}
