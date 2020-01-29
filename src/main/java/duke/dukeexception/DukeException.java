package dukeexception;
/**
 * Represents the error when the user inputs an invalid command. A <code>DukeException</code> object corresponds to a
 * an error caused by invalid command e.g., <code>"todo"</code>
 */
public class DukeException extends Exception {
    protected String exception;
    public DukeException(String exception){
        this.exception = exception;
    }
    /**
     * Displays the error message.
     * @return The error message.
     */
    @Override
    public String getMessage(){
        if (exception.equals("todo")){
            return "      OOPS!!! The description of a todo cannot be empty.";
        } else {
            return "      OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
