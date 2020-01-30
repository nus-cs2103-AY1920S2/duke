/**
 * Deals with making sense of the user command by breaking it down.
 */
public class Parser {
    public static String[] parse(String input) {
         return input.split("\\s+", 2);
    }
}
