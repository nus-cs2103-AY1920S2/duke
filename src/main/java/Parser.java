public class Parser {

    public Command parse(String input) {
        String[] splitInput = input.split(" ");
        String cmd = splitInput[0];
        Command command;
        try {
            switch (cmd) {
            case "bye":
                command = new ByeCommand(cmd);
                break;
            case "list":
                command = new ListCommand();
                break;
            case "done":
                // Read the task number as the next element of splitInput
                int taskNumber = Integer.parseInt(splitInput[1]);
                command = new MarkDoneCommand(taskNumber);
                break;
            case "delete":
                int taskNumberDelete = Integer.parseInt(splitInput[1]);
                command = new DeleteCommand(taskNumberDelete);
                break;
            case "todo":
            case "deadline":
            case "event":
                command = new AddTaskCommand(input);
                break;
            default:
                break;
            }
        } catch (DukeException dukeErr) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t " + dukeErr.getMessage());
            System.out.println("\t____________________________________________________________");
        }
    }

}
