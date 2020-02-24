public class DukeException {

    public DukeException() {
    }

    /**
     * This method return the Error Message for Incorrect Input.
     * @return String for error message.
     */
    public String incorrectInputTodo() {
        return "OOPS!!! The description of a todo cannot be empty.\nPlease insert Action\n";
    }

    /**
     * This method return the Error Message for Incorrect Input for a Deadline Task.
     * @return String for error message.
     */
    public String incorrectInputDeadline() {
        return "OOPS!!! The description of a Deadline cannot be empty.\nPlease insert Action\n";
    }

    /**
     * This method return the Error Message for Incorrect Input for a Event Task.
     * @return String for error message.
     */
    public String incorrectInputEvent() {
        return "OOPS!!! The description of a Event cannot be empty.\nPlease insert Action\n";
    }

    /**
     * This method return the Error Message for Missing Date in Deadline Task.
     * @return String for error message.
     */
    public String deadlineMissingDate() {
        return "OOPS!!! Deadline is missing a Date!\nPlease input Date\n";
    }

    /**
     * This method return the Error Message for Missing Date in Event Task.
     * @return String for error message.
     */
    public String eventMissingDate() {
        return "OOPS!!! Event is missing a Date!\nPlease input Date\n";
    }

    /**
     * This method return the Error Message for Invalid Input.
     * @return String for error message.
     */
    public String invalidInput() {
        return "OOPS!!! I'm sorry, but I don't know what that means.\n";
    }

    /**
     * This method return the Error Message for Invalid Number.
     * @return String for error message.
     */
    public String exceedList() {
        return "Invalid Number. Please Enter a valid number.\n";
    }

    /**
     * This method return the Error Message for Invalid Date Format.
     * @return String for error message.
     */
    public String invalidDateFormat() {
        return "Invalid Date Format! Enter in /by dd/mm/yyyy\n";
    }

    /**
     * This method return the Error Message for Invalid Time Format.
     * @return String for error message.
     */
    public String invalidTimeFormat() {
        return "Invalid Date Format! Enter in hhmm\n";
    }

    /**
     * This method return Error Message for No Matched Found.
     * @return String for error message.
     */
    public String noMatchesFound() {
        return "What you are looking for does not exist.\n";
    }

    /**
     * This method return the Error Message for Missing Done Index.
     * @return String for error message.
     */
    public String missingDoneIndex() {
        return "OOPS you're missing an Index.";
    }


}
