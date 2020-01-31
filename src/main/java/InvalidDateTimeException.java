public class InvalidDateTimeException extends DukeException {
    @Override
    public String getMessage() {
        return "ERROR: Date/time format is wrong!";
    }
}
