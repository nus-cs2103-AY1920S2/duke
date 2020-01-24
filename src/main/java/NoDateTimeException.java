public class NoDateTimeException extends DukeException {

    @Override
    public String toString() {
        return "*************************************************************\n"
            + "☹ OI. Provide date and time of the event! >:("
            + "\n*************************************************************";
    }
}