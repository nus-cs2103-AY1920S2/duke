package logic.parser;

import logic.command.TodoCommand;
import logic.parser.exceptions.ParserException;
import tasks.Name;
import tasks.Tag;
import tasks.Todo;

import java.util.Set;
import java.util.stream.Stream;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static logic.parser.CliSyntax.PREFIX_NAME;
import static logic.parser.CliSyntax.PREFIX_TAG;

public class TodoCommandParser implements Parser<TodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TodoCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public TodoCommand parse(String args) throws ParserException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParserException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodoCommand.MESSAGE_USAGE));
        }
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Todo todoTask = new Todo(name, tagList);
        return new TodoCommand(todoTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
