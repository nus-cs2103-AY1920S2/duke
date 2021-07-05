public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "We're running short of time, so make sure you note it down.\n"
                + "Give a short description, then\n"
                + "have the deadline as </yyyy-mm-dd>.";
    }
}
