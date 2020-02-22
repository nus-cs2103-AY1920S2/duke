package liaomeng.duke;

public class DukeException extends Exception {
    public DukeException(String message) {
        super("An Error occurred. Please refer to the following message:\n" + message);
    }
}
