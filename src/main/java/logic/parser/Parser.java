package logic.parser;

import logic.command.Command;
import logic.parser.exceptions.ParserException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParserException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParserException;
}
