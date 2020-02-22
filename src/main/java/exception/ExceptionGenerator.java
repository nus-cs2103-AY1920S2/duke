package exception;

/**
 * CS2103 Individual Project.
 * ExceptionGenerator checks for all the potential exceptions.
 * @author Wei Cheng
 */
public class ExceptionGenerator {
    /**
     * Check if the user inputs has the correct length.
     * @param input array of Strings.
     * @throws DukeException incorrect user's input length.
     */
    public static void checkInputLength(String[] input) throws DukeException {
        if (input.length == 1 && input[0].equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (input.length == 1 && input[0].equals("deadline")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.length == 1 && input[0].equals("event")) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
    }
    /**
     * Check if the user input is valid.
     * @param input array of String.
     * @throws DukeException user input invalid.
     */

    public static void checkInputAction(String[] input) throws DukeException {
        if (!input[0].equals("todo") && !input[0].equals("list")
                && !input[0].equals("delete") && !input[0].equals("event")
                    && !input[0].equals("deadline") && !input[0].equals("done")
                        && !input[0].equals("find") && !input[0].equals("bye")
                            && !input[0].equals("sort")) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * A static method to check for the validity of the user's date.
     * @param string date
     * @throws DukeException invalid date entered.
     */

    public static void checkDateFormat(String date) throws DukeException {
        String[] dateArray = date.split("-");
        if (dateArray.length < 3) {
            throw new DukeException("The input of date is in the wrong order. "
                    + "Please input it in the format of Year-Month-Date <3"
                        + "for example, 2020-02-20");
        }
        else if (dateArray[0].length() != 4) {
            throw new DukeException("You have entered an invalid year :(");
        }
        else if (dateArray[1].length() != 2 || dateArray[2].length() != 2 ){
            throw new DukeException("Please make sure your inputs are of the following for the month and day" +
                    "For example, 2020-02-20");
        }
        int monthValue = Integer.parseInt(dateArray[1]);
        if (monthValue > 12 || monthValue < 1) {
            throw new DukeException("Please ensure the month falls within the range of 1-12");
        }
        int dayValue = Integer.parseInt(dateArray[2]);
        if (dayValue >=31 || dayValue < 1) {
            throw new DukeException(("Please ensure the date falls within the range of 1-31"));
        }
    }

    /**
     * Check the input for the deadline task is of the correct format
     * @param input user input stored in a string array
     * @throws DukeException that the format for deadline is wrong.
     */

    public static void checkDeadlinesInput(String[] input) throws DukeException{
        if (input.length < 2) {
            throw new DukeException("Wrong format for deadline! Please ensure the format is of the following;)" +
                    "E.g deadline homework /by 2020-02-20");
        }
    }

    /**
     * Check the input for the event task is of the correct order.
     * @param input user input in a string array
     * @throws DukeException the format for event is wrong.
     */

    public static void checkEventInput(String[] input) throws DukeException {
        if (input.length < 2 ) {
            throw new DukeException("Wrong format for event! Please ensure the format is of the following;)" +
                    "E.g event homework /at 2020-02-20");
        }
    }



}
