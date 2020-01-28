import java.util.List;

public class Parser {
    //Custom parseCommand to parse CommandTypes (if valid, and to perform the logic)
    static boolean parseCommand(String input, List<Task> taskList) {
        String[] inputBreakdown = input.split(" ", 2);
        CommandType commandType;
        String commandSuffix = null;

        try {
            //First test for valid commands from input
            try {
                commandType = CommandType.valueOf(inputBreakdown[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new UnknownCommandException();
            }

            //Second test for valid command suffixes from input, for commands that require it
            if (commandType != CommandType.BYE && commandType != CommandType.LIST) {
                try {
                    commandSuffix = inputBreakdown[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingDetailsException();
                }
            }

            switch (commandType) {
                case BYE:
                    return Command.byeCommand(taskList);

                case CALENDAR:
                    Command.calendarCommand(taskList, commandSuffix);
                    break;

                case DEADLINE:
                    Command.deadlineCommand(taskList, commandSuffix);
                    break;

                case DELETE:
                    TaskList.deleteTask(taskList, commandSuffix);
                    break;

                case DONE:
                    TaskList.doneTask(taskList, commandSuffix);
                    break;

                case EVENT:
                    Command.eventCommand(taskList, commandSuffix);
                    break;

                case LIST:
                    Command.listCommand(taskList);
                    break;

                case TODO:
                    Command.todoCommand(taskList, commandSuffix);
                    break;

                default:
                    break;
            }
        } catch (DukeException e) {
            UI.print(e.toString());
        }

        return true;
    }
}
