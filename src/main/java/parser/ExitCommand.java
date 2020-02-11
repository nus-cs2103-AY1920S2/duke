package parser;

public class ExitCommand extends Command {

    static String exitGreeting = "Bye. Hope to see you again soon!\n";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public String execute() {
        return exitGreeting;
//        System.exit(0);
    }


}
