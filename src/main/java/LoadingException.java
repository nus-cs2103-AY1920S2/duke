public class LoadingException extends DukeException {
    @Override
    public String getMessage() {
        return "Error occurred while loading tasks from file.";
    }
}
