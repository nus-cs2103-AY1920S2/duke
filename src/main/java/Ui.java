/**
 * Represents an interface to facilitate user input and output.
 */
public class Ui {
    public Ui() {}
    /**
     * Prints error message.
     *
     * @param e Exception to print.
     * @return Display message.
     */
    public String showError(Exception e) {
        return "Something went wrong: " + e.getMessage();
    }
}
