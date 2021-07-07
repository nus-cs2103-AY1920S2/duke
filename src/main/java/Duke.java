import command.Command;
import command.ErrorCommand;
import common.Storage;
import exception.DukeException;
import parser.Parser;
import task.TaskList;
import ui.TextUi;

import java.util.Stack;

/**
 * Control class of Duke, including the logic of Duke.
 */
public class Duke {

    private TextUi textUi;
    private Storage dukeStorage;
    private TaskList tasks;
    private boolean isFinished;
    private Stack<Command> commandStack;

    /**
     * Initializes ui and storage.
     */
    public Duke() {
        textUi = new TextUi();
        dukeStorage = new Storage("tasks.txt");
        tasks = new TaskList(dukeStorage.readFromFile());
        commandStack = new Stack<>();
    }

    /**
     * Returns a string of duke's respond base on the user input command.
     * @param text user input text.
     * @return duke's response
     */
    public String getResponse(String text) {
        try {
            Command c;
            String commandResult;
            if (text.trim().equalsIgnoreCase("undo")) {
                if (commandStack.isEmpty()) {
                    c = new ErrorCommand();
                } else {
                    c = commandStack.pop();
                }
                commandResult = c.undo(tasks, textUi, dukeStorage);
            } else {
                c = Parser.parse(text.trim());
                if (c.isUndoable()) {
                    commandStack.push(c);
                }
                commandResult = c.execute(tasks, textUi, dukeStorage);
            }
            if (commandResult.equals("     It is time to say goodbye :(")) {
                this.isFinished = true;
            }
            return commandResult;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Takes in the user inputs and excutes each command.
     */
    public void run() {
        textUi.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = textUi.readCommand();
                Command c = Parser.parse(fullCommand);
                String commandResult = c.execute(tasks, textUi, dukeStorage);
                System.out.println(commandResult);
                isExit = c.isExit();
            } catch (DukeException e) {
                textUi.showError(e.getMessage());

            }
        }
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Ends the conversation and exits the system.
     */
    public void exit() {
        textUi.showGoodBye();
        System.exit(0);
    }

    public boolean isFinished() {
        return this.isFinished;
    }
}
