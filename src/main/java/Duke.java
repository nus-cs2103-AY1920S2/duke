import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class is the main class,
 * where the command processing happens.
 */

public class Duke {

    public static Ui uI;
    private static Parser parser;
    private static Storage storage;

    /**
     * Construct a new Duke instance.
     */
    public Duke() {
        uI = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    /**
     * Function to generate a response to user input.
     *
     * @param input Command entered by the user
     * @return String representing Duke's response to the input command.
     * @throws IOException If the file is corrupted or some error occurred during reading the data.
     */
    protected String getResponse(String input) throws IOException {

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        tasks = storage.loadFile();

        if (input.contains("bye")) {
            uI.printBye();
        } else {
            try {
                run(input);
            } catch (DukeException d) {
                uI.printError(d);
            } catch (DateTimeParseException d) {
                uI.printInvalidDateFormatError();
            }
        }
        return baos.toString();
    }

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Parses the input entered by the user.
     * The following are valid commands that Duke can process:
     * list - lists all the tasks that are stored.
     * done [index] - marks the task of a particular index as done.
     * delete [index] - deletes the task of a particular index.
     * todo [description] - adds the Todo task to the list.
     * deadline [description] /by [date] [time] - adds the Deadline task to the list.
     * event [description] /at [date] [time] - adds the Event task to the list.
     *
     * @param input Command entered by the user.
     * @throws DukeException If the command is invalid or the task enquired doesn't exists.
     * @throws DateTimeParseException If the date of the deadline or event is not formatted properly.
     * @throws IOException If the file is corrupted or some error occurred during reading the data.
     */
    private static void run(String input) throws DukeException, DateTimeParseException, IOException {
        TaskList taskList = new TaskList(tasks);
        String command = input;
        if (command.equals("list")) {
            uI.displayTasks(tasks);
        } else if (command.contains("find")) {
            uI.displayFoundTasks(taskList.findTask(parser.trimCommand("find", command)));
        } else {
            switch (parser.checkCommand(command)) {
            case "done":
                tasks = taskList.markAsDone(command);
                break;
            case "delete":
                tasks = taskList.deleteTask(command);
                break;
            default:
                tasks = taskList.addTask(command);
                break;
            }
        }
        storage.saveFile(tasks);
    }

}
