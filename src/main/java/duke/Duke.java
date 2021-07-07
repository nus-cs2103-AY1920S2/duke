package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.gui.Gui;

/**
 * Class Duke, the driver class of the program.
 * */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private Gui gui;

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Duke object creation, initiate Ui, Parser, TaskList, and Storage.
     *
     * @throws IOException If Exception is raised during loading and saving.
     */
    public Duke() throws IOException {
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(".//saved-tasks.txt");
        gui = new Gui();

        storage.loadBaby(taskList, parser);
    }

    /**
     * Driver function of the whole program.
     *
     * <p>Initiates greeting. Enter a while loop waiting for commands and call respective handlers.
     * Exits when a "bye" command is given.
     */
    public void run() {
        assert (storage != null && ui != null && parser != null && taskList != null) : "Duke instantiation error";

        ui.greeting();
        ui.showList(taskList.getTaskList(), "Tasks loaded from disk:");
        ui.initPrompt();

        boolean isExit = false;

        while (!isExit) {
            try {
                String longCommand = ui.getCommand();
                Command command = parser.parse(longCommand);
                command.execute(taskList, ui);
                isExit = command.isExit();
                storage.saveBaby(taskList.getTaskList());
            } catch (DukeException e) {
                ui.showException(e);
            } catch (Exception e) {
                ui.showUnknownException(e);
            }
        }
        ui.bye();
    }

    /**
     * Returns the String response of Duke according to a user command.
     *
     * @param input input string of user
     * @return String response of Duke
     */
    public String getResponse(String input) {
        String output;
        try {
            Command command = parser.parse(input);
            output = command.execute(taskList, gui);
            storage.saveBaby(taskList.getTaskList());
        } catch (DukeException e) {
            output = gui.showException(e);
        } catch (Exception e) {
            output = gui.showUnknownException(e);
        }
        return output;
    }

}
