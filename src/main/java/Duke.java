import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    private static Storage storage;
    private static TaskList taskList;
    private static Ui uI;
    private static Parser parser;

    public Duke() {
        storage = new Storage();
        uI = new Ui();
        parser = new Parser();
        try {
            uI.greet();
            tasks = storage.loadFile();
            run();
            storage.saveFile(tasks);
        } catch (IOException | DukeException e) {
            uI.printError(e);
        } catch (DateTimeParseException d) {
            uI.printInvalidDateFormatError();
        } finally {
            uI.printTerminated();
        }
    }

    private static List<Task> tasks = new ArrayList<>();

    private static void run() throws DukeException, DateTimeParseException {
        taskList = new TaskList(tasks);
        String command = parser.getCommand();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                uI.displayTasks(tasks);
            } else if (command.contains("find")) {
                uI.displayFoundTasks(taskList.findTask(parser.trimCommand(command)));
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

