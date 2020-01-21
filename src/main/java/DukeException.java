public class DukeException extends Exception {
    public final DukeExceptionType type;

    public DukeException(DukeExceptionType t) {
        this.type = t;
    }

    @Override
    public String toString() {
        if(this.type == DukeExceptionType.UNKNOWNCOMMAND) {
            return "*************************************************************\n" 
                    + "☹ OI. Can you give me a proper command... >:("
                    + "\n*************************************************************";
        } else if (this.type == DukeExceptionType.NODATETIME) {
            return "*************************************************************\n"
                    + "☹ OI. Provide date and time of the event! >:("
                    + "\n*************************************************************";
        } else if (this.type == DukeExceptionType.NODATE) {
            return "*************************************************************\n"
                    + "☹ OI. Provide date of the deadline! >:("
                    + "\n*************************************************************";
        } else if (this.type == DukeExceptionType.NOSUCHDELETE) {
            return "*************************************************************\n"
                    + "☹ OI. Check properly... Where got such task. >:("
                    + "\n*************************************************************";
        } else if (this.type == DukeExceptionType.NONUMBERDELETE) {
            return "*************************************************************\n"
                    + "☹ OI. Specify which task to delete! >:("
                    + "\n*************************************************************";
        } else {
            return "*************************************************************\n"
                    + "☹ OI. Provide the description of the task! >:("
                    + "\n*************************************************************";
        }
    }
}

