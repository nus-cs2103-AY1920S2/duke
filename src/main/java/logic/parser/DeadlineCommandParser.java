package logic.parser;

import logic.command.DeadlineCommand;
import logic.parser.exceptions.ParserException;
import tasks.Date;
import tasks.Deadline;
import tasks.Name;
import tasks.Tag;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static logic.parser.CliSyntax.PREFIX_DATE;
import static logic.parser.CliSyntax.PREFIX_NAME;
import static logic.parser.CliSyntax.PREFIX_TAG;

public class DeadlineCommandParser implements Parser<DeadlineCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeadlineCommand
     * and returns an DeadlineCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public DeadlineCommand parse(String args) throws ParserException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParserException(String.format("Pawdon me, I think you furgot to include the description or date" +
                    " of the deadline." + MESSAGE_INVALID_COMMAND_FORMAT, DeadlineCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Deadline deadlineTask = new Deadline(name, date, tagList);

        return new DeadlineCommand(deadlineTask);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
