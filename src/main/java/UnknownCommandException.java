public class UnknownCommandException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";
    @Override
    public String toString() {
        return errorLine + "\n    ☹ OI. Can you give me a proper command... >:(\n"
                + errorLine;
    }
}