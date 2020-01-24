public class NoDescriptionException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";

    @Override
    public String toString() {
        return errorLine + "\n    ☹ OI. Provide the description of the task! >:(\n"
                + errorLine;
    }
}