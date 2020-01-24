public class UnknownCommandException extends DukeException {

    @Override
    public String toString() {
        return "*************************************************************\n"
                + "☹ OI. Can you give me a proper command... >:("
                + "\n*************************************************************";
    }
}