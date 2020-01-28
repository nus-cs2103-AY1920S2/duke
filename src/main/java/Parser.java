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
     * Method for making sense of the user command.
     * @param fullCommand user input
     * @return Command object of the user input
     * @throws DukeException throws an exception if command is undefined
     */
    public static Command parse(String fullCommand) throws DukeException{
        String[] arr = fullCommand.split(" ");
        String command = arr[0];
        String description = "";

        if(arr.length > 1) {
            //get task description
            for(int i = 1; i < arr.length - 1; i++) {
                description = description + arr[i] + " ";
            }
            description = description + arr[arr.length - 1];
        }

        System.out.println(description);

        if(command.equals("bye")) {
            return new ExitCommand(command, description);
        } else if(command.equals("delete")) {
            return new DeleteCommand(command, description);
        } else if(command.equals("done")) {
            return new DoneCommand(command, description);
        } else if(command.equals("list")) {
            return new ListCommand(command, description);
        } else if(command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            return new AddCommand(command, description);
        } else {
            throw new DukeException("");
        }
    }
}
