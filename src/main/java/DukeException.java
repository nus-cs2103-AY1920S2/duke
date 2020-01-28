public class DukeException extends Exception {

    protected String description;
    private static final String INDENT = "    ";
    private static final String HOR_LINE = "___________________________" +
            "_________________________________";

    public DukeException(String e) {
        super(e);
        this.description = e;
    }
}
