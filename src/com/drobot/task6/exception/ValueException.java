package com.drobot.task6.exception;

public class ValueException extends Exception {
    public ValueException() {
    }

    public ValueException(String message) {
        super(message);
    }

    public ValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValueException(Throwable cause) {
        super(cause);
    }
}
