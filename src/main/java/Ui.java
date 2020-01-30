public class Ui {
    private static final String line = "    ____________________________________________________________";
    private static final String space = "    ";

    public static void printLine() {
        System.out.println(line);
    }

    public static void indent(String toIndent) {
        System.out.printf(space);
        System.out.println(toIndent);
    }

    public static void doubleIndent(String toIndent) {
        System.out.printf(space);
        System.out.printf(space);
        System.out.println(toIndent);
    }

    public static void greet() {
        printLine();
        String logo = space
                + " ____        _        \n" + space
                + "|  _ \\ _   _| | _____ \n" + space
                + "| | | | | | | |/ / _ \\\n" + space
                + "| |_| | |_| |   <  __/\n" + space
                + "|____/ \\__,_|_|\\_\\___|\n";
        indent("Greetings, you may call me\n" + logo);
        indent("How may I help you in this fine day today?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        indent("I bid you adieu. Until the day we meet again.");
        printLine();
    }

    public static void displayList() {
        printLine();
        // TODO: Set up the tasks
        for (int i = 0; i < tasks.size(); i++) {
            indent((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public static void printTaskCount() {
        int len = tasks.size();
        if (len == 0 || len == 1) {
            indent("As of now, you have " + len + " task in the list.");
        } else {
            indent("As of now, you have " + len + " tasks in the list.");
        }
    }
}
