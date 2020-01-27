public class InvalidTaskInputException extends DukeException {
    @Override
    public String toString() {
        return "Invalid task input format!\n" +
                "Format: e.g. deadline <activity> /by <date/ time>\n" +
                "or event <activity> /at <date/ time>";
    }
}
