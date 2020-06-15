package app.util;

/**
 * This class provides various utility functionalities 
 * to parse a String.
 */
public final class Parser {
    /**
     * Parses an input string by splitting the string by the
     * first instance of a whitespace. Returns a StringPair object 
     * containing the first word and the rest of the string.
     * @param input The input string
     * @return A StringPair object containing the first word and 
     *     the rest of the string.
     */
    public static StringPair parse(String input) {
        String command = input;
        String args = "";
        
        int splitIndex = input.indexOf(" ");
        if (splitIndex != -1) {
            command = input.substring(0, splitIndex);
            args = input.substring(splitIndex + 1);
        }

        return new StringPair(command.trim(), args.trim());
    }
}