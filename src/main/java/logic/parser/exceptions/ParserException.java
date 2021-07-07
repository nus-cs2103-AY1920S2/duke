package logic.parser.exceptions;

import commons.exceptions.IllegalValueException;

public class ParserException extends IllegalValueException {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
