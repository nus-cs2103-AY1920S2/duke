package duke;

import duke.parser.CommandExecutionExeption;
import duke.parser.Parser;
import duke.ui.UiText;
import duke.ui.gui.Gui;
import duke.ui.gui.MainGui;
import duke.ui.terminal.TerminalUi;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main UI method.
 */
public class DukeMain extends Application {

    public static void main(String[] args) {
        UiText ui = new TerminalUi(System.in);
        ui.respond(ui.greetings);
        DukeHistory.progress(new Duke(ui));
        while (true) {
            feedCommandLine();
        }
    }

    private static void feedCommandLine() {
        Duke main = DukeHistory.getCurrent();
        if (main.ui.hasNextLine()) {
            String cmd = main.ui.nextLine();
            main.ui.clearUserInput();
            try {
                DukeHistory.progress(Parser.parse(cmd).execute(main));
            } catch (CommandExecutionExeption cmde) {

            }
        }
    }

    Gui gui;

    @Override
    public void init() throws Exception {
        this.gui = new MainGui();
        DukeHistory.progress(new Duke(this.gui));
    }

    @Override
    public void start(Stage primaryStage) {
        this.gui.start(primaryStage);
        this.gui.respond(this.gui.greetings);
    }

    @Override
    public void stop() {
        System.out.println("Stopping...");
    }
}
