package dukemain;

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

    /*
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
    */
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

    public String getResponse(String input) {
        String command;
        String output = "";
        try {
            command = input;
            DukeCommand currCommand = parser.handleCommand(command);
            output = currCommand.execute(dl, ds, ui);
            System.out.println("Duke: \n" + output);
        } catch (DukeException e) {
            output = ui.getErrorMessage(e);
        }

        return output;
    }
}
