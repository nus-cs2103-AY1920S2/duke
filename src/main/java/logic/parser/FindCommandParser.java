package logic.parser;

import logic.command.FindCommand;
import logic.parser.exceptions.ParserException;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParserException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParserException(
                    String.format("Pawdon me, I think you furgot to include the keyword."
                            + MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(trimmedArgs);
    }
}
