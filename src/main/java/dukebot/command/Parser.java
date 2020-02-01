package dukebot.command;

import dukebot.ui.LineName;

import java.util.HashMap;

public class Parser {
    private enum CommandList {
        BYE("bye"),
        HELP("help"),
        LIST("list"),
        FIND("find"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private final String defaultCommand;
        CommandList(String defaultCommand) {
            this.defaultCommand = defaultCommand;
        }

        protected String getDefaultCommand() {
            return defaultCommand;
        }
    }
    private HashMap<String, CommandList> parseMap;

    /**
     * Use default commands for parser.
     */
    public Parser() {
        parseMap = new HashMap<>();
        for (CommandList command: CommandList.values()) {
            parseMap.put(command.getDefaultCommand(), command);
        }
    }

    // /**
    //  * Use saved aliases for parser.
    //  *
    //  * @param aliasMap  Key is the default command, value is alias.
    //  */
    // public Parser(HashMap<String, String> aliasMap) {
    //     parseMap = new HashMap<>();
    //     for (CommandList command: CommandList.values()) {
    //         String defaultValue = command.getDefaultCommand();
    //         if (aliasMap.containsKey(defaultValue)) {
    //             parseMap.put(aliasMap.get(defaultValue), command);
    //         } else {
    //             parseMap.put(command.getDefaultCommand(), command);
    //         }
    //     }
    // }

    /**
     * Returns command object based on input string.
     *
     * @param input  Command to parse.
     * @return Command to execute.
     */
    public Command parse(String input) {
        String[] inpArr = input.split(" ");
        String command = inpArr[0].toLowerCase();
        if (parseMap.containsKey(command)) {
            switch (parseMap.get(command)) {
            case BYE:
                return new ExitCommand();
            case HELP:
                return new UiOnlyCommand(LineName.HELP);
            case LIST:
                return new ListCommand();
            case FIND:
                return new FindCommand(inpArr);
            case DONE:
                return new DoneCommand(inpArr);
            case TODO:
                return new NewTodoCommand(inpArr);
            case DEADLINE:
                return new NewDeadlineCommand(inpArr);
            case EVENT:
                return new NewEventCommand(inpArr);
            case DELETE:
                return new DeleteCommand(inpArr);
            }
        } else {
            switch (command) {
            case "":
                return new UiOnlyCommand(LineName.NO_INPUT);
            case "Duke":
            case "duke":
            case "Master":
            case "master":
                return new UiOnlyCommand(LineName.SAY_DUKE);
            }
        }
        return new UiOnlyCommand(LineName.INVALID_COMMAND);
    }
}