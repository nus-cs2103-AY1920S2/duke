public class UnknownCommandException extends DukeException {
    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ OI. Can you give me a proper command... >:(\n"
                + Constant.ERROR_LINE;
    }
}