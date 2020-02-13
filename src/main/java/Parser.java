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
    public static final int COMMAND_POSITION = 0;
    public static final int DESCRIPTION_POSITION = 1;
    public static final int MINIMUM_COMMAND_LENGTH = 2;

    public static final String TODO_TYPE = "T";
    public static final String DEADLINE_TYPE = "D";
    public static final String EVENT_TYPE = "E";

    public static final int MINIMUM_DEADLINE_LENGTH = 2;
    public static final int DEADLINE_DESCRIPTION_POSITION = 0;
    public static final int DEADLINE_TIME_POSITION = 1;

    public static final int MINIMUM_EVENT_LENGTH = 2;
    public static final int EVENT_DESCRIPTION_POSITION = 0;
    public static final int EVENT_TIME_POSITION = 1;

    /**
     * Make sense of the user's command and execute their desired function(s).
     * @param command Input from the user.
     * @param taskList User's TaskList.
     * @param storage Storage class that reads from/write to a specified file.
     */
    public void parseAndExecute(String command, TaskList taskList, Storage storage) {
        String[] commandArray = command.split(" ", MINIMUM_COMMAND_LENGTH);
        try {
            if (command.toLowerCase().equals("list")) {
                Ui.printWithBorder(taskList.showList());
            } else if (commandArray[COMMAND_POSITION].toLowerCase().equals("done")) {
                if (commandArray.length >= MINIMUM_COMMAND_LENGTH) {
                    markTaskAsDone(taskList, commandArray[DESCRIPTION_POSITION]);
                } else {
                    throw new DukeArgumentException("Please specify which task to be marked as done.");
                }
            } else if (commandArray[COMMAND_POSITION].toLowerCase().equals("delete")) {
                if (commandArray.length >= MINIMUM_COMMAND_LENGTH) {
                    deleteTask(taskList, commandArray[DESCRIPTION_POSITION]);
                } else {
                    throw new DukeArgumentException("Please specify which task to be deleted.");
                }
            } else if (commandArray[COMMAND_POSITION].toLowerCase().equals("find")) {
                if (commandArray.length >= MINIMUM_COMMAND_LENGTH) {
                    findTask(taskList, commandArray[DESCRIPTION_POSITION]);
                } else {
                    throw new DukeArgumentException("Please specify the keyword(s) that you want to search.");
                }
            } else if (commandArray[COMMAND_POSITION].toLowerCase().equals("todo")) {
                if (commandArray.length >= MINIMUM_COMMAND_LENGTH) {
                    addTask(taskList, commandArray[DESCRIPTION_POSITION], TODO_TYPE);
                } else {
                    throw new DukeArgumentException("Missing field in todo command.");
                }
            } else if (commandArray[COMMAND_POSITION].toLowerCase().equals("deadline")) {
                if (commandArray.length >= MINIMUM_COMMAND_LENGTH) {
                    addTask(taskList, commandArray[DESCRIPTION_POSITION], DEADLINE_TYPE);
                } else {
                    throw new DukeArgumentException("Missing field in deadline command.");
                }
            } else if (commandArray[COMMAND_POSITION].toLowerCase().equals("event")) {
                if (commandArray.length >= MINIMUM_COMMAND_LENGTH) {
                    addTask(taskList, commandArray[DESCRIPTION_POSITION], EVENT_TYPE);
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
     * @throws DukeTaskException Throws exception related to Duke Task class.
     */
    public static void addTask(TaskList taskList, String input, String type) throws DukeTaskException {
        String str = "\nCurrent number of task(s): ";

        if (type.equals(TODO_TYPE)) {
            Task todo = new Todo(input);
            if (duplicatedTask(taskList, todo)) {
                Ui.printWithBorder("This todo is already in your task list.");
            } else {
                taskList.add(todo);
                Ui.printWithBorder("The following to-do has been added:\n    "
                        + todo.toString() + str + taskList.size());
            }

        } else if (type.equals(DEADLINE_TYPE)) {
            String[] arr = input.split("/", MINIMUM_DEADLINE_LENGTH);
            if (arr.length >= MINIMUM_DEADLINE_LENGTH) {
                String description = arr[DEADLINE_DESCRIPTION_POSITION];
                String by = arr[DEADLINE_TIME_POSITION].split(" ", MINIMUM_DEADLINE_LENGTH)[DEADLINE_TIME_POSITION];
                if (isLocalDate(by)) {
                    LocalDate deadlineDate = LocalDate.parse(by);
                    Deadline deadline = new Deadline(description, deadlineDate);
                    if (duplicatedTask(taskList, deadline)) {
                        Ui.printWithBorder("This deadline is already in your task list.");
                    } else {
                        taskList.add(deadline);
                        Ui.printWithBorder("The following task has been added:\n"
                                + "    " + deadline.toString() + str + taskList.size());
                    }
                } else {
                    throw new DukeTaskException("Invalid date format detected. "
                            + "Please ensure date is in yyyy-mm-dd (e.g. 2019-01-30).");
                }
            } else {
                throw new DukeTaskException("\'/by\' field is missing.");
            }

        } else if (type.equals(EVENT_TYPE)) {
            String[] arr = input.split("/", MINIMUM_EVENT_LENGTH);
            if (arr.length >= MINIMUM_EVENT_LENGTH) {
                String description = arr[EVENT_DESCRIPTION_POSITION];
                String at = arr[EVENT_TIME_POSITION].split(" ", MINIMUM_EVENT_LENGTH)[EVENT_TIME_POSITION];
                if (isLocalDate(at)) {
                    LocalDate eventDate = LocalDate.parse(at);
                    Event event = new Event(description, eventDate);
                    if (duplicatedTask(taskList, event)) {
                        Ui.printWithBorder("This event is already in your task list.");
                    } else {
                        taskList.add(event);
                        Ui.printWithBorder("The following task has been added:\n"
                                + "    " + event.toString() + str + taskList.size());
                    }
                } else {
                    throw new DukeTaskException("Invalid date format detected. "
                            + "Please ensure date is in yyyy-mm-dd (e.g. 2019-02-28).");
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
     * @throws DukeArgumentException Throws exception related to invalid argument provided.
     */
    public void markTaskAsDone(TaskList taskList, String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            Ui.printWithBorder("There is no task in your list to be marked as done.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            Ui.printWithBorder("As per requested, the following task has been marked as done:\n"
                    + "    " + t.toString());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
    }

    /**
     * Deletes the specified task.
     * @param taskList User's TaskList.
     * @param userIndex Index of Task to be deleted.
     * @throws DukeArgumentException Throws exception related to invalid argument provided.
     */
    public void deleteTask(TaskList taskList, String userIndex) throws DukeArgumentException {
        int index = Integer.parseInt(userIndex) - 1;
        if (taskList.isEmpty()) {
            Ui.printWithBorder("There is no task in your list to be deleted.");
        } else if (index < taskList.size()) {
            Task t = taskList.get(index);
            taskList.remove(index);
            Ui.printWithBorder("As per requested, the following task has been deleted:\n"
                    + "    " + t.toString() + "\nCurrent number of task(s): " + taskList.size());
        } else {
            throw new DukeArgumentException("Please provide a number between 1 and " + taskList.size() + ".");
        }
    }

    /**
     * Displays a search result of Tasks that matches the keyword specified by user.
     * @param taskList User's TaskList.
     * @param keyword Keyword used to search the TaskList.
     */
    public void findTask(TaskList taskList, String keyword) {
        TaskList searchResult = taskList.findTask(keyword);
        if (searchResult.isEmpty()) {
            Ui.printWithBorder("Apologies, I could not find any matching task.");
        } else {
            StringBuilder sb = new StringBuilder("After much deliberation, I found these matching tasks:");
            for (int i = 0; i < searchResult.size(); i++) {
                Task t = searchResult.get(i);
                sb.append("\n    ");
                sb.append(t.toString());
            }
            Ui.printWithBorder(sb.toString());
        }
    }

    /**
     * Checks if there already exists a duplicated task in the user's TaskList.
     * @param taskList User's list of Tasks.
     * @param task New Task that is to be added.
     * @return True if a duplicated Task exists, false otherwise.
     */
    public static boolean duplicatedTask(TaskList taskList, Task task) {
        boolean isDuplicate = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task current = taskList.get(i);
            if (task.getDescription().equals(current.getDescription()) && current.getStatus().equals("[X]")) {
                isDuplicate = true;
                break;
            } else {
                continue;
            }
        }
        return isDuplicate;
    }
}
