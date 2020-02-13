public class Ui {

    /**
     * Prints the introduction welcome message when duke runs.
     * @return String the introduction
     */
    public String printIntro() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        logo = "Hello from\n" + logo;

        logo = logo + "Hello! I 'm Duke\n";
        logo = logo + "What can I do for you?\n";
        return logo;
    }

    /**
     * Prints the introduction exit message when duke exit.
     * @return string of the message
     */
    public String printEnding() {
        return ("Bye. Hope not to see you again soon!\n");
    }

    /**
     * Prints any message by duke.
     * @param message of duke
     * @return String ot the message
     */
    public String printMessage(String message) {
        return message + "\n";
    }

    /**
     * Prints a line in the terminal.
     * @return String of a line
     */
    public String printLine() {
        return "________________________________________\n";
    }
}
