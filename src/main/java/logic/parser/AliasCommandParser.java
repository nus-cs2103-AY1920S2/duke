package logic.parser;

import logic.command.AliasCommand;

import java.util.stream.Stream;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static logic.parser.CliSyntax.PREFIX_ALIAS;
import static logic.parser.CliSyntax.PREFIX_COMMAND;

public class AliasCommandParser implements Parser<AliasCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     *
     * @throws ParserException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParserException {
        //);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND);
        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS, PREFIX_COMMAND)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParserException("Pawdon me, I think you furgot to include the alias and command\n"
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }
        String alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());
        String command = ParserUtil.parseCommand(argMultimap.getValue(PREFIX_COMMAND).get());
        return new AliasCommand(alias, command);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
