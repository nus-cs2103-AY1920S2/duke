/**
 * CS2103 Individual Project
 * @author Wei Cheng
 * ExceptionGenerator checks for
 * all the potential exceptions
 */
public class ExceptionGenerator {
    /**
     * Check if the user inputs has the correct length
     * @param input array of Strings
     * @throws DukeException incorrect user's input length
     */
    public static void checkInputLength(String[] input) throws DukeException{
        if(input.length == 1 && input[0].equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if(input.length == 1 && input[0].equals("deadline")){
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        else if(input.length == 1 && input[0].equals("event")){
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

    }

    /**
     * Check if the user input is valid
     * @param input array of String
     * @throws DukeException user input invalid
     */
    public static void checkInputAction(String[] input) throws DukeException {
        if(!input[0].equals("todo") && !input[0].equals("list")
                && !input[0].equals("delete") && !input[0].equals("event")
                    && !input[0].equals("deadline") && !input[0].equals("done")){
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
