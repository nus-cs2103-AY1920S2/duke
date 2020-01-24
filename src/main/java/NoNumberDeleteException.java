public class NoNumberDeleteException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";

    @Override
    public String toString() {
        return errorLine + "\n    ☹ OI. Specify which task to delete! >:(\n"
                + errorLine;
    }
}
