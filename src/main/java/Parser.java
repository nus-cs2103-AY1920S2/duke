import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class handles all input from the user.
 */

public class Parser {
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

    public static String parse(String command) {
        String output = "";
        if (command.equals("bye")) {
            //Exit
            output += "Bye. Hope to see you again soon!";
        } else if (command.equals("list")) {
            //List all tasks
            output += "Here are the tasks in your list:"
                    + System.lineSeparator();
            for (int i = 0; i < currentList.getSize(); i++) {
                output += i + 1 + ". " + currentList.getTask(i)
                        + System.lineSeparator();
            }
        } else if ((command.length() > 3) && (command.substring(0, 4).equals("done"))) {
            //Mark task as done
            try {
                int taskNumber = Integer.parseInt(command.substring(5));
                currentList.getTask(taskNumber - 1).markAsDone();
                output += "Nice! I've marked this task as done:"
                        + System.lineSeparator();
                output += currentList.getTask(taskNumber - 1)
                        + System.lineSeparator();
            } catch (StringIndexOutOfBoundsException e) {
                output += "Done cannot be empty. Please add a valid task number to mark as done"
                        + System.lineSeparator();
            } catch (IndexOutOfBoundsException e) {
                output += "Please enter a valid task number to mark as done"
                        + System.lineSeparator();
            }
        } else if ((command.length() > 5) && (command.substring(0, 6).equals("delete"))) {
            //Delete task
            try {
                int taskNumber = Integer.parseInt(command.substring(7));
                Task temp = currentList.removeTask(taskNumber - 1);
                output += "Nice! I have removed this task:"
                        + System.lineSeparator();
                output += temp + System.lineSeparator();
            } catch (StringIndexOutOfBoundsException e) {
                output += "Delete cannot be empty. Please add a valid task number to delete"
                        + System.lineSeparator();
            } catch (IndexOutOfBoundsException e) {
                output += "Please enter a valid task number to delete"
                        + System.lineSeparator();
            }
        } else if ((command.length() > 3) && (command.substring(0, 4).equals("find"))) {
            //Find task
            try {
                if (command.length() == 4) throw new DukeException("No info find");
                boolean isMatch = false;
                String keyWord = command.substring(5);
                ArrayList<Task> matchedTask = new ArrayList<Task>();
                for (int i = 0; i < currentList.getSize(); i++) {
                    Task currentTask = currentList.getTask(i);
                    if (currentTask.toString().contains(keyWord)) {
                        matchedTask.add(currentTask);
                        isMatch = true;
                    }
                }
                if (isMatch) {
                    output += "Here are the matching tasks in your list:"
                            + System.lineSeparator();
                    for (int j = 0; j < matchedTask.size(); j++) {
                        output += (j + 1) + "." + matchedTask.get(j)
                                + System.lineSeparator();
                    }
                } else {
                    output += "There are no tasks that match your description"
                            + System.lineSeparator();
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (DukeException e) {
                output += "Find description cannot be empty"
                        + System.lineSeparator();
            }
        } else {
            //Add task
            Task newTask = new Task("placeholder");
            if (command.contains("todo")) {
                try {
                    newTask = new ToDo(command.substring(5));
                    currentList.addTask(newTask);
                    assert (currentList.getSize() != 0);
                    output += "Got it. I've added this task: \n" + newTask
                            + System.lineSeparator();
                    output += "Now you have " + currentList.getSize() + " tasks in the list"
                            + System.lineSeparator();                } catch (IndexOutOfBoundsException e) {
                    output += "ToDo description cannot be empty"
                            + System.lineSeparator();
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
                    assert (currentList.getSize() != 0);
                    output += "Got it. I've added this task: \n" + newTask
                            + System.lineSeparator();
                    output += "Now you have " + currentList.getSize() + " tasks in the list"
                            + System.lineSeparator();
                } catch (DukeException e) {
                    output += "Deadline description cannot be empty"
                            + System.lineSeparator();
                } catch (IndexOutOfBoundsException e) {
                    output += "Please re-enter the date for Deadline"
                            + System.lineSeparator();
                } catch (DateTimeParseException e) {
                    output += "Please enter a valid date format in the form of YYYY-MM-DD"
                            + System.lineSeparator();
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
                    assert (currentList.getSize() != 0);
                    output += "Got it. I've added this task: \n" + newTask
                            + System.lineSeparator();
                    output += "Now you have " + currentList.getSize() + " tasks in the list"
                            + System.lineSeparator();
                } catch (DukeException e) {
                    output += "Event description cannot be empty"
                            + System.lineSeparator();
                } catch (IndexOutOfBoundsException e) {
                    output += "Please re-enter the date for Event"
                            + System.lineSeparator();
                } catch (DateTimeParseException e) {
                    output += "Please enter a valid date format in the form of YYYY-MM-DD"
                            + System.lineSeparator();
                }
            } else {
                output += "Please input a valid command"
                        + System.lineSeparator();
            }
        }
        assert (!output.equals(""));
        return output;
    }
}
