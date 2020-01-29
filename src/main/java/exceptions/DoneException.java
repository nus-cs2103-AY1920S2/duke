package exceptions;

public class AlreadyDoneException extends DukeException {

    /**
     * Constructor for DeadlineException
     *
     * @param message any message to convey
     */
    public AlreadyDoneException(String message) {
        printLine();
        print("Seems like you are kinda tired. Please remember to define a Task Number!\n"
                + "Or, you could also take a break. :)\n" + message);
        printLine();
    }

}
