//package java;

import exceptions.IllegalDateTimeFormatException;
import parser.Command;
import parser.ExitCommand;
import parser.Parser;
import exceptions.InvalidStorageFilePathException;
import exceptions.NoCommandException;
import exceptions.StorageOperationException;
import model.TaskList;

import exceptions.NoDescriptionException;
import storage.Storage;

import java.io.IOException;

/**
 * An application capable of recording the tasks and events to help the users
 * manage the schedule.
 */
public class Duke {

    protected String userName;
    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;
    protected Ui ui;

    public Duke() {
        this.userName = "";
    }

    /**
     * Load the storage from file into internal task list.
     * Initiate other components.
     */
    public Duke(String userName) {
        this.userName = userName;
    }
    
    private void start() {
        this.ui = new Ui();
        try {
            this.parser = new Parser();
            this.storage = new Storage();
            this.taskList = storage.load();
            ui.askForName();
            ui.greet();
        } catch (InvalidStorageFilePathException | IOException e) {
            ui.printErrorMessage(e.getMessage());
            throw new RuntimeException(e);
        } catch (StorageOperationException | NoDescriptionException | IllegalDateTimeFormatException err) {
            ui.printErrorMessage(err.getMessage());
        }
    }

    /**
     * exit with status 0.
     */
    private void exit() {
        System.exit(0);
    }

    /**
     * Listen to the user input and start interactions.
     */
    private void run() {
        Command command = new Command();
        this.start();

        while (!ExitCommand.isExit(command)) {
            try {
                String input = ui.getUserInput();
                command = parser.parseCommand(input);
                command.setTaskList(this.taskList);
                String commandResult = command.execute();
                ui.printCommandResult(commandResult);
                storage.save(taskList);
            } catch (NoDescriptionException | NoCommandException | IllegalDateTimeFormatException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                ui.printErrorMessage(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        this.exit();
    }

    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }
}
