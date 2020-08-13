package duke.util;

public class Ui {
    public static final String BORDER = "=^..^=   =^..^=   =^..^=\n";

    public Ui() {

    }

    public static String setBorder(String msg) {
        return BORDER + "\n\t" + msg + "\n\n" + BORDER;
    }

    /**
     * Returns string containing welcome message for start of program.
     */
    public static String welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        return "Greetings mortal, my name is\n" + logo + "How may I help you?\n";
    }

    public String exitMsg() {
        return setBorder("See u the next time you test ur code :P");
    }

}
