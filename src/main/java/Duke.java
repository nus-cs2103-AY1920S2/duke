public class Duke {

    /** The main method is where the chat-bot is created and executed. */
    public static void main(String[] args) {
        displayLogo();
    }

    /** Outputs the program's logo in the chat-bot. */
    private static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indent("Hello from\n" + logo));
    }

    /** Adds a four-space indent to all lines of a given text. */
    private static String indent(String text) {
        String indent = " ".repeat(4); // Change this number for different indent widths

        return text.replaceAll("(?m)^", indent);
    }

    /** Outputs an indented line in the chat-bot. */
    private static void drawLine() {
        String lineSymbol = "-";
        int lineWidth = 60;

        System.out.println(indent(lineSymbol.repeat(lineWidth)));
    }
}
