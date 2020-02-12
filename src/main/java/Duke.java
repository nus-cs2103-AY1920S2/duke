//package java;

import parser.Command;
import parser.ExitCommand;
import parser.Parser;

import exceptions.IllegalDateTimeFormatException;
import exceptions.InvalidStorageFilePathException;
import exceptions.NoCommandException;
import exceptions.StorageOperationException;
import exceptions.NoDescriptionException;

import model.TaskList;

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

    }

    /**
     * Load the storage from file into internal task list.
     * Initiate other components.
     */
    public Duke(String userName) {
        this.userName = userName;
    }
    
    private void start() {
        ui = new Ui();
        try {
            parser = new Parser();
            storage = new Storage();
            taskList = storage.load();
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
     * Listen to the user input and take actions.
     */
    private void listen() {
        Command command = null;

        while (!ExitCommand.isExit(command)) {
            try {
                String input = ui.getUserInput();
                command = parser.parseCommand(input);
                command.setTaskList(taskList);
                ui.printCommandResult(command.execute());
                storage.save(taskList);
            } catch (NoDescriptionException | NoCommandException | IllegalDateTimeFormatException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (IOException e) {
                ui.printErrorMessage(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * exit with status 0.
     */
    private void exit() {
        System.exit(0);
    }

    private void run() {
        start();
        listen();
        exit();
    }

    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }
}
