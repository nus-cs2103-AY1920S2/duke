package logic.parser;

import logic.parser.exceptions.ParserException;

public interface Parser<Command> {
    Command parse(String userInput) throws ParserException;
}
