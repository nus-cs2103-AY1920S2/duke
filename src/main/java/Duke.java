import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;



/**
 * The Duke class contains the main method to update the TaskList and
 * execute the user's commands and input.
 */
public class Duke {
    public final static String LINE = "__________________________________________";
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * The constructor for Duke takes in a String that contains the file path of the txt file
     * to update the TaskList in the txt file.
     */
    public Duke() {
        this("data/duke.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            // shown when current saved task list in the txt file is empty
            tasks = new TaskList();
            // therefore, there is a need to make a new task list.
        }
    }

    /**
     * This method is called to update the Tasks and print outputs based on different user inputs.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            System.out.println(isExit);
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Duke's main method which serves to update the given txt file with the updated list of tasks.
     *
     * @param args
     * @throws DateTimeParseException if user's date input is not of 'yyyy-MM-dd' format.
     */
    public static void main(String[] args) throws DateTimeParseException {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        ByteArrayOutputStream formattedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(formattedOutput));
        String reply = "";
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
        reply = formattedOutput.toString();
        while (reply.contains(Duke.LINE)) {
            reply = reply.replace(Duke.LINE, "");
        }
        reply = reply.trim();
        System.setOut(System.out);
        return reply;
    }

}
