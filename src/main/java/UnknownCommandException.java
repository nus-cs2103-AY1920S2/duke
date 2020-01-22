public class UnknownCommandException extends DukeException {
    private static final String space = "    ";

    @Override
    public String toString() {
        return "Unfortunately, I do not understand the command that you have just entered. \n" +
                space + "Please try once again.";
    }
}
