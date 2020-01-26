package commands;

/**
 * Commander handles the creation of commands to be executed.
 */
public class Commander {

    /**
     * Creates the command indicated by the processor to be executed.
     * @param commandType The type of command that the processor has requested.
     * @return Returns a command, to be executed.
     */
    public static Command createCommand(CommandType commandType) {
        Command command;
        switch(commandType) {
            case HI:
                command = new CommandHi();
                break;
            case BYE:
                command = new CommandBye();
                break;
            case LIST:
                command = new CommandList();
                break;
            case DONE:
                command = new CommandDone();
                break;
            case TODO:
                command = new CommandTodo();
                break;
            case DEADLINE:
                command = new CommandDeadline();
                break;
            case EVENT:
                command = new CommandEvent();
                break;
            case DELETE:
                command = new CommandDelete();
                break;
            case LISTON:
                command = new CommandListOn();
                break;
            default:
                command = new CommandInvalid();
        }
        return command;
    }
}
