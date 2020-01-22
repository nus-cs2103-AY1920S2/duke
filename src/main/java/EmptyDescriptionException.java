public class EmptyDescriptionException extends DukeException {
    private static final String space = "    ";

    @Override
    public String toString() {
        return "You are required to specify the description for the task. \n" + space +
                "Please try again.";
    }
}
