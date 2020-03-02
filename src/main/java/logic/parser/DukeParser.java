package logic.parser;

import logic.command.AliasCommand;
import logic.command.ClearCommand;
import logic.command.Command;
import logic.command.DeadlineCommand;
import logic.command.DeleteCommand;
import logic.command.DoneCommand;
import logic.command.EventCommand;
import logic.command.ExitCommand;
import logic.command.FindCommand;
import logic.command.HelloCommand;
import logic.command.HelpCommand;
import logic.command.ListCommand;
import logic.command.TagCommand;
import logic.command.TodoCommand;
import logic.parser.exceptions.ParserException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static commons.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static commons.Messages.MESSAGE_UNKNOWN_COMMAND;

/**
 * Parses user input and instruct corresponding classes to perform specified tasks.
 */
public class DukeParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    //private FriendlierSyntax friendlierSyntax;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput     full user input string
     * @param commandSyntax to check for alias.
     * @return the command based on the user input
     * @throws ParserException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, CommandSyntax commandSyntax) throws ParserException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParserException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandSyntax.lookUpCommand(commandWord.toLowerCase())) {
            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommandParser().parse(arguments);

            case EventCommand.COMMAND_WORD:
                return new EventCommandParser().parse(arguments);

            case TodoCommand.COMMAND_WORD:
                return new TodoCommandParser().parse(arguments);

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case DoneCommand.COMMAND_WORD:
                return new DoneCommandParser().parse(arguments);

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments);

            case TagCommand.COMMAND_WORD:
                return new TagCommandParser().parse(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case AliasCommand.COMMAND_WORD:
                return new AliasCommandParser().parse(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelloCommand.COMMAND_WORD:
                return new HelloCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                throw new ParserException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

