import dukecommand.DukeCommand;
import dukeexceptions.DukeException;
import dukelist.DukeList;
import dukeparser.DukeParser;
import dukestorage.DukeStorage;
import dukeui.DukeUI;

import java.io.IOException;

/**
 * Represents a Duke object that handles the business logic of the program
 * Will run until user inputs 'bye' command or force quits the program.
 */
public class Duke {
    private DukeList dl;
    private DukeStorage ds;
    private DukeUI ui;
    private DukeParser parser;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Constructor for Duke. Each instantiation will create a new
     * DukeStorage, DukeList, DukeUI, DukeParser for the object.
     */
    public Duke() {
        try {
            ds = new DukeStorage();
            dl = ds.load();
            ui = new DukeUI();
            parser = new DukeParser();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Drives the body of the code.
     */
    public void run() {
        System.out.println("Started");
        ui.showWelcomeMessage();
        String command;
        boolean isMainExit = false;

        while (!isMainExit) {
            try {
                command = ui.readCommandString();
                ui.printLine();
                DukeCommand currCommand = parser.handleCommand(command);
                currCommand.execute(dl, ds, ui);
                isMainExit = currCommand.getIsExit();
                ui.printLine();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
