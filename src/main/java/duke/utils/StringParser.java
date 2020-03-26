package duke.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The <code>StringParser</code> class is a utility class to help parse strings into
 * different formats.
 */
public class StringParser {
    public static final String SPACE = " ";
    public static final String SPACED_PIPE = " | ";
    public static final String NEWLINE = "\n";
    
    public static String addSquareBracket(String text) {
        return "[" + text + "]";
    }
    
    public static String addRoundBracket(String text) {
        return "(" + text + ")";
    }
    
    /**
     * Format task contents into a string separated by spaces.
     *
     * @param text Task contents.
     * @return Formatted task contents.
     */
    public static String combineTextWithSpace(String... text) {
        return Arrays.asList(text).stream()
                .map(content -> content.strip())
                .collect(Collectors.joining(SPACE));
    }
    
    /**
     * Format task contents into a string separated by spaced pipes for saving to storage.
     *
     * @param text Task contents.
     * @return Formatted task contents.
     */
    public static String combineTextWithPipe(String... text) {
        return Arrays.asList(text).stream()
                .map(content -> content.strip())
                .collect(Collectors.joining(SPACED_PIPE));
    }
    
    /**
     * Combine text items into a newline-separated string.
     *
     * @param text Text items.
     * @return Combined string.
     */
    public static String combineTextWithNewline(String... text) {
        return Arrays.asList(text).stream()
                .map(content -> content.strip())
                .collect(Collectors.joining(NEWLINE));
    }
}
