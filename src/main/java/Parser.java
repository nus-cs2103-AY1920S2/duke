/**
 * Represents a Parser that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Constructor for parser object.
     */
    public Parser() {

    }

    /**
     * Parses user commands and executes them accordingly.
     * @param fullCommand user input
     * @return Command object of the user input
     * @throws DukeException throws an exception if command is undefined
     */
    public static Command parse(String fullCommand) throws DukeException{
        String[] descArr = fullCommand.split(" ");
        String command = descArr[0];
        String description = "";

        boolean hasTaskDesc = descArr.length > 1;
        boolean isByeCommand = command.equals("bye");
        boolean isDeleteCommand = command.equals("delete");
        boolean isDoneCommand = command.equals("done");
        boolean isListCommand = command.equals("list");
        boolean isAddCommand = command.equals("todo") || command.equals("deadline")
                               || command.equals("event");
        boolean isFindCommand = command.equals("find");
        boolean isUndoCommand = command.equals("undo");

        if (hasTaskDesc) {
            //get task description
            for (int i = 1; i < descArr.length - 1; i++) {
                description = description + descArr[i] + " ";
            }
            description = description + descArr[descArr.length - 1];
        }

        if (isByeCommand) {
            return new ExitCommand(command, description);
        } else if (isDeleteCommand) {
            return new DeleteCommand(command, description);
        } else if (isDoneCommand) {
            return new DoneCommand(command, description);
        } else if (isListCommand) {
            return new ListCommand(command, description);
        } else if (isAddCommand) {
            return new AddCommand(command, description);
        } else if (isFindCommand) {
            return new FindCommand(command, description);
        } else if (isUndoCommand) {
            return new UndoCommand("undo", description);
        } else {
            throw new DukeException("");
        }
    }
}
