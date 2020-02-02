package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.ui.LineName;

import java.util.HashMap;

public class Parser {
    private HashMap<String, CommandList> aliasMap;

    /**
     * Use default commands for parser.
     */
    public Parser() {
        aliasMap = new HashMap<>();
        for (CommandList command: CommandList.values()) {
            aliasMap.put(command.getDefaultCommand(), command);
        }
    }

    /**
     * Use saved aliases for parser.
     *
     * @param loadedAliasMap  Key is the default command, value is alias.
     */
    public Parser(HashMap<String, String> loadedAliasMap) {
        aliasMap = new HashMap<>();
        for (CommandList command: CommandList.values()) {
            String defaultValue = command.getDefaultCommand();
            if (loadedAliasMap.containsKey(defaultValue)) {
                aliasMap.put(loadedAliasMap.get(defaultValue), command);
            } else {
                aliasMap.put(command.getDefaultCommand(), command);
            }
        }
    }

    /**
     * Returns command object based on input string.
     *
     * @param input  Command to parse.
     * @return Command to execute.
     */
    public Command parse(String input) {
        String[] inpArr = input.split(" ");
        String command = inpArr[0].toLowerCase();
        if (aliasMap.containsKey(command)) {
            switch (aliasMap.get(command)) {
            case ALIAS:
                return new AliasCommand(inpArr, aliasMap);
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
            default:
                break;
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
            default:
                break;
            }
        }
        return new UiOnlyCommand(LineName.INVALID_COMMAND);
    }
}