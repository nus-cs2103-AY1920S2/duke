import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChatBot {
    public static Scanner lineScanner;
    public static Scanner sc;

    protected void run() throws FileNotFoundException, NoSuchElementException {
        List<Task> history = new IOFromHardDisk().getAllTasksFromFile(); // get from database
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
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < history.size(); i++) {
                        System.out.println((i + 1) + "." + history.get(i).toString());
                    }
                } else if (userInput.startsWith("done")) {
                    int taskNumber = -1 + Integer.parseInt(userInput.split(" ")[1]);
                    history.get(taskNumber).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(history.get(taskNumber));
                    
                } else if (userInput.startsWith("todo")) {
                    lineScanner = new Scanner(userInput);
                    lineScanner.next(); // skip todo word
                    try {
                        Task task = new Todo(lineScanner.nextLine().substring(1)); // skip space
                        history.add(task);
                        new IOFromHardDisk().saveAllTasksToFile(history);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task);
                        System.out.println("Now you have " + history.size() + " tasks in the list.");
                    } catch (NoSuchElementException error) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (userInput.startsWith("deadline")) {
                    try {
                        lineScanner = new Scanner(userInput);
                        lineScanner.next();
                        String theRest = lineScanner.nextLine();
                        Task task = new Deadline(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                        history.add(task);
                        new IOFromHardDisk().saveAllTasksToFile(history);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task);
                        System.out.println("Now you have " + history.size() + " tasks in the list.");
                    } catch (NoSuchElementException error) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (userInput.startsWith("event")) {
                    try {
                        lineScanner = new Scanner(userInput);
                        lineScanner.next();
                        String theRest = lineScanner.nextLine();
                        Task task = new Event(theRest.split("/")[0].substring(1), theRest.split("/")[1]);
                        history.add(task);
                        new IOFromHardDisk().saveAllTasksToFile(history);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task);
                        System.out.println("Now you have " + history.size() + " tasks in the list.");
                    } catch (NoSuchElementException error) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                } else if (userInput.startsWith("delete")) {
                    try {
                        int taskNumber = -1 + Integer.parseInt(userInput.split(" ")[1]);
                        history.get(taskNumber); //try
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(history.get(taskNumber));
                        history.remove(taskNumber);
                        System.out.println("Now you have " + history.size() + " tasks in the list.");
                    }
                    catch (IndexOutOfBoundsException error) {
                        System.out.println("This item is not valid to remove.");
                    }
                } else if (userInput.startsWith("find")) { //find {date/month/year} {value}
                    String criterion = userInput.split(" ")[1];
                    if (criterion.equals("month")) {
                        int month = Integer.parseInt(userInput.split(" ")[2]);
                        List<Task> filteredTasks = new TaskQueries().filterBySpecificMonth(history, month);
                        System.out.println("Here are the tasks in the month " + month);
                        for (int i = 0; i < filteredTasks.size(); i++) {
                            System.out.println((i + 1) + "." + filteredTasks.get(i).toString());
                        }
                    } else if (criterion.equals("year")) {
                        int year = Integer.parseInt(userInput.split(" ")[2]);
                        List<Task> filteredTasks = new TaskQueries().filterBySpecificYear(history, year);
                        System.out.println("Here are the tasks in the year " + year);
                        for (int i = 0; i < filteredTasks.size(); i++) {
                            System.out.println((i + 1) + "." + filteredTasks.get(i).toString());
                        }
                    } else if (criterion.equals("date")) {
                        String date = userInput.split(" ")[2];
                        List<Task> filteredTasks = new TaskQueries().filterBySpecificDate(history, date);
                        System.out.println("Here are the tasks on date " + date);
                        for (int i = 0; i < filteredTasks.size(); i++) {
                            System.out.println((i + 1) + "." + filteredTasks.get(i).toString());
                        }
                    }
                }

                else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                System.out.println("____________________________________________________________");
            }
    }
}
