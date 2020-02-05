package main.java;

import main.java.Parser.Command;
import main.java.Parser.ExitCommand;
import main.java.Parser.Parser;
import main.java.exceptions.InvalidStorageFilePathException;
import main.java.exceptions.NoCommandException;
import main.java.model.TaskList;


import main.java.exceptions.NoDescriptionException;
import main.java.storage.Storage;

import java.io.IOException;

public class Duke {

    protected String user_name;
    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;
    protected Ui ui;

    public Duke () {
        this.user_name = "";
    }

    public Duke (String user_name) {
        this.user_name = user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    private void start() {
        this.ui = new Ui();
        try {
            this.taskList = new TaskList();
            this.parser = new Parser();
            this.storage = new Storage();
            ui.askForName();
            ui.greet();
        } catch (InvalidStorageFilePathException e) {
            ui.printErrorMessage(e.getMessage());

            throw new RuntimeException(e);
        }
    }

    private void exit() {
        System.exit(0);
    }

    public void run() {
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
            } catch (NoDescriptionException | NoCommandException e) {
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
