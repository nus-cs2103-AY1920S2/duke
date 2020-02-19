import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javafx.application.Platform;

/**
 * Duke, representing a personal assistant chat bot that helps to track the user's task list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a chat bot, Duke, with the specified file path.
     * @param filePath Path to specify location of file in computer.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            File file = new File("data");
            if (!file.exists()) {
                new File("data").mkdir();
            }
            new File(filePath).createNewFile();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the chat bot, Duke, while users key in various command inputs.
     * @param input Input by user to perform certain tasks in the chat bot.
     * @return String indicating GUI response to input by user.
     */
    public String runGUI(String input) {
        String output;
        try {
            //String fullCommand = ui.readCommand();
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            output = ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            output = ui.showDateError();
        } catch (IndexOutOfBoundsException e) {
            output = ui.showIndexError();
        }
        assert output != null: "Response message by Duke should not be null";
        return output;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = this.runGUI(input);
        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }
        return response;
    }
}
