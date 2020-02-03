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

    protected void run() throws FileNotFoundException, NoSuchElementException {
        storage = new Storage(FILE_NAME);
        tasks = new TaskList(storage);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am here to help you with anything you need!");
        sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String userInput = sc.nextLine();
                System.out.println("____________________________________________________________");
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
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
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        lineScanner = new Scanner(userInput);
                        lineScanner.next();
                        String theRest = lineScanner.nextLine();
                        Task task = new Deadline(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                        tasks.addTask(task);
                        storage.saveAllTasksToFile(tasks);
                    } catch (NoSuchElementException error) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        lineScanner = new Scanner(userInput);
                        lineScanner.next();
                        String theRest = lineScanner.nextLine();
                        Task task = new Event(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                        tasks.addTask(task);
                        storage.saveAllTasksToFile(tasks);
                    } catch (NoSuchElementException error) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                } else if (userInput.startsWith("delete")) {
                    try {
                        int taskNumber = -1 + Integer.parseInt(userInput.split(" ")[1]);
                        tasks.getTasks().get(taskNumber); // try to get exception
                        tasks.deleteTask(taskNumber);
                    }
                    catch (IndexOutOfBoundsException error) {
                        System.out.println("This item is not valid to remove.");
                    }
                } else if (userInput.startsWith("filter")) { //find {date/month/year} {value}
                    String criterion = userInput.split(" ")[1];
                    if (criterion.equals("month")) {
                        int month = Integer.parseInt(userInput.split(" ")[2]);
                        TaskList filteredTasks = tasks.filterBySpecificMonth(month);
                        System.out.println("Here are the tasks in the month " + month);
                        for (int i = 0; i < filteredTasks.getTasksLength(); i++) {
                            System.out.println((i + 1) + "." + filteredTasks.getTask(i).toString());
                        }
                    } else if (criterion.equals("year")) {
                        int year = Integer.parseInt(userInput.split(" ")[2]);
                        TaskList filteredTasks = tasks.filterBySpecificYear(year);
                        System.out.println("Here are the tasks in the year " + year);
                        for (int i = 0; i < filteredTasks.getTasksLength(); i++) {
                            System.out.println((i + 1) + "." + filteredTasks.getTask(i).toString());
                        }
                    } else if (criterion.equals("date")) {
                        String date = userInput.split(" ")[2];
                        TaskList filteredTasks = tasks.filterBySpecificDate(date);
                        System.out.println("Here are the tasks on date " + date);
                        for (int i = 0; i < filteredTasks.getTasksLength(); i++) {
                            System.out.println((i + 1) + "." + filteredTasks.getTask(i).toString());
                        }
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                System.out.println("____________________________________________________________");
            }
    }
}
