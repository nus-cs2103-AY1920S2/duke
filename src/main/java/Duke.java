package main.java;

import main.java.exceptions.IllegalDateTimeFormatException;
import main.java.parser.Command;
import main.java.parser.ExitCommand;
import main.java.parser.Parser;
import main.java.exceptions.NoCommandException;
import main.java.model.TaskList;


import main.java.exceptions.NoDescriptionException;

public class Duke {

    protected String user_name;
    protected TaskList taskList;
    protected Parser parser;
    protected Ui ui;

    public Duke () {
        this.user_name = "";
    }

    public Duke (String user_name) {
        this.user_name = user_name;
    }
    
    private void start() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        ui.askForName();
        ui.greet();
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
            } catch (NoDescriptionException | NoCommandException | IllegalDateTimeFormatException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        this.exit();
    }

    public static void main(String[] args) {
        Duke myDuke = new Duke();
        myDuke.run();
    }
}
