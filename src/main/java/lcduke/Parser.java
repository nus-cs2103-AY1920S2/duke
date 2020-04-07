package lcduke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/** Ths creates a Parser object, which is to check exception of user's input.
 */
public class Parser {
    protected static boolean isProblem = false;
    protected static String errorMessage;

    /** This is the constructor to create the Storage Object.
     *
     * @param userInput Input by the user.
     */
    protected Parser(String userInput) {
        try {
            testMessage(userInput);
        } catch (DukeException ex) {
            isProblem = true;
            errorMessage = ex.getMessage();
        }
        if (userInput.contains("/at") || (userInput.contains("/by"))) {
            try {
                testDateFormat(userInput);
            } catch (DukeException ex) {
                isProblem = true;
                errorMessage = ex.getMessage();
            }
        }
    }

    protected boolean getIsProblem() {
        return isProblem;
    }

    private void testMessage(String userInput) throws DukeException {
        if (!userInput.contains("bye") && !userInput.contains("list") && !userInput.contains("done")
                && !userInput.contains("delete") && !userInput.contains("todo") && !userInput.contains("reminders")
                && !userInput.contains("deadline") && !userInput.contains("event") && !userInput.contains("find")
                && !userInput.contains("hi")) {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");

        } else if (!userInput.contains(" ") && !userInput.contains("bye") && !userInput.contains("list")
                && !userInput.contains("reminders") && !userInput.contains("hi")) {
            throw new DukeException("     ☹ OOPS!!! The description of a " + userInput + " cannot be empty.\n");
        }
    }

    /** Ths is used to test the validity of the date format.
     *
     * @param message user's input message
     */
    public void testDateFormat(String message) throws DukeException {
        String dt = null;
        if (message.contains("/by")) {
            dt = message.substring(message.indexOf("/by") + 4, message.length());
        } else if (message.contains("/at")) {
            dt = message.substring(message.indexOf("/at") + 4, message.length());
        }
        if (dt == null) {
            throw new DukeException("date and time is empty");
        }

        if (dt.contains(" ")) {
            dt = dt.replace(" ", "T");
        } else {
            throw new DukeException("date format is wrong");
        }

        try {
            LocalDateTime.parse(dt);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format is wrong");
        }
    }
}
