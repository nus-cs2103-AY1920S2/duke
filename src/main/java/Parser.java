public class Parser {

    static Command parse(String command) {
        String[] arrOfCommands = command.split(" ");
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (arrOfCommands[0].equals("done")) {
            int num = Integer.parseInt(arrOfCommands[1]);
            return new DoneCommand(num);
        } else if (command.startsWith("deadline")) {
            return new AddCommand(command);
        } else if (command.startsWith("todo")) {
            return new AddCommand(command);
        } else if (command.startsWith("event")) {
            return new AddCommand(command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        }
        return null;
    }
}
