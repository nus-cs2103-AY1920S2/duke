public class ProgramOutputHandler {
    protected static final String OUTPUT_HORIZONTAL_LINE = "____________________________________________________________";
    protected static final String OUTPUT_PREFIX_TAB = "    ";

    public static void printLineSeperator() {
        printWithIndentation(OUTPUT_HORIZONTAL_LINE);
    }

    public static void printWithIndentation(String text) {
        System.out.println(OUTPUT_PREFIX_TAB + text);
    }

    public static void nextLine() {
        System.out.print("\n");
    }
}
