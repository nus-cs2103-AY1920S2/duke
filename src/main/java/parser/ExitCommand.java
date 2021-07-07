package parser;

/**
 * Presents a command to terminate the program and exit from the system.
 */
public class ExitCommand extends Command {

    private static final String EXIT_GREETING = "Bye. Hope to see you again soon!\n";

    /**
     * Static method to verify whether a command is an ExitCommand.
     * @param command Command to verify.
     * @return boolean, True if the command is an ExitCommand, False otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Returns exit response.
     * @return farewell response as a string.
     */
    @Override
    public String execute() {
        return EXIT_GREETING;
    }


}
