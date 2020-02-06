import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatBot {

    public static Scanner lineScanner;
    public static Scanner sc;

    private Storage storage;
    private final String FILE_NAME = "./data/duke.txt";
    private TaskList tasks;
    private Ui ui = new Ui();

    protected void run() throws FileNotFoundException, NoSuchElementException {
        storage = new Storage(FILE_NAME);
        tasks = new TaskList(storage);
        sc = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (sc.hasNext()) {
                String userInput = sc.nextLine();
                ui.showLineOfUnderscores();

                if (userInput.equals("bye")) {
                    ui.showGoodbyeMessage();
                } else if (userInput.equals("list")) {
                    tasks.printTasks();
                } else if (userInput.startsWith("done")) {
                    tasks.makeTaskDone(-1 + Integer.parseInt(userInput.split(" ")[1]));
                } else if (userInput.startsWith("todo")) {
                    lineScanner = new Scanner(userInput);
                    lineScanner.next(); // skip todo word
                    try {
                        Task task = new Todo(lineScanner.nextLine().substring(1)); // skip space
                        tasks.addTask(task);
                        storage.saveAllTasksToFile(tasks);
                    } catch (NoSuchElementException error) {
                        ui.showEmptyDescription("todo");                 }
                } else if (userInput.startsWith("deadline")) {
                    lineScanner = new Scanner(userInput);
                    lineScanner.next();
                    String theRest = lineScanner.nextLine();
                    try {
                        Task task = new Deadline(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                        tasks.addTask(task);
                        storage.saveAllTasksToFile(tasks);
                    } catch (NoSuchElementException error) {
                        ui.showEmptyDescription("deadline");
                    }
                } else if (userInput.startsWith("event")) {
                        lineScanner = new Scanner(userInput);
                        lineScanner.next();
                        String theRest = lineScanner.nextLine();
                    try {
                        Task task = new Event(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                        tasks.addTask(task);
                        storage.saveAllTasksToFile(tasks);
                    } catch (NoSuchElementException error) {
                        ui.showEmptyDescription("event");
                    }
                } else if (userInput.startsWith("delete")) {
                    try {
                        int taskNumber = -1 + Integer.parseInt(userInput.split(" ")[1]);
                        tasks.getTasks().get(taskNumber); // try to get exception
                        tasks.deleteTask(taskNumber);
                    }
                    catch (IndexOutOfBoundsException error) {
                        ui.showInvalidRemoval();
                    }
                } else if (userInput.startsWith("filter")) { //filter {date/month/year} {value}
                    try {
                        String criterion = userInput.split(" ")[1];
                        if (criterion.equals("month")) {
                            int month = Integer.parseInt(userInput.split(" ")[2]);
                            tasks.showFilteredBySpecificMonth(month);
                        } else if (criterion.equals("year")) {
                            int year = Integer.parseInt(userInput.split(" ")[2]);
                            tasks.showFilteredBySpecificYear(year);
                        } else if (criterion.equals("date")) {
                            String date = userInput.split(" ")[2];
                            tasks.showFilteredBySpecificDate(date);
                        }
                    } catch (NoSuchElementException e) {
                        ui.showMissingParemeters();
                    }
                } else if (userInput.startsWith("find")) {
                    try {
                        String word = userInput.split(" ")[1];
                        tasks.showFilteredByName(word);
                    } catch (NoSuchElementException e) {
                        ui.showMissingParemeters();
                    }
                } else {
                    ui.showCommandNotFound();
                }
                ui.showLineOfUnderscores();
            }
    }
}
