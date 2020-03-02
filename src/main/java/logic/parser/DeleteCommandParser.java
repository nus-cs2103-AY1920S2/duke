package logic.parser;

import commons.Index;
import logic.command.DeleteCommand;
import logic.parser.exceptions.ParserException;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParserException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParserException pe) {
            throw new ParserException("Pawdon me, I think you furgot to include the task number."
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
