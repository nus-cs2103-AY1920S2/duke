package lcduke;

/** Ths creates a Parser object, which is to check exception of user's input.
 */

public class Parser {
    static boolean isProblem = false;

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
                && !userInput.contains("deadline") && !userInput.contains("event") && !userInput.contains("find")) {
            System.out.println("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________\n");
            throw new DukeException("I don't know what that means");

        } else if (!userInput.contains(" ") && !userInput.contains("bye") && !userInput.contains("list")
                && !userInput.contains("reminders")){
            System.out.println("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description of a " + userInput + " cannot be empty.\n"
                    + "    ____________________________________________________________\n");
            throw new DukeException("Empty");
        }
    }
}
