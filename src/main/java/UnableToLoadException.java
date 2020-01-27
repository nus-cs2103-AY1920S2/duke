public class UnableToLoadException extends DukeException {
    private static final String errorLine = "   " + "**************************************************************";

    @Override
    public String toString() {
        return errorLine + "\n    ☹ Unable to load. Can check the location and tell me the right location >:(\n" + errorLine;
    }
}
