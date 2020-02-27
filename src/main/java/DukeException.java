//package duke;

public class DukeException extends Exception {
    private String type;

    public DukeException (String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}