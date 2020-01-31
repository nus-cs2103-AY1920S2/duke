package dukebot.command;

import dukebot.ui.LineName;

public class Parser {

    /**
     * Returns command object based on input string.
     *
     * @param input  Command to parse.
     * @return Command to execute.
     */
    public static Command parse(String input) {
        String[] inpArr = input.split(" ");
        String command = inpArr[0].toLowerCase();
        switch (command) {
        case "":
            return new UiOnlyCommand(LineName.NO_INPUT);
        case "Duke":
        case "duke":
        case "Master":
        case "master":
            return new UiOnlyCommand(LineName.SAY_DUKE);
        case "bye":
            return new ExitCommand();
        case "help":
            return new UiOnlyCommand(LineName.HELP);
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(inpArr);
        case "done":
            return new DoneCommand(inpArr);
        case "todo":
            return new NewTodoCommand(inpArr);
        case "deadline":
            return new NewDeadlineCommand(inpArr);
        case "event":
            return new NewEventCommand(inpArr);
        case "delete":
            return new DeleteCommand(inpArr);
        default:
            return new UiOnlyCommand(LineName.INVALID_COMMAND);
        }
    }
}