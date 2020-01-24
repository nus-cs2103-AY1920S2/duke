public class NoNumberDeleteException extends DukeException {

    @Override
    public String toString() {
        return "*************************************************************\n"
                + "☹ OI. Specify which task to delete! >:("
                + "\n*************************************************************";
    }
}