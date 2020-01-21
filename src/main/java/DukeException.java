public class DukeException extends Exception {
    public final DukeExceptionType type;

    public DukeException(DukeExceptionType t) {
        this.type = t;
    }

    @Override
    public String toString() {
        if(this.type == DukeExceptionType.UNKNOWNCOMMAND) {
            return "*************************************************************\n" 
                    + "☹ Can you give me a proper command... >:("
                    + "\n*************************************************************";
        } else if (this.type == DukeExceptionType.NODATETIME) {
            return "*************************************************************\n"
                    + "☹ Please also provide date and time of the event! >:("
                    + "\n*************************************************************";
        } else if (this.type == DukeExceptionType.NODATE) {
            return "*************************************************************\n"
                    + "☹ Please also provide date of the deadline! >:("
                    + "\n*************************************************************";
        } else {
            return "*************************************************************\n"
                    + "☹ Please also provide the description of the task! >:("
                    + "\n*************************************************************";
        }
    }
}

