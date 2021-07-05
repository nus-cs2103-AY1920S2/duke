public class InvalidEventException extends DukeException {
    public InvalidEventException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "We're running short of time, so make sure you note it down.\n"
                + "Give a short description, then\n"
                + "have the deadline as </yyyy-mm-dd>.";
    }
}
