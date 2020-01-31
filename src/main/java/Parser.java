/** Parses user input where necessary. */
public class Parser {

    /**
     * Generates a command word for all user input keywords.
     *
     * @param getInput user keyword input.
     * @return command for next action.
     */
    public String parse(String getInput) {
        if (getInput.equals("todo") || getInput.equals("deadline") || getInput.equals("event")) {
            return "add";
        } else {
            return getInput;
        }

    }

}
