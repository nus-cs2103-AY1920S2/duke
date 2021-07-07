package com.duke.bot.exception;

/**
 * Represents the exceptions that occur due to invalid commands being parsed to Duke Bot.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
