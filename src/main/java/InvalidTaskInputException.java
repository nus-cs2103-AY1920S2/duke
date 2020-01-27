public class InvalidTaskInputException extends DukeException {
    @Override
    public String toString() {
        return "Invalid task input format!\n" +
                "Format:\n" +
                "deadline <activity> /by <date/ time>\n" +
                "or event <activity> /at <date/ time>\n" +
                "or todo <activity>\n" +
                "or done <index>";
    }
}
