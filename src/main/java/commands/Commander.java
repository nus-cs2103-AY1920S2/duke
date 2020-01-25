package commands;

public class Commander {

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
