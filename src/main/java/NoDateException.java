public class NoDateException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";

    @Override
    public String toString() {
        return errorLine + "\n    ☹ OI. Provide date of the deadline with /at! >:(\n"
                + errorLine;
    }
}