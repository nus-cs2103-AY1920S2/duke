/**
 * Handles making sense of the user inputs to return specific commands.
 */
public class Parser {

    /**
     * Parses the user input to return a corresponding command message.
     * @param fullCommand User's given input.
     * @return Returns a Command type.
     * @throws DukeException When user's inputs do not fulfill any of the command specifications.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split("\\s+", 2);
        String command = arr[0];
        if (command.equals("todo")) {
            if (arr.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                Task task = new Todo(arr[1]);
                return new AddCommand(task);
            }
        } else if (command.equals("deadline")) {
            if (arr.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String[] deadlineArr = arr[1].split(" /by ", 2);
                if (deadlineArr.length == 1) {
                    throw new DukeException("☹ OOPS!!! You forgot to specify a date/time for the deadline.");
                } else {
                    Task task = new Deadline(deadlineArr[0], deadlineArr[1]);
                    return new AddCommand(task);
                }
            }
        } else if (command.equals("event")) {
            if (arr.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } else {
                String[] eventArr = arr[1].split(" /at ", 2);
                if (eventArr.length == 1) {
                    throw new DukeException("☹ OOPS!!! You forgot to specify a date/time for the event.");
                } else {
                    Task task = new Event(eventArr[0], eventArr[1]);
                    return new AddCommand(task);
                }
            }
        } else if (command.equals("done")) {
            return new DoneCommand(Integer.parseInt(arr[1]));
        } else if (command.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(arr[1]));
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("find")) {
            return new FindCommand(arr[1]);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
