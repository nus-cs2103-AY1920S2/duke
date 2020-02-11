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

    /**
     * Indents the input string by prepending SPACE <code>level</code> times.
     * @param toIndent The String which is to be indented.
     * @param level An integer representing the level of indentation.
     * @return The String after indentation.
     */
    public static String indent(String toIndent, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append(SPACE);
        }
        sb.append(toIndent);
        return sb.toString();
    }

    /**
     * Indents the input string by prepending SPACE <code>level</code> times and appending a newline character.
     * @param toIndent The String which is to be indented.
     * @param level An integer representing the level of indentation.
     * @return The String after indentation.
     */
    public static String indentWithNewline(String toIndent, int level) {
        return indent(toIndent, level) + "\n";
    }

    /**
     * Prints to the CLI UI with horizontal lines before and after the body.
     * @param body The String which wants to be displayed between the two lines.
     */
    public static void printSection(String body) {
        printLine();
        System.out.println(body);
        printLine();
    }

    /**
     * Displays to the CLI UI the entry message.
     */
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

    /**
     * Returns the goodbye message from duke.
     * @return The goodbye message from duke.
     */
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

    /**
     * Returns a String representation of the TaskList which has been properly indented for ClI UI.
     * @param taskList The TaskList which we want to get the String representation of.
     * @param comment A String which will be prepended to the TaskList String.
     * @return A String representation of the TaskList which has been properly indented for CLI UI.
     */
    public static String displayListInUi(TaskList taskList, String comment) {
        StringBuilder sb = new StringBuilder();
        sb.append(indentWithNewline(comment, 1));
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(indentWithNewline((i + 1) + ". " + taskList.get(i).toString(),1 ));
        }
        return sb.toString();
    }

    /**
     * Returns a String representing the message for CLI UI regarding the task count.
     * @param taskList The TaskList which want to count the number of tasks of.
     * @return A string representing the message for CLI UI regarding the task count.
     */
    public static String getTaskCount(TaskList taskList) {
        int len = taskList.size();
        if (len == 0 || len == 1) {
            return indent("As of now, you have " + len + " task in the list.", 1);
        } else {
            return indent("As of now, you have " + len + " tasks in the list.", 1);
        }
    }
}
