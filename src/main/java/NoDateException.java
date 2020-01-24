public class NoDateException extends DukeException {

    @Override
    public String toString() {
        return "*************************************************************\n"
            + "☹ OI. Provide date of the deadline! >:("
            + "\n*************************************************************";
    }
}