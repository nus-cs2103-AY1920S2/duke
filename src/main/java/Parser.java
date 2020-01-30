import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeException;
import duke.exception.DukeTaskException;
import duke.exception.DukeUnknownException;
import duke.exception.DukeArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Make sense of the user's command and execute their desired function(s).
     * @param command Input from the user.
     * @param taskList User's TaskList.
     * @param storage Storage class that reads from/write to a specified file.
     */
    public void parseAndExecute(String command, TaskList taskList, Storage storage) {
        String[] commandArray = command.split(" ", 2);
        try {
            if (command.toLowerCase().equals("list")) {
                Ui.printWithBorder(taskList.showList());
            } else if (commandArray[0].toLowerCase().equals("done")) {
                if (commandArray.length >= 2) {
                    markTaskAsDone(taskList, commandArray[1]);
                } else {
                    throw new DukeArgumentException("Please specify which task to be marked as done.");
                }
            } else if (commandArray[0].toLowerCase().equals("delete")) {
                if (commandArray.length >= 2) {
                    deleteTask(taskList, commandArray[1]);
                } else {
                    throw new DukeArgumentException("Please specify which task to be deleted.");
                }
            } else if (commandArray[0].toLowerCase().equals("todo")) {
                if (commandArray.length >= 2) {
                    addTask(taskList, commandArray[1], "T");
                } else {
                    throw new DukeArgumentException("Missing field in todo command.");
                }
            } else if (commandArray[0].toLowerCase().equals("deadline")) {
                if (commandArray.length >= 2) {
                    addTask(taskList, commandArray[1], "D");
                } else {
                    throw new DukeArgumentException("Missing field in deadline command.");
                }
            } else if (commandArray[0].toLowerCase().equals("event")) {
                if (commandArray.length >= 2) {
                    addTask(taskList, commandArray[1], "E");
                } else {
                    throw new DukeArgumentException("Missing field in event command.");
                }
            } else {
                throw new DukeUnknownException("Apologies, I do not recognise this command.");
            }
        } catch (DukeException err) {
            Ui.printWithBorder(err.getMessage());
        }
    }

    /**
     * Adds Task to TaskList based on its type (i.e. Todo, Deadline or Event).
     * @param taskList User's TaskList.
     * @param input Input provided by the user.
     * @param type Type of Task ("T" for Todo, "D" for Deadline, "E" for Event).
     * @throws DukeTaskException
     */
    public static void addTask(TaskList taskList, String input, String type) throws DukeTaskException {
        String str = "\nCurrent number of task(s): ";

        if (type.equals("T")) {
            Task todo = new Todo(input);
            taskList.add(todo);
            Ui.printWithBorder("The following to-do has been added:\n    " + todo.toString() + str + taskList.size());

        } else if (type.equals("D")) {
            String[] arr = input.split("/", 2);
            if (arr.length > 1) {
                String description = arr[0];
                String by = arr[1].split(" ", 2)[1];
                if (isLocalDate(by)) {
                    LocalDate deadlineDate = LocalDate.parse(by);
                    Deadline deadline = new Deadline(description, deadlineDate);
                    taskList.add(deadline);
                    Ui.printWithBorder("The following task has been added:\n    " + deadline.toString() + str + taskList.size());
                } else {
                    throw new DukeTaskException("Invalid date format detected. Please ensure date is in yyyy-mm-dd (e.g. 2019-01-30).");
                }
            } else {
                throw new DukeTaskException("\'/by\' field is missing.");
            }

        } else if (type.equals("E")) {
            String[] arr = input.split("/", 2);
            if (arr.length > 1) {
                String description = arr[0];
                String at = arr[1].split(" ", 2)[1];
                if (isLocalDate(at)) {
                    LocalDate eventDate = LocalDate.parse(at);
                    Event event = new Event(description, eventDate);
                    taskList.add(event);
                    Ui.printWithBorder("The following task has been added:\n    " + event.toString() + str + taskList.size());
                } else {
                    throw new DukeTaskException("Invalid date format detected. Please ensure date is in yyyy-mm-dd (e.g. 2019-02-28).");
                }
            } else {
                throw new DukeTaskException("\'/at\' field is missing.");
            }
        }
    }

    /**
     * Checks if input provided can be parsed into a LocalDate object.
     * @param input Input from user.
     * @return True if input can be parsed, false otherwise.
     */
    public static boolean isLocalDate(String input) {
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException err) {
            return false;
        }
        return true;
    }

    /**
     * Marks the specified task as done.
     * @param taskList User's TaskList.
     * @param userIndex Index of Task to be marked as done.
     * @throws DukeArgumentException
     */
    public void markTaskAsDone(TaskList taskList, String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            Ui.printWithBorder("There is no task in your list to be marked as done.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            Ui.printWithBorder("As per requested, the following task has been marked as done:\n" + "    " + t.toString());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
    }

    /**
     * Deletes the specified task.
     * @param taskList User's TaskList.
     * @param userIndex Index of Task to be deleted.
     * @throws DukeArgumentException
     */
    public void deleteTask(TaskList taskList, String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            Ui.printWithBorder("There is no task in your list to be deleted.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            taskList.remove(index);
            Ui.printWithBorder("As per requested, the following task has been deleted:\n" + "    " + t.toString() + "\nCurrent number of task(s): " + taskList.size());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
    }
}
