package duke.main;

public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private static final String SPACE = "    ";

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void indent(String toIndent) {
        System.out.printf(SPACE);
        System.out.println(toIndent);
    }

    public static String indent(String toIndent, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(SPACE);
        }
        sb.append(toIndent);
        return sb.toString();
    }

    public static String indentWithNewline(String toIndent, int level) {
        return indent(toIndent, level) + "\n";
    }

    public static void printSection(String body) {
        printLine();
        System.out.println(body);
        printLine();
    }

    public static void greet() {
        String logo = SPACE
                + " ____        _        \n" + SPACE
                + "|  _ \\ _   _| | _____ \n" + SPACE
                + "| | | | | | | |/ / _ \\\n" + SPACE
                + "| |_| | |_| |   <  __/\n" + SPACE
                + "|____/ \\__,_|_|\\_\\___|\n";
        String message = indent("Greetings, you may call me\n" + logo, 1);
        message += indent("How may I help you in this fine day today?", 1);
        printSection(message);
    }

    public static String getGoodbyeMessage() {
        return indent("I bid you adieu. Until the day we meet again.", 1);
    }

    public static void displayList(TaskList taskList) {
        printLine();
        // TODO: Set up the tasks
        for (int i = 0; i < taskList.size(); i++) {
            indent((i + 1) + ". " + taskList.get(i).toString());
        }
        printLine();
    }

    public static String displayListInUi(TaskList taskList, String comment) {
        StringBuilder sb = new StringBuilder();
        sb.append(indentWithNewline(comment, 1));
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(indentWithNewline((i + 1) + ". " + taskList.get(i).toString(),1 ));
        }
        return sb.toString();
    }

    public static String getTaskCount(TaskList taskList) {
        int len = taskList.size();
        if (len == 0 || len == 1) {
            return indent("As of now, you have " + len + " task in the list.", 1);
        } else {
            return indent("As of now, you have " + len + " tasks in the list.", 1);
        }
    }
}
