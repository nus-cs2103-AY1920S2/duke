package duke.parser;

abstract class Parser {
    /**
     * Returns true if the input contains the correct number of arguments,
     * otherwise false.
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
