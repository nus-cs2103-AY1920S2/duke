import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class handles all input from the user.
 */

public class Parser {
    public static boolean isBye = false;
    private static TaskList currentList;

    /**
     * Creates a new instance of the class Parser.
     */

    public Parser() {
    }

    /**
     * Associates a parser with a task list.
     * @param tasks This is the TaskList to be associated with the current parser.
     */

    public static void addList(TaskList tasks) {
        currentList = tasks;
    }

    /**
     * Parses the command from user input.
     * @param command The command line that the user input.
     */

    public static void parse(String command) {
        if (command.equals("bye")) {
            //Exit
            System.out.println("Bye. Hope to see you again soon!");
            isBye = true;
        } else if (command.equals("list")) {
            //List all tasks
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < currentList.getSize(); i++) {
                System.out.println(i + 1 + ". " + currentList.getTask(i));
            }
        } else if ((command.length() > 3) && (command.substring(0, 4).equals("done"))) {
            //Mark task as done
            try {
                int taskNumber = Integer.parseInt(command.substring(5));
                currentList.getTask(taskNumber - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currentList.getTask(taskNumber - 1));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Done cannot be empty. Please add a valid task number to mark as done");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid task number to mark as done");
            }
        } else if ((command.length() > 5) && (command.substring(0, 6).equals("delete"))) {
            //Delete task
            try {
                int taskNumber = Integer.parseInt(command.substring(7));
                Task temp = currentList.removeTask(taskNumber - 1);
                System.out.println("Nice! I have removed this task:");
                System.out.println(temp);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Delete cannot be empty. Please add a valid task number to delete");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid task number to delete");
            }
        } else {
            //Add task
            Task newTask = new Task("placeholder");
            if (command.contains("todo")) {
                try {
                    newTask = new ToDo(command.substring(5));
                    currentList.addTask(newTask);
                    Ui.gotIt(newTask, currentList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("ToDo description cannot be empty");
                }
            } else if (command.contains("deadline")) {
                try {
                    int breakPos = command.indexOf("/");
                    if ((breakPos == -1) && (command.length() == 8)) {
                        throw new DukeException("No desc Deadline");
                    }
                    LocalDate date = LocalDate.parse(command.substring(breakPos + 4));
                    newTask = new Deadline(command.substring(9, breakPos - 1), date);
                    currentList.addTask(newTask);
                    Ui.gotIt(newTask, currentList.getSize());
                } catch (DukeException e) {
                    System.out.println("Deadline description cannot be empty");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please re-enter the date for Deadline");
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter a valid date format in the form of YYYY-MM-DD");
                }
            } else if (command.contains("event")) {
                try {
                    int breakPos = command.indexOf("/");
                    if ((breakPos == -1) && (command.length() == 5)) {
                        throw new DukeException("No desc Event");
                    }
                    LocalDate date = LocalDate.parse(command.substring(breakPos + 4));
                    newTask = new Event(command.substring(6, breakPos - 1), date);
                    currentList.addTask(newTask);
                    Ui.gotIt(newTask, currentList.getSize());
                } catch (DukeException e) {
                    System.out.println("Event description cannot be empty");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please re-enter the date for Event");
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter a valid date format in the form of YYYY-MM-DD");
                }
            } else {
                System.out.println("Please input a valid command");
            }
        }

    }
}
