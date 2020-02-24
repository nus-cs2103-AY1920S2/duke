package duke;

/**
 * Represents a Parser object that parses user info for Duke.
 */
public class Parser {

    /**
     * Parses the input text into limit divisions and by the whitespace character.
     *
     * @param text  to parse
     * @param limit num of pieces to make
     * @return a String array of parsed input.
     */
    public String[] parse(String text, int limit) {
        return text.split(" ", limit);
    }
}
