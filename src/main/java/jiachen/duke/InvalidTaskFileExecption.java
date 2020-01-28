package jiachen.duke;

public class InvalidTaskFileExecption extends DukeException {
    public InvalidTaskFileExecption() {
        super("Unable to load task file.");
    }
}
