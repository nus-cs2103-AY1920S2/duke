public class ProgramOutputHandler {
    protected static final String OUTPUT_HORIZONTAL_LINE = "____________________________________________________________";
    protected static final String OUTPUT_PREFIX_TAB = "    ";

    public static void PrintLineSeperator()
    {
        PrintWithIndentation(OUTPUT_HORIZONTAL_LINE);
    }

    public static void PrintWithIndentation(String text)
    {
        System.out.println(OUTPUT_PREFIX_TAB + text);
    }
}
