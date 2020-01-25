public class NoDateTimeException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";

    @Override
    public String toString() {
        return errorLine + "\n    ☹ OI. Provide date and time of the event with /at! >:(\n"
                + errorLine;
    }
}