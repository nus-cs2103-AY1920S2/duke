import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = new String(new char[50]).replace('\0', '_');
    private static final String WELCOME_MESSAGE = "Hello there!";
    private static final String EXIT_MESSAGE = "Goodbye, see you again!";
    private static final String INPUT_PROMPT = "Enter your command: ";
    private final Scanner in;

    public Ui(Scanner in) {
        this.in = in;
    }

    // deals with interactions with the user
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
        showLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showExit() {
        System.out.println(EXIT_MESSAGE);
    }

    public String getUserCommand() {
        System.out.println(INPUT_PROMPT);
        return in.nextLine().toLowerCase();
    }

}
