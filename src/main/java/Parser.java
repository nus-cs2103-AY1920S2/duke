/**
 * Parses and makes sense of the commands provided by the user.
 */
public class Parser {

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Checks what type of command has been issued by the user.
     *
     * @param input Input from the user
     * @return The first word of the user command
     * @throws GooseUnrecognisedException If the command is not valid
     */
    public static String parseCommand(String input) throws GooseUnrecognisedException {
        String[] inputArr = input.split(" ");
        if (isValid(input)) {
            throw new GooseUnrecognisedException("Honk honk??");
        }
        return inputArr[0];
    }

    private static boolean isValid(String input) {
        String[] inputArr = input.split(" ");
        return inputArr[0].equals("list") || inputArr[0].equals("done") || inputArr[0].equals("deadline") ||
                inputArr[0].equals("event") || inputArr[0].equals("todo") || inputArr[0].equals("delete") ||
                isBye(inputArr[0]) || input.equalsIgnoreCase("list");
    }
}