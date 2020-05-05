public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_MESSAGE = "Farewell, my friend. I hope to see you again soon!";

    @Override
    public CommandResult execute() {
        return new CommandResult(BYE_MESSAGE);
    }

    public static boolean isExitCommand(Command c) {
        return c instanceof ExitCommand;
    }
}
