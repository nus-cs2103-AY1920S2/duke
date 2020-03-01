package duke.parser;

/**
 * Represents a generic parser.
 */
abstract class Parser {
    /**
     * Checks if a tokenized string input contains the specific number of
     * arguments.
     *
     * @param input a tokenized array of input arguments.
     * @param length the desired number of input arguments.
     * @return true if the input contains the correct number of arguments,
     *         otherwise false.
     */
    protected static boolean hasNumArguments(String[] input, int length) {
        return input.length == length;
    }
}
