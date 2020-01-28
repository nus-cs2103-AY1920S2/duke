import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
            System.err.println(e);
            uI.printTryAgain();
        } catch (DateTimeParseException d) {
            System.err.println("Please enter the date as yyyy-mm-dd followed by the time.");
            uI.printTryAgain();
        } finally {
            uI.printTerminated();
        }
    }

    private static List<Task> tasks = new ArrayList<>();

    private static void run() throws DukeException, DateTimeParseException {
        taskList = new TaskList(tasks);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                uI.displayTasks(tasks);
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
            command = sc.nextLine();
        }
        sc.close();
        uI.printBye();
    }

    public static void main(String[] args) {
        new Duke();
    }

}

