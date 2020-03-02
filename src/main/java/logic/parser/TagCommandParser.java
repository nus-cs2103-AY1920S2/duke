package logic.parser;

import logic.command.FindCommand;
import logic.command.TagCommand;
import logic.parser.exceptions.ParserException;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new TagCommand object
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns a TagCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public TagCommand parse(String args) throws ParserException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParserException(
                    String.format("Pawdon me, I think you furgot to include the tag."
                                    + MESSAGE_INVALID_COMMAND_FORMAT,
                            FindCommand.MESSAGE_USAGE));
        }
        //String[] nameKeywords = trimmedArgs.split("\\s+");
        return new TagCommand(trimmedArgs);
    }
}
