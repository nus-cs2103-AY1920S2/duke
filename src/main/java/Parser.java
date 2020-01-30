public class Parser {
    public static Command parse(String command) {
        try {
            String firstWord = command.split(" ")[0];

            switch (firstWord) {
                case "bye":
                    return new ExitCommand();
                case "deadline":
                    return parseDeadline(getDetails(command, firstWord));
                case "delete":
                    return parseDeleteTask(getDetails(command, firstWord));
                case "done":
                    return parseCompleteTask(getDetails(command, firstWord));
                case "event":
                    return parseEvent(getDetails(command, firstWord));
                case "list":
                    return new ShowTasksCommand();
                case "todo":
                    return parseTodo(getDetails(command, firstWord));
                default:
                    return new InvalidCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseDeadline(String details) throws IndexOutOfBoundsException{
        String description = details.split(" /by ")[0];
        String deadline = details.split(" /by ")[1];

        return new AddDeadlineCommand(description, deadline);
    }

    private static Command parseEvent(String details) throws IndexOutOfBoundsException{
        String description = details.split(" /at ")[0];
        String time = details.split(" /at ")[1];

        return new AddEventCommand(description, time);
    }

    private static Command parseTodo(String details) {
        String description = details;
        if (!description.isEmpty()) {
            return new AddTodoCommand(description);
        } else {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseDeleteTask(String details) {
        try {
            int index = Integer.parseInt(details) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    private static Command parseCompleteTask(String details) {
        try {
            int index = Integer.parseInt(details) - 1;
            return new CompleteTaskCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    private static String getDetails(String command, String firstWord) throws IndexOutOfBoundsException {
        return command.split(firstWord)[1].trim();
    }
}
