import java.io.PrintStream;

/**
 * Class containing static methods for printing formatted text to the UI.
 */
public class PrintUtil {
    private static final int indentLevels = 4;
    
    private static String indentString(String s) {
        //https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java
        return s.replaceAll("(?m)^", " ".repeat(indentLevels));
    }
    
    /**
     * Prints each line of the provided string indented with `indentLevels` spaces, followed by a new line.
     * @param s String to print
     */
    public static void indentedPrintln(String s) {
        System.out.println(indentString(s));
    }
    
    /**
     * Formats the provided arguments with the given format string,
     * then prints the result with indents, similar to `indentedPrintln`.
     * @param format Format string
     * @param args Arguments for format string
     */
    public static void indentedPrintf(String format, Object... args) {
        System.out.print(indentString(String.format(format, args)));
    }
    
    /**
     * Prints the header line indented with `indentLevels` spaces.
     * This is needed for printing the boxes seen in the UI.
     */
    public static void printHeaderLine() {
        indentedPrintln("____________________________________________________________");
    }
}

