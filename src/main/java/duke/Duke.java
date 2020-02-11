package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Class Duke, the driver class of the program.
 * */
public class Duke {
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    /**
     * Duke object creation, initiate Ui, Parser, TaskList, and Storage.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(".//saved-tasks.txt");
    }

    /**
     * Driver function of the whole program.
     *
     * <p>Initiates greeting. Enter a while loop waiting for commands and call respective handlers.
     * Exits when a "bye" command is given.
     *
     * @throws IOException If Exception is raised during loading and saving.
     */
    public void run() throws IOException {
        assert ( storage != null && ui != null && parser != null && taskList != null ): "Duke instantiation error";

        storage.loadBaby(taskList, parser);
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

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
