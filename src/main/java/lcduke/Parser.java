package lcduke;

/** Ths creates a Parser object, which is to check exception of user's input.
 */
public class Parser {
    static boolean isProblem = false;
    static String errorMessage;

    /** This is the constructor to create the Storage Object.
     *
     * @param userInput Input by the user.
     */
    public Parser(String userInput) {
        try {
            testMessage(userInput);
        } catch (DukeException ex) {
            isProblem = true;
        }
    }

    public boolean getIsProblem(){
        return isProblem;
    }

    public void testMessage(String userInput) throws DukeException {
        if (!userInput.contains("bye") && !userInput.contains("list") && !userInput.contains("done")
                && !userInput.contains("delete") && !userInput.contains("todo") && !userInput.contains("reminders")
                && !userInput.contains("deadline") && !userInput.contains("event") && !userInput.contains("find")
                && !userInput.contains("hi")) {
            errorMessage = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            throw new DukeException("I don't know what that means");

        } else if (!userInput.contains(" ") && !userInput.contains("bye") && !userInput.contains("list")
                && !userInput.contains("reminders") && !userInput.contains("hi")){
            errorMessage = "     ☹ OOPS!!! The description of a " + userInput + " cannot be empty.\n";
            throw new DukeException("Empty");
        }
    }
}
