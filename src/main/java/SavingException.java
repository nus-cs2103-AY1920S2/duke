public class SavingException extends DukeException {
    @Override
    public String getMessage() {
        return "Error occurred while saving tasks to file.";
    }
}