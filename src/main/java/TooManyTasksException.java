public class TooManyTasksException extends DukeException {
    private static final String space = "    ";

    @Override
    public String toString() {
        return "You currently have 100 tasks! \n" + space +
                "Perhaps it would be good to complete the tasks at hand. ";
    }
}
