/**
 * Tokenizer to break a command into tokens given a regular expression.
 */
public class Parser {

    /**
     * Parses a given string command into tokens with a string regular expression.
     *
     * @param command the string command.
     * @param regex   the regular expression.
     * @return a string array of tokens.
     * @throws EmptyInputAelitaException if command is an empty string.
     */
    public static String[] parse(String command, String regex) throws EmptyInputAelitaException {
        String[] tokens = command.split(regex);
        if (tokens[0].length() == 0) {
            throw new EmptyInputAelitaException();
        } else {
            return tokens;
        }
    }

}
