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
        String ret = "Got it. I've added this task:\n";
        ret += "\t" + tasks.get(tasks.size() - 1).toString() + "\n";
        ret += "Now you have "
                + tasks.size()
                + "tasks in the list."
                + "\n";

        return ret;
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
            return "Bye. Hope to see you again soon!";
        case "list":
            return tasks.displayGUI();
        case "listname":
            return tasks.displayGUI('n');
        case "listdate":
            return tasks.displayGUI('d');
        case "todo":
            if (userInput.length() > 5) {
                int original = tasks.size();
                tasks.add(userInput.substring(5), false);
                assert tasks.size() == original + 1: "Task list not updated";

                tasks.save(storage);
                return printAddedGUI(tasks);
            } else {
                return "No todo task specified!\n";
            }
        case "deadline":    // deadline description /yyyy-mm-dd
            if (userInput.length() > 9) {
                try {
                    String description = Parser.getDescription(userInput, 9);
                    LocalDate date = Parser.getDate(userInput);

                    int original = tasks.size();
                    tasks.add('D', description, false, date);
                    assert tasks.size() == original + 1: "Task list not updated";

                    tasks.save(storage);
                    return printAddedGUI(tasks);
                } catch (Exception e) {
                    return "Error: incorrect format to add deadline task\n";
                }
            } else {
                return "No deadline task specified!\n";
            }
        case "event":   // event description /yyyy-mm-dd
            if (userInput.length() > 6) {
                try {
                    String description = Parser.getDescription(userInput, 6);
                    LocalDate date = Parser.getDate(userInput);

                    int original = tasks.size();
                    tasks.add('E', description, false, date);
                    assert tasks.size() == original + 1: "Task list not updated";


                    tasks.save(storage);
                    return printAddedGUI(tasks);
                } catch (Exception e) {
                    return "Error: incorrect format to add deadline task\n";
                }
            } else {
                return "Error: no deadline task specified\n";
            }
        case "done":
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
        case "delete":
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
                System.out.println("Error: invalid (out of bounds) or non-integer entered");
            }
        case "find":
            if (userInput.length() > 5) {
                String key = userInput.substring(5);
                return tasks.find(key);
            } else {
                return "No search string specified!\n";
            }
        default:
            return "Oops!! I'm sorry, but I don't know what that means :(\n";
        }
    }
}