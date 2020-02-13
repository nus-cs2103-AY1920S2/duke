package duke.command;

public class ByeCommand implements Command {
    /**
     * ByeCommand literally returns BYE to the parser,
     * (with the required Command interface implementation of the run() method.
     *
     * @return  "BYE" to the Parser, which will allow Duke.getResponse() to
     *          execute the abstracted Duke.exitDuke() method to terminate the Duke application.
     */
    public static String run() {
        return ("BYE");
    }
}
