/**
 * The Parser class is used to make sense of the user's input by calling different commands based
 * on the first word input of the user.
 */
class Parser {
    /**
     * A static method used to parse the user's commands and
     * calls different commands based on input.
     *
     * @param command The user's String input is parsed to call different Commands.
     * @return A Command based on input.
     */
    static Command parse(String command) {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done")) {
            String[] arrOfCommands = command.split(" ");
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
        } else if (command.startsWith("find")) {
            return new FindCommand(command);
        }
        return null;
    }
}
