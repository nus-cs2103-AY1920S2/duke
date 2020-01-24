public class NoSuchDeleteException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";

    @Override
    public String toString() {
        return errorLine + "\n    ☹ OI. Check properly... Where got such task. >:(\n"
                + errorLine;
    }
}