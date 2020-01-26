package processor;

import commands.CommandType;
import commands.Commander;
import commands.Command;

/**
 * Handles the parsing of user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the Command he has requested for. Invalid command is returned for an invalid
     * command.
     * @param input User input.
     * @return Command that user has requested for.
     */
    public static Command parseInput(String input) {
        String[] inputArgs = input.split(" ", 2);
        Command command;

        switch(inputArgs[0]) {
            case "bye":
                command = Commander.createCommand(CommandType.BYE);
                break;
            case "list":
                command = Commander.createCommand(CommandType.LIST);
                break;
            case "liston":
                command = Commander.createCommand(CommandType.LISTON);
                break;
            case "done":
                command = Commander.createCommand(CommandType.DONE);
                break;
            case "todo":
                command = Commander.createCommand(CommandType.TODO);
                break;
            case "deadline":
                command = Commander.createCommand(CommandType.DEADLINE);
                break;
            case "event":
                command = Commander.createCommand(CommandType.EVENT);
                break;
            case "delete":
                command = Commander.createCommand(CommandType.DELETE);
                break;
            default:
                command = Commander.createCommand(CommandType.INVALID);
        }
        return command;
    }


}
