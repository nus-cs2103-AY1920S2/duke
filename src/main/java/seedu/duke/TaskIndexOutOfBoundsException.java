package seedu.duke;

public class TaskIndexOutOfBoundsException extends DukeException {
    @Override
    public String toString() {
        return "You keyed in an invalid index!";
    }
}
