public class Parser {
    public Command parse(String command) {
        String firstWord = command.split(" ")[0];
        String details = command.split(firstWord)[0].trim();

        switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "deadline":
                return parseDeadline(details);
            case "delete":
                return parseDeleteTask(details);
            case "done":
                return parseCompleteTask(details);
            case "event":
                return parseEvent(details);
            case "list":
                return new ShowTasksCommand();
            case "todo":
                return parseTodo(details);
            default:
                return new InvalidCommand();
        }
    }

    private Command parseDeadline(String details) {
        try {
            String description = details.split(" /by ")[0];
            String deadline = details.split(" /by ")[1];

            return new AddDeadlineCommand(description, deadline);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidFormatCommand();
        }
    }

    private Command parseEvent(String details) {
        try {
            String description = details.split(" /by ")[0];
            String time = details.split(" /at ")[1];

            return new AddEventCommand(description, time);
        } catch (IndexOutOfBoundsException e) {
            return new InvalidFormatCommand();
        }
    }

    private Command parseTodo(String details) {
        String description = details;
        if (!description.isEmpty()) {
            return new AddTodoCommand(description);
        } else {
            return new InvalidFormatCommand();
        }
    }

    private Command parseDeleteTask(String details) {
        try {
            int index = Integer.parseInt(details);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }

    private Command parseCompleteTask(String details) {
        try {
            int index = Integer.parseInt(details);
            return new CompleteTaskCommand(index);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand();
        }
    }
}
