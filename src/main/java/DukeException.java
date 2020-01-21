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
        }
        return "asd";
    }
}

//"  ☹ Can you give me a proper command... >:(";