public class Parser {

    public static String[] parse(String command, String regex) throws EmptyInputAelitaException {
        String[] tokens = command.split(regex);
        if (tokens[0].length() == 0) {
            throw new EmptyInputAelitaException();
        } else {
            return tokens;
        }
    }

}
