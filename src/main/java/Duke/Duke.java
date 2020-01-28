package Duke;

import Duke.DukeExceptions.DukeException;
import Duke.List.DukeList;
import Duke.Parser.DukeParser;
import Duke.Storage.DukeStorage;
import Duke.UI.DukeUI;
import Duke.Command.DukeCommand;

import java.io.IOException;

// Handles the functioning of Duke
public class Duke {
    private DukeList dl;
    private DukeStorage ds = new DukeStorage();
    private DukeUI ui;
    private DukeParser parser;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        try {
            dl = ds.load();
            ui = new DukeUI();
            parser = new DukeParser();
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        String command;

        while(true) {
            try {
                command = ui.readCommandString();
                ui.printLine();
                DukeCommand currCommand = parser.handleCommand(command);
                currCommand.execute(dl, ds, ui);
                ui.printLine();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }
}
