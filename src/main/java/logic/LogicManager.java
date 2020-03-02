package logic;

import commons.Duke;
import logic.parser.CommandSyntax;
import logic.command.Command;
import logic.command.CommandException;
import logic.command.CommandResult;
import logic.parser.DukeParser;
import storage.Storage;
import logic.parser.exceptions.ParserException;

import java.io.IOException;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    //private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Duke duke;
    private final Storage storage;
    private final DukeParser dukeParser;
    private CommandSyntax commandSyntax;

    /**
     * Constructor for the logic manager object which controls commands.
     */
    public LogicManager(Duke duke, Storage storage) {
        this.duke = duke;
        this.storage = storage;
        commandSyntax = storage.readAlias();
        dukeParser = new DukeParser();
    }

    public CommandSyntax getCommandSyntax() {
        return commandSyntax;
    }

    /**
     * Parses user input and determine specified instructions to execute.
     *
     * @param commandText input received from user.
     * @return output to be displayed to user.
     */
    @Override
    public CommandResult execute(String commandText) throws CommandException, ParserException {
        //logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        Command command = dukeParser.parseCommand(commandText, commandSyntax);
        commandResult = command.execute(duke);
        try {
            storage.saveTaskList(duke.getTaskList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return commandResult;
    }
}



