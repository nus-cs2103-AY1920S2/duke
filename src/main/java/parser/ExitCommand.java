package parser;

/**
 * Presents a command to terminate the program and exit from the system.
 */
public class ExitCommand extends Command {

    static String exitGreeting = "Bye. Hope to see you again soon!\n";

    /**
     * Static method to verify whether a command is an ExitCommand.
     * @param command Command to verify.
     * @return boolean, True if the command is an ExitCommand, False otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * @return farewell response as a string.
     */
    @Override
    public String execute() {
        return exitGreeting;
//        System.exit(0);
    }


}
