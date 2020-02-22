
import exception.DukeException;
/**
 * CS2103 Individual Project.
 * UI handles all the interaction with the user.
 * @author Wei Cheng
 */

public class Ui {

    /**
     * Greet the user.
     */

    public static void initiateGreetings() {
        System.out.println("Hello! I'm Duke\n"
                + "     What can I do for you?");
    }

    /**
     * Tell the user that the programme has finished loading.
     */

    public static void loadingCompleted() {

        System.out.println("FINISH loading data");
    }

    /**
     * Print out the message from Duke to the user.
     * @param message message from Duke.
     */

    public void transmitMessage(String message) {

        System.out.println(message);
    }

    /**
     * Output all the error messages.
     * @param err Exceptions being thrown.
     */

    public void showLoadingError(DukeException err) {

        err.printStackTrace();
    }

    /**
     * Says goodbye to the user.
     */

    public static String initiateFareWell() {

        return ("Bye. Hope to see you again soon!");
    }
}
