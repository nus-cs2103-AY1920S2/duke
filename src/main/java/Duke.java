//package java;

import exceptions.*;
import parser.Command;
import parser.Parser;
import parser.HelpCommand;
import model.TaskList;
import storage.Storage;

import java.io.IOException;

/**
 * An application capable of recording the tasks and events to help the users
 * manage the schedule.
 */
public class Duke {

    private static final String GREET_INPUT = "help";

    protected String userName;
    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;

    public Duke() {

    }

    /**
     * Load the storage from file into internal task list.
     * Initiate other components.
     */
    public Duke(String userName) {
        this.userName = userName;
    }

    public static boolean isExitKey(String input) {
        return Parser.isExitKey(input);
    }

    public String greet() {
        return HelpCommand.HELP_TEXT;
    }

    public void start() throws
            InvalidStorageFilePathException, IllegalDateTimeFormatException,
            NoDescriptionException, StorageOperationException, IOException {
        parser = new Parser();
        storage = new Storage();
        taskList = storage.load();
    }

    /**
     * Listen to the user input and take actions.
     */
    public String getResponse(String input) throws RuntimeException{
        Command command = null;
//        assert false: "this is for testing";
        try {
            command = parser.parseCommand(input);
            command.setTaskList(taskList);
            String commandResult = command.execute();
            storage.save(taskList);

            assert commandResult != null: "The response message is null";
            assert commandResult.length() > 0: "The response message is empty";

            return commandResult;
        } catch (NoDescriptionException
                | NoCommandException
                | IllegalDateTimeFormatException
                | IllegalPositionException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
