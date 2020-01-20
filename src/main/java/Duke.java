public class Duke {
    /**
     * Main method. Entry point for the Duke program.
     * @param args Command-line arguments. Unused.
     */
    public static void main(final String[] args) {
        final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__._|_|\\_\\___|\n";
        final String logoDivider = "++++++++++++++++++++++\n";
                System.out.println(logoDivider + logo + "\n" + logoDivider);

        final DukeAssistant dukeAssistant = new DukeAssistant();
        dukeAssistant.run();
    }
}
