import java.util.EmptyStackException;

/**
 * This class is responsible for parsing and processing
 * the various user input commands, and then responding appropriately.
 */
public class Parser {
    private Ui ui;
    private ActionHistory history;

    public Parser() {
        ui = new Ui();
        history = new ActionHistory();
    }

    /**
     * @return The welcome message on Duke's initialisation.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Parses the user input and processes the commands accordingly.
     * Returns the string representing Duke's reply to the user.
     *
     * @param input Input string entered by user.
     * @param list Working task list of the program, for updating.
     * @return The string representing Duke's reply to the user.
     */
    public String parseCommand(String input, TaskList list) {
        String response;
        TaskList clonedList = new TaskList();
        ActionHistory.copyList(list, clonedList);
        String[] commands = input.split(" ", 2);

        switch (commands[0].toLowerCase()) {
        case "bye":
            response = ui.getExitMessage();
            break;
        case "list":
            response = executeListCommand(list);
            break;
        case "todo":
            response = executeTodoCommand(commands, list, clonedList);
            break;
        case "deadline":
            response = executeDeadlineCommand(commands, list, clonedList);
            break;
        case "event":
            response = executeEventCommand(commands, list, clonedList);
            break;
        case "done":
            response = executeDoneCommand(commands, list, clonedList);
            break;
        case "delete":
            response = executeDeleteCommand(commands, list, clonedList);
            break;
        case "find":
            response = executeFindCommand(commands, list);
            break;
        case "undo":
            response = executeUndoCommand(list);
            break;
        default:
            response = "    Oops! I'm sorry, but I don't know what that means :(\n";
            break;
        }
        return response;
    }

    /**
     * Implements the 'list' functionality of the program.
     * @param list The working task list.
     * @return Duke's message listing to the user the tasks on the list.
     */
    private String executeListCommand(TaskList list) {
        String response = "    Here are the tasks in your list:\n";
        int listLength = list.size();
        for (int i = 0; i < listLength; i++) {
            response += "    " + (i + 1) + "." + list.get(i).getTaskDisplay() + "\n";
        }
        return response;
    }

    /**
     * Implements the adding of tasks to the list.
     * @param task The task to be added.
     * @param list The working task list.
     * @return The message confirming the addition of the task to the list.
     */
    private String executeAddCommand(Task task, TaskList list) {
        list.add(task);
        return ui.getAddedMessage(task, list);
    }

    /**
     * Implements the 'to-do' functionality of the program.
     * @param commands The array of user input commands.
     * @param original The original task list.
     * @param clone The clone of the task list.
     * @return The message confirming addition of the to-do.
     */
    private String executeTodoCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            Task todo = new ToDo(commands[1]);
            response = executeAddCommand(todo, original);
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! The description of a todo cannot be empty.\n";
        }
        return response;
    }

    /**
     * Implements the 'deadline' functionality of the program.
     * @param commands The array of user input commands.
     * @param original The original task list.
     * @param clone The clone of the task list.
     * @return The message confirming addition of the deadline.
     */
    private String executeDeadlineCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            String[] details = commands[1].split(" /by ", 2);
            Task deadline = new Deadline(details[0], details[1]);
            response = executeAddCommand(deadline, original);
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! A deadline must contain both a task "
                    + "description and a deadline.\n";
        }
        return response;
    }

    /**
     * Implements the 'event' functionality of the program.
     * @param commands The array of user input commands.
     * @param original The original task list.
     * @param clone The clone of the task list.
     * @return The message confirming addition of the event.
     */
    private String executeEventCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            String[] details = commands[1].split(" /at ", 2);
            Task event = new Event(details[0], details[1]);
            response = executeAddCommand(event, original);
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! An event must contain both an event "
                    + "description and event time.\n";
        }
        return response;
    }

    /**
     * Implements the 'done' functionality of the program,
     * marking a task a 'done'.
     * @param commands The array of user input commands.
     * @param original The original task list.
     * @param clone The clone of the task list.
     * @return The message confirming the task has been marked as done.
     */
    private String executeDoneCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            original.get(index).markDone();
            response = "    Nice! I've marked this task as done:\n      "
                    + original.get(index).getTaskDisplay() + "\n";
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = ui.getIndexExceptionMessage();
        } catch (NumberFormatException e) {
            response = ui.getNumFormatExceptionMessage();
        }
        return response;
    }

    /**
     * Implements the 'delete' functionality of the program,
     * removing tasks from the list.
     * @param commands The array of user input commands.
     * @param original The original task list.
     * @param clone The clone of the task list.
     * @return The message confirming deletion of the task.
     */
    private String executeDeleteCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            Task toDelete = original.remove(index);
            response = "    Noted. I've removed this task:\n      " + toDelete.getTaskDisplay()
                    + "\n    Now you have " + original.size() + " task"
                    + ((original.size() == 1) ? "" : "s") + " in the list.\n";
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = ui.getIndexExceptionMessage();
        } catch (NumberFormatException e) {
            response = ui.getNumFormatExceptionMessage();
        }
        return response;
    }

    /**
     * Implements the search functionality of the program.
     * Allows the user to search for tasks using keywords.
     * @param commands The array of user input commands.
     * @param list The working task list.
     * @return Duke's reply listing the tasks matching the user's
     *         search string.
     */
    private String executeFindCommand(String[] commands, TaskList list) {
        String response;
        try {
            response = "    Here are the matching tasks in your list:\n";
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                if (list.get(i).getName().contains(commands[1])) {
                    response += "    " + (i + 1) + "." + list.get(i).getTaskDisplay() + "\n";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! A 'find' command must be followed by a search word.\n";
        }
        return response;
    }

    /**
     * Implements the 'undo' functionality of the program.
     * Allows a user to erase actions on the program,
     * within the same application run.
     * @param list The working task list.
     * @return The message confirming whether or not the
     *         undo was successful.
     */
    private String executeUndoCommand(TaskList list) {
        String response;
        try {
            ActionHistory.copyList(history.pop(), list);
            response = "    Undo successful!\n";
        } catch (EmptyStackException e) {
            response = "    Oops! Can't undo any further.\n";
        }
        return response;
    }
}
