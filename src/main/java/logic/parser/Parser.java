package logic.parser;

public interface Parser<Command> {
    Command parse(String userInput) throws ParserException;
}
