import exceptions.IllegalPositionException;
import exceptions.IllegalDateTimeFormatException;
import exceptions.InvalidStorageFilePathException;
import exceptions.NoCommandException;
import exceptions.NoDescriptionException;
import exceptions.StorageOperationException;

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

    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;

    public Duke() {

    }

    /**
     * Checks does a user input match an exit key.
     * @param input user input in string.
     * @return true if user input matches exit key.
     */
    public static boolean isExitKey(String input) {
        return Parser.isExitKey(input);
    }

    /**
     * Returns greeting string to the screen.
     * @return greet text.
     */
    public String greet() {
        return HelpCommand.HELP_TEXT;
    }

    /**
     * Initializes components.
     * @throws InvalidStorageFilePathException If default storage path is invalid.
     * @throws IllegalDateTimeFormatException If datetime strings of loaded tasks are invalid.
     * @throws NoDescriptionException If loaded tasks do not have description.
     * @throws StorageOperationException If there is error decoding tasks.
     * @throws IOException If there is error reading files.
     */
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
    public String getResponse(String input) throws RuntimeException {
        Command command = null;
        try {
            command = parser.parseCommand(input);
            command.setTaskList(taskList);
            String commandResult = command.execute();
            storage.save(taskList);

            assert commandResult != null : "The response message is null";
            assert commandResult.length() > 0 : "The response message is empty";

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
