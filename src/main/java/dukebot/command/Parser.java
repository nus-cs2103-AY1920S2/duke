package dukebot.command;

import dukebot.LineName;

public class Parser {
    public static Command parse(String input) {
        String[] inpArr = input.split(" ");
        switch (inpArr[0]) {
        case "":
            return new uiOnlyCommand(LineName.NO_INPUT);
        case "Duke":
        case "duke":
        case "Master":
        case "master":
            return new uiOnlyCommand(LineName.SAY_DUKE);
        case "bye":
            return new exitCommand();
        case "list":
            return new listCommand();
        case "done":
            return new doneCommand(inpArr);
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new newTaskCommand(inpArr);
        case "delete":
            return new deleteCommand(inpArr);
        default:
            return new uiOnlyCommand(LineName.INVALID_COMMAND);
        }
    }
}