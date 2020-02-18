package seedu.duke.exception;

public class InvalidInputFormatException extends DukeException {
    @Override
    public String toString() {
        return "Invalid input format!\n"
                + "Format:\n"
                + "- deadline <description> /by <due date in yyyy-mm-dd>\n"
                + "- event <description> /at <date in yyyy-mm-dd>\n"
                + "- todo <description>\n"
                + "- done <index>\n"
                + "- delete <index>\n"
                + "- find <keyword>";
    }
}
