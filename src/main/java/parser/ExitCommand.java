package parser;

public class ExitCommand extends Command {

    private static final String EXIT_GREETING = "Bye. Hope to see you again soon!\n";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public String execute() {
        return EXIT_GREETING;
    }


}
