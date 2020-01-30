/**
 * This class is the general exception that is thrown when
 * Duke is being executed. It is thrown when unexpected input
 * is received from the user.
 */

public class DukeException extends Exception {

    /**
     * Constructor for the customer class.
     * @param msg is the error message if any.
     * */
    public DukeException(String msg){
        super(msg);
    }

    public String toString() {
        return "____________________________________________________________\n" +
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________\n";
    }

    public String fileErrorMessage(){
        return "The file is not found";
    }
}
