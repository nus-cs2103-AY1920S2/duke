package duke.exception;

public class NoDateTimeException extends DukeException {

    @Override
    public String toString() {
        return " ☹ OI. Provide date and time of the event with /at! >:(";
    }
}