package logic.parser;

import commons.Index;
import logic.command.DoneCommand;
import logic.parser.exceptions.ParserException;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DoneCommandParser implements Parser<DoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns a DoneCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public DoneCommand parse(String args) throws ParserException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneCommand(index);
        } catch (ParserException pe) {
            throw new ParserException("Pawdon me, I think you furgot to include the task number."
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE), pe);
        }
    }

}