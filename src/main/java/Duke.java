import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class is the main class,
 * where the command processing happens.
 */
public class Duke {

    private static Ui uI;
    private static Parser parser;

    /**
     * Construct a new Duke instance.
     * First greets the user and then loads the task data from file.
     */
    public Duke() {
        Storage storage = new Storage();
        uI = new Ui();
        parser = new Parser();
        uI.greet();
        while (true) {
            try {
                tasks = storage.loadFile();
                run();
                storage.saveFile(tasks);
                System.exit(0);
            } catch (IOException | DukeException e) {
                uI.printError(e);
            } catch (DateTimeParseException d) {
                uI.printInvalidDateFormatError();
            }
        }
    }


    private static List<Task> tasks = new ArrayList<>();

    /**
     * Parses the input entered by the client.
     * The following are valid commands that Duke can process:
     * list - lists all the tasks that are stored.
     * done [index] - marks the task of a particular index as done.
     * delete [index] - deletes the task of a particular index.
     * todo [description] - adds the Todo task to the list.
     * deadline [description] /by [date] [time] - adds the Deadline task to the list.
     * event [description] /at [date] [time] - adds the Event task to the list.
     * If an exit command is entered, it is processed,
     * then the goodbye message is printed and the program exits from the loop.
     *
     * @throws DukeException If the command is invalid or the task enquired doesn't exists.
     * @throws DateTimeParseException If the date of the deadline or event is not formatted properly.
     */
    private static void run() throws DukeException, DateTimeParseException {
        TaskList taskList = new TaskList(tasks);
        String command = parser.getCommand();
        while (!command.equals("bye")) {
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
            command = parser.getCommand();
        }
        uI.printBye();
    }

    public static void main(String[] args) {
        new Duke();
    }

}

