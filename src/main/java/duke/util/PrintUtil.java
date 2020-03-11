package duke.util;

import java.io.PrintStream;

/**
 * Class containing static methods for printing formatted text to the UI.
 */
public class PrintUtil {
    private static int indentLevel = 4;
    private static String buffer = ""; //TODO: replace with char buffer
    
    /**
     * Sets the indentation level of indented lines printed.
     * @param indentLevel Number of spaces of indentation
     */
    public static void setIndentLevel(int indentLevel) {
        PrintUtil.indentLevel = indentLevel;
    }
    
    private static String indentString(String s) {
        //@@author {thetruevincentchow}-reused
        //Reused from https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java with minor modifications
        
        return s.replaceAll("(?m)^", " ".repeat(indentLevel));
        
        //@@author
    }
    
    /**
     * Prints each line of the provided string indented with `indentLevel` spaces,
     * followed by a new line.
     * @param s String to print
     */
    public static void indentedPrintln(String s) {
        buffer += indentString(s) + "\n";
    }
    
    /**
     * Formats the provided arguments with the given format string,
     * then prints the result with indents, similar to `indentedPrintln`.
     * @param format Format string
     * @param args Arguments for format string
     */
    public static void indentedPrintf(String format, Object... args) {
        buffer += indentString(String.format(format, args));
    }
    
    /**
     * Prints the header line indented with `indentLevel` spaces.
     * This is needed for printing the boxes seen in the UI.
     */
    public static void printHeaderLine() {
        indentedPrintln("____________________________________________________________");
    }

    /**
     * Flushes and returns the original contents of the internal buffer.
     * @return Content of buffer
     */
    public static String flushBuffer() {
        String output = buffer;
        buffer = "";
        return output;
    }
}

