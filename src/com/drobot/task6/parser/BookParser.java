package com.drobot.task6.parser;

import com.drobot.task6.exception.BookException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookParser {

    private static final String NUMBER_REGEX = "^[-]?\\d+";
    private static final String PARSE_EXCEPTION_MESSAGE = "Wrong data type";

    public int parseInt(String stringValue) throws BookException {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(stringValue);

        if (matcher.matches()) {
            return Integer.parseInt(stringValue);
        } else {
            throw new BookException(PARSE_EXCEPTION_MESSAGE);
        }
    }

    public long parseLong(String stringValue) throws BookException {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(stringValue);

        if (matcher.matches()) {
            return Long.parseLong(stringValue);
        } else {
            throw new BookException(PARSE_EXCEPTION_MESSAGE);
        }
    }
}
