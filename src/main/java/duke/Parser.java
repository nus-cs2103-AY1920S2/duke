package duke;

public class Parser {
    /**
     * Parses a single string command from the user.
     * @param fullCommand The the whole line of a user's input as read from Standard Input
     * @return A Command that is capable of mutating the passed in TaskList, Ui, and Storage
     * @throws DukeException a specialized DukeException that can be printed to the user.
     */
    public static Command parse(String fullCommand) throws DukeException {
        int spaceIndex = fullCommand.indexOf(" ");

        if (spaceIndex == -1) {
            // full command is only 1 word
            if (fullCommand.equals("list")) {
                return new ListCommand();
            } else if (fullCommand.equals("bye")) {
                return new ExitCommand();
            } else if (fullCommand.equals("todo")) {
                throw new EmptyToDoException();
            } else {
                throw new UndefinedCommandException();
            }
            // TODO add exception for invalid find method
        } else {
            String firstArg = fullCommand.substring(0, spaceIndex);
            String otherArgs = fullCommand.substring(spaceIndex + 1);

            if (firstArg.equals("todo")) {
                return new AddCommand(new Todo(otherArgs));
            } else if (firstArg.equals("deadline")) {
                String[] splitby = otherArgs.split(" /by ");    //note surrounding spaces
                return new AddCommand(new Deadline(splitby[0], splitby[1]));
            } else if (firstArg.equals("event")) {
                String[] splitat = otherArgs.split(" /at ");    //note surrounding spaces
                return new AddCommand(new Event(splitat[0], splitat[1]));
            } else if (firstArg.equals("done")) {
                // assumes command is only "done" and an int
                // the input is 1-indexed. duke.DoneCommand takes in 0-indexed
                return new DoneCommand(Integer.parseInt(otherArgs) - 1);
            } else if (firstArg.equals("delete")) {
                // assumes command is only "delete" and an int
                // the input is 1-indexed. duke.DeleteCommand takes in 0-indexed
                return new DeleteCommand(Integer.parseInt(otherArgs) - 1);
            } else if (firstArg.equals("find")) {
                // assumes command is only "find" and a single word search phrase
                // currently will search the remainder of the command
                return new FindCommand(otherArgs);
            } else {
                throw new UndefinedCommandException();
            }
        }
    }
}
