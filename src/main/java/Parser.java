public class Parser {

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
        case "todo":
        case "deadline":
        case "event":
            command = new AddTaskCommand(cmd, input);
            break;
        default:
            break;
        }
        return command;
    }

}
