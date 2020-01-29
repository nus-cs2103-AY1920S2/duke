public class DukeException extends IllegalArgumentException {
    protected String errorMsg = "";

    @Override
    public String toString() {
        String text = "    ____________________________________________________________\n"
                + "     " + errorMsg + "\n"
                + "    ____________________________________________________________\n";
        return text;
    }
}
