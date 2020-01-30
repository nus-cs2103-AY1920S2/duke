/**
 * This class handles an exception within the Todo class.
 * It is thrown when unexpected input occurs during the
 * use of the Todo class. It is a child of the DukeException class.
 */

public class TodoException extends DukeException {

    /**
     * Constructor for the customer class.
     * @param input is the error message if any.
     * */

    private String input;
    public TodoException(String input) {
        super(input);
    };

    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                " ☹ OOPS!!! You must specify a Task to be done!\n" +
                "\n Please enter as: 'todo <msg>', where msg is your description \n" +
                "____________________________________________________________\n";
    }
}
