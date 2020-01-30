import java.util.Scanner;

public class Parser {
    static Scanner sc = new Scanner(System.in);

    /**
     * Reads the next line of input.
     * @return the String that has been read
     */
    public static String readLine() {
        return sc.nextLine();
    }

    /**
     * Checks if there is still input in the next line
     * @return true if there is, false if not
     */
    public static boolean stillHasInputs() {
        return sc.hasNext();
    }

    /**
     * Extracts and returns the command from the input line
     * @param input A string of a single command, or a command with arguments
     * @return the command represented as a String
     */
    public static String getCommand(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs[0];
    }

    /**
     * Extracts and returns the arguments from the input line
     * @param input A String of command with arguments
     * @return the arguments represented as a String
     */
    public static String getArgs(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs[1];
    }

    /**
     * Checks if the command in the input line is the same as the command expected
     * @param command The command expected represented as a string
     * @param input A String of a single command or a command with arguments
     * @return true if the expected command matches, false otherwise
     */
    public static boolean commandEquals(String command, String input) {
        return getCommand(input).equals(command);
    }

    /**
     * Checks if the command does not have any arguments. I.e. Input is a single command or a command with arguments
     * @param input
     * @return true if it is a single command, false otherwise
     */
    public static boolean hasNoArgs(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs.length == 1;
    }

    /**
     * Obtains the task number from the input line
     * @param input A command with a number as an argument
     * @return an integer of the argument
     */
    public static int getTaskNo(String input) {
        return Integer.parseInt(getArgs(input));
    }
}
