import CustomException.DukeException;

import java.io.IOException;
import java.time.LocalDate;
/**
 * Represents the main UI for communicating with user. A <code>Ui</code> object corresponds to a UI
 */
public class Ui {

    public Ui() {
    }

    /**
     * Prints when an exception is caught during loading
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Prints the task added when a task has been successfully added to the task list
     * @param tasks the list of tasks in memory
     */
    private String printAddedGUI(TaskList tasks) {
        return "Got it. I've added this task:\n"
                + "\t"
                + tasks.get(tasks.size() - 1).toString()
                + "\n"
                + "Now you have "
                + tasks.size()
                + "tasks in the list."
                + "\n";
    }

    /**
     * Main loop of the chatbot, continuously loops to ask for input until user inputs "bye"
     *
     * @param storage the storage media, representing the .txt file as database
     * @param tasks the list of tasks in memory
     * @throws IOException if tasks.save() throws an exception
     */
    public String promptGUI(Storage storage, TaskList tasks, String userInput) throws IOException {
        String firstWord;

        // Guarantees 1st word is legitimate
        try {
            firstWord = Parser.getFirstWord(userInput);
        } catch (DukeException e) {
            return "Oops!! I'm sorry, but I don't know what that means :(";
        }

        switch (firstWord) {
        case "bye":
            return caseBye();
        case "list":
            return caseList(tasks);
        case "listname":
            return caseListName(tasks);
        case "listdate":
            return caseListDate(tasks);
        case "todo":
            return caseToDo(storage, tasks, userInput);
        case "deadline":    // deadline description /yyyy-mm-dd
            return caseDeadline(storage, tasks, userInput);
        case "event":       // event description /yyyy-mm-dd
            return caseEvent(storage, tasks, userInput);
        case "done":
            return caseDone(tasks, userInput);
        case "delete":
            return caseDelete(storage, tasks, userInput);
        case "find":
            return caseFind(tasks, userInput);
        default:
            return "Oops!! I'm sorry, but I don't know what that means :(\n";
        }
    }

    /**
     * Says bye to the user and ends the program
     *
     * @return goodbye message
     */
    private String caseBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns list of tasks when user asks to list
     *
     * @param tasks the list of tasks
     * @return String representation of the list of tasks
     */
    private String caseList(TaskList tasks) {
        return tasks.displayGUI();
    }

    /**
     * Returns list of tasks sorted by name
     *
     * @param tasks the list of tasks
     * @return String representation of the sorted list of tasks
     */
    private String caseListName(TaskList tasks) {
        return tasks.displayGUI('n');
    }

    /**
     * Returns list of tasks sorted by date
     *
     * @param tasks the list of tasks
     * @return String representation of the sorted list of tasks
     */
    private String caseListDate(TaskList tasks) {
        return tasks.displayGUI('d');
    }

    /**
     * Creates a ToDo task
     *
     * @param storage the storage representing the database
     * @param tasks the list of tasks in memory
     * @param userInput the String the user types in
     * @return String of the confirmation message or error message
     * @throws IOException if unable to save to database
     */
    private String caseToDo(Storage storage, TaskList tasks, String userInput) throws IOException {
        if (userInput.length() > 5) {
            int original = tasks.size();
            tasks.add(userInput.substring(5), false);
            assert tasks.size() == original + 1: "Task list not updated";

            tasks.save(storage);
            return printAddedGUI(tasks);
        } else {
            return "No todo task specified!\n";
        }
    }

    /**
     * Creates a Deadline task
     *
     * @param storage the storage representing the database
     * @param tasks the list of tasks in memory
     * @param userInput the String the user types in
     * @return String of the confirmation message or error message
     * @throws IOException if unable to save to database
     */
    private String caseDeadline(Storage storage, TaskList tasks, String userInput) throws IOException {
        if (userInput.length() > 9) {
            try {
                String description = Parser.getDescription(userInput, 9);
                LocalDate date = Parser.getDate(userInput);

                int original = tasks.size();
                tasks.add('D', description, false, date);
                assert tasks.size() == original + 1: "Task list not updated";
            } catch (Exception e) {
                return "Error: incorrect format to add deadline task\n";
            }

            tasks.save(storage);
            return printAddedGUI(tasks);
        } else {
            return "No deadline task specified!\n";
        }
    }

    /**
     * Creates an Event task
     *
     * @param storage the storage representing the database
     * @param tasks the list of tasks in memory
     * @param userInput the String the user types in
     * @return String of the confirmation message or error message
     * @throws IOException if unable to save to database
     */
    private String caseEvent(Storage storage, TaskList tasks, String userInput) throws IOException {
        if (userInput.length() > 6) {
            try {
                String description = Parser.getDescription(userInput, 6);
                LocalDate date = Parser.getDate(userInput);

                int original = tasks.size();
                tasks.add('E', description, false, date);
                assert tasks.size() == original + 1: "Task list not updated";
            } catch (Exception e) {
                return "Error: incorrect format to add deadline task\n";
            }

            tasks.save(storage);
            return printAddedGUI(tasks);
        } else {
            return "Error: no deadline task specified\n";
        }
    }

    /**
     * Mark a task as done
     *
     * @param tasks list of tasks in memory
     * @param userInput String that user inputs
     * @return confirmation message of marking it as done or error message
     */
    private String caseDone(TaskList tasks, String userInput) {
        try {
            int index = Parser.getIndex(userInput, "done");
            tasks.markAsDone(index);

            return "Nice! I've marked this task as done: \n"
                    + "\t"
                    + tasks.get(index).toString()
                    + "\n";
        } catch (Exception e) {
            return "Error: invalid (out of bounds) or non-integer entered\n";
        }
    }

    /**
     * Delets a task
     *
     * @param storage storage representing the database
     * @param tasks list of tasks in memory
     * @param userInput String that user inputs
     * @return confirmation message of deletion or error message
     */
    private String caseDelete(Storage storage, TaskList tasks, String userInput) {
        try {
            int original = tasks.size();

            int index = Parser.getIndex(userInput, "delete");
            Task rm = tasks.delete(index);
            tasks.save(storage);

            assert tasks.size() == original - 1: "Task list not updated";

            return "Noted. I've removed this task: \n"
                    + "\t" + rm.toString()
                    + "Now you have "
                    + tasks.size()
                    + " tasks in the list.\n";
        } catch (Exception e) {
            return "Error: invalid (out of bounds) or non-integer entered";
        }
    }

    /**
     * Finds a list of tasks based on a keyword input by the user
     *
     * @param tasks list of tasks in memory
     * @param userInput Stirng that user inputs
     * @return Confirmation message or error message
     */
    private String caseFind(TaskList tasks, String userInput) {
        if (userInput.length() > 5) {
            String key = userInput.substring(5);
            return tasks.find(key);
        } else {
            return "No search string specified!\n";
        }
    }
}