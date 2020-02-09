package lcduke;

public class Parser {
    static boolean isProblem = false;

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
        if (!userInput.contains("bye") && !userInput.contains("list") && !userInput.contains("done 2")
                && !userInput.contains("delete") && !userInput.contains("todo")
                && !userInput.contains("deadline") && !userInput.contains("event")) {
            System.out.println("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________\n");
            throw new DukeException("I don't know what that means");

        } else if (!userInput.contains(" ") && !userInput.contains("bye") && !userInput.contains("list")){
            System.out.println("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description of a " + userInput + " cannot be empty.\n"
                    + "    ____________________________________________________________\n");
            throw new DukeException("Empty");
        }
    }
}
