public class Ui {

    /**
     * Prints the introduction welcome message when duke runs
     */
    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I 'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
    }

    /**
     * Prints the introduction exit message when duke exit
     */
    public void printEnding() {
        System.out.println("Bye. Hope not to see you again soon!");
    }

    /**
     * Prints any message by duke
     * @param message of duke
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a line in the terminal
     */
    public void printLine() {
        System.out.println("________________________________________");
    }
}
