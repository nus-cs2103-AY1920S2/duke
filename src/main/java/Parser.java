/**
 * Parser class allows the program to parse the instruction and act accordingly to the instruction.
 */
public class Parser {

    /**
     * creates a Parser class.
     */
    public Parser() {

    }

    /**
     * Parse the instruction input.
     * @param response full instruction that is given.
     * @return a Command object that is associated with the instruction.
     * @throws DukeException if the instruction does not match any of the programmed one.
     */
    public Command parse(String response) throws DukeException {

        String messageType = checkMessageType(response);

        if (messageType.equals("delete"))  {

            int numberToDelete = Integer.parseInt(response.replace("delete" , "").trim());

            return new DeleteCommand(response, numberToDelete);

        } else if (messageType.equals("bye")) {

            return new ExitCommand(response);

        } else if (messageType.equals("todo") || messageType.equals("deadline") || messageType.equals("event")) {

            return new AddCommand(response, messageType);

        } else if (messageType.equals("done")) {

            int taskNumberDone = Integer.parseInt(response.split(" ")[1]);
            return new DoneCommand(response, taskNumberDone);

        } else if (messageType.equals("list")) {

            return new ListCommand(response);

        } else {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }


    }

    /**
     * Checks the message type.
     * @param response full instruction that is given.
     * @return the type of instruction that the user inputted.
     * @throws DukeException if the instruction does not match any of the pre-assigned ones.
     */
    public String checkMessageType(String response) throws DukeException {

        if (response.contains("bye")) {
            return "bye";
        }
        if (response.contains("list")) {
            return "list";
        }

        if (response.contains("done")) {
            return "done";
        }

        if (response.contains("todo")) {
            return "todo";
        }

        if (response.contains("deadline")) {
            return "deadline";
        }

        if (response.contains("event")) {
            return "event";
        }

        if (response.contains("delete")) {
            return "delete";
        }

        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
