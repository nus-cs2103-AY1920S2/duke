package duke.exception;

public class DukeException extends Exception{
    public final String description;

    public DukeException (String description){
        this.description = description;
    }
}
