import java.time.format.DateTimeParseException;
import java.util.HashMap;
/**
 * Parser class.
 * Handles the command input by the user.
 *
 * @author Amos Cheong
 */
public class Parser {

    private Ui ui;
    private String fullcommand;
    private HashMap<String, String> commandpairing = new HashMap<String, String>();

    public Parser (String fullcommand, Ui ui) {
        this.fullcommand = fullcommand;
        this.ui = ui;

        // Add all valid commands into HashMap
        // HashMap is used for validation
        commandpairing.put("list", "1");
        commandpairing.put("done", "2");
        commandpairing.put("todo", "2");
        commandpairing.put("event", "/at");
        commandpairing.put("deadline", "/by");
        commandpairing.put("delete", "2");
        commandpairing.put("find", "2");

        assert !commandpairing.isEmpty() : "Error adding key-value objects in HashMap";
    }

    /**
     * Find out the type of command and execute it.
     *
     * @param input The line input by the user.
     * @param tasks TaskList object to acces to list of tasks.
     * @return String message that Duke has to tell the user.
     */
    public String executeCommmand(String input, TaskList tasks, Ui ui) {
        // Split arguments to get the first index
        String[] arguments = input.split(" ");

        return DukeCommand.valueOf(arguments[0].toUpperCase()).execute(input, tasks, ui);
    }

    public String parseCommand(TaskList tasks) {
        String message = this.detectError(tasks);

        if (message.equals("")) {
            // Run this line of code when there is no error with the command
            // But when specific tasks (Event/Deadline) is added, the date
            // and time will be validated here. Therefore, it will still
            // print out error code when the date or time format input
            // is incorrect
            message = this.executeCommmand(this.fullcommand, tasks, ui);
        }

        return message;
    }

    /**
     * Minimally check if the main command make sense to the code.
     * However, it will parse to the DukeEnumEceptions to check for other errors
     * such as IndexOutOfBounds exception, etc.
     *
     * @param tasklist Pass in the TaskList object as an argument.
     * @return boolean true when there is no error and return false if otherwise and throws an Exception.
     */
    public String detectError(TaskList tasklist) {
        String[] arguments = fullcommand.split(" ");
        String myMessage = "";
        try {
            // If input is not recognisable by the code
            if (!commandpairing.containsKey((arguments[0]).toLowerCase()))
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            // Execute the error-checking
            DukeEnumExceptions.valueOf(arguments[0].toUpperCase())
                    .checkError(fullcommand, commandpairing.get((arguments[0]).toLowerCase()), tasklist);
        } catch (DukeException ex){
            myMessage = ex.getMessage();
        } catch (DateTimeParseException exception) {
            myMessage = "Input date in the form of yyy-mm-dd and " +
                    "remember to add time in 24-hour format \n" + exception.getMessage();
        } finally {
            return myMessage;
        }

    }
}
