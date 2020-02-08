/**
 * Represents a object that makes sense of the user input into the program.
 */
public class Parser {

    /**
     * Returns a Command object that is generated from the user's input into the program.
     * The type of Command object that is returned depends on the user's input.
     * Throws a NullPointerException if the user's input is not of the correct format.
     * @param input the input String from the user into the program.
     * @return a Command object based on the user's input.
     * @throws NullPointerException thrown if the user input is not in the correct format.
     */
    public Command parse(String input) throws NullPointerException {
        String[] splitInput = input.split(" ");
        String cmd = splitInput[0];
        Command command = null;
        switch (cmd) {
        case "bye":
            command = new ByeCommand(cmd);
            break;
        case "list":
            command = new ListCommand(cmd);
            break;
        case "done":
            // Read the task number as the next element of splitInput
            int taskNumber = Integer.parseInt(splitInput[1]);
            command = new MarkDoneCommand(cmd, taskNumber);
            break;
        case "delete":
            int taskNumberDelete = Integer.parseInt(splitInput[1]);
            command = new DeleteCommand(cmd, taskNumberDelete);
            break;
        case "find":
            command = new FindCommand(input);
            break;
        case "todo":
        case "deadline":
        case "event":
            command = new AddTaskCommand(cmd, input);
            break;
        default:
            command = new OtherCommand(input);
            break;
        }
        return command;
    }

}
