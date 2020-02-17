package dukebot.command;

import dukebot.ui.LineName;

import java.util.HashMap;

/**
 * Class which contains the input parser.
 */
public class Parser {
    private enum ParserState {
        NORMAL,
        CONFIRM_RESET_STORAGE;
    }

    private final HashMap<String, CommandList> aliasMap;
    private ParserState parserState = ParserState.NORMAL;

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
        assert loadedAliasMap != null;
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
     * Returns command object based on parser state.
     *
     * @param input  Command to parse.
     * @return Command to execute.
     */
    public Command parse(String input) {
        switch (parserState) {
        case CONFIRM_RESET_STORAGE:
            return parseConfirmation(input, new ResetStorageCommand(), new UiOnlyCommand(LineName.RESET_STORAGE_FAIL));
        case NORMAL:
            // fallthrough
        default:
            // Should never be default but just in case
            return parseNormal(input);
        }
    }

    /**
     * Get confirmation state.
     *
     * @param input  Command to parse.
     * @return Command to execute.
     */
    public Command parseConfirmation(String input, Command commandToConfirm, Command commandIfFail) {
        parserState = ParserState.NORMAL;
        if (input.toLowerCase().equals("yes")) {
            return commandToConfirm;
        } else {
            return commandIfFail;
        }
    }

    /**
     * Parser in NORMAL state.
     *
     * @param input  Command to parse.
     * @return Command to execute.
     */
    public Command parseNormal(String input) {
        String[] inpArr = input.split(" ");
        String command = inpArr[0].toLowerCase();
        if (aliasMap.containsKey(command)) {
            switch (aliasMap.get(command)) {
            case ADD_CONTACT:
                return new AddContactCommand(inpArr);
            case CONTACTS:
                return new ListContactCommand();
            case ALIAS:
                return new AliasCommand(inpArr, aliasMap);
            case BYE:
                return new ExitCommand();
            case DEADLINE:
                return new NewDeadlineCommand(inpArr);
            case DELETE:
                return new DeleteCommand(inpArr);
            case DELETE_CONTACT:
                return new DeleteContactCommand(inpArr);
            case DONE:
                return new DoneCommand(inpArr);
            case EVENT:
                return new NewEventCommand(inpArr);
            case FIND:
                return new FindCommand(inpArr);
            case HELP:
                return new UiOnlyCommand(LineName.HELP);
            case LIST:
                return new ListCommand();
            case RESCHEDULE:
                return new RescheduleCommand(inpArr);
            case RESET:
                parserState = ParserState.CONFIRM_RESET_STORAGE;
                return new UiOnlyCommand(LineName.RESET_STORAGE_INIT);
            case TODO:
                return new NewTodoCommand(inpArr);
            default:
                // Should never happen as everything in aliasMap should be handled
                assert false;
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
            case "credits":
                return new UiOnlyCommand(LineName.CREDITS);
            default:
                break;
            }
        }
        return new UiOnlyCommand(LineName.INVALID_COMMAND);
    }
}