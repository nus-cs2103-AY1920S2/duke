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
    }

    /**
     * Minimally check if the main command make sense to the code.
     * However, it will parse to the DukeEnumEceptions to check for other errors
     * such as IndexOutOfBounds exception, etc.
     * @param tasklist Pass in the TaskList object as an argument.
     * @return boolean true when there is no error and return false if otherwise and throws an Exception.
     */
    public boolean checkForError(TaskList tasklist) {
        String[] arguments = fullcommand.split(" ");

        try {
            // If input is not recognisable by the code
            if (!commandpairing.containsKey((arguments[0]).toLowerCase()))
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

            // Execute the error-checking
            DukeEnumExceptions.valueOf(arguments[0].toUpperCase())
                    .checkerror(fullcommand, commandpairing.get((arguments[0]).toLowerCase()), tasklist);
        } catch (DukeException ex){
            ui.showErrorMessage(ex.getMessage());
            return false;
        }

        return true;
    }

}
