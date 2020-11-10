import java.util.Scanner;

public class Parser {
    static Scanner sc = new Scanner(System.in);

    /**
     * Scans for next input.
     * @return the String scanned.
     */
    public static String scanLine() {
        return sc.nextLine();
    }

    /**
     * Check for input in next line.
     * @return true if input is present, false if not.
     */
    public static boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Gets command from input
     * @param a String of a single command or a command with information
     * @return the command as a String
     */
    public static String getCommand(String input) {
        String command;
        String[] inputs = input.split(" ",2);
        command = inputs[0];
        return command;
    }

    /**
     * Gets information from input
     * @param a String of command with information
     * @return the information as a String
     */
    public static String getInfo(String input) {
        String info;
        String[] inputs = input.split(" ",2);
        assert inputs.length == 2: "There should be information";
        info = inputs[1];
        return info;
    }

    /**
     * Checks if command given matches command expected
     * @param a String representing the command given.
     * @param a String representing the command expected.
     * @return true if command given matches command expected, returns false otherwise.
     */
    public static boolean commandEquals(String input, String command) {
        return getCommand(input).equals(command);
    }

    /**
     * Checks if an input has details needed to execute the respective commands
     * @param a String of command
     * @return True if the details required are present, false otherwise
     */
    public static boolean hasDetails(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs.length == 2;
    }

    /**
     * Get task number
     * @param a String of command
     * @return an int representing task number
     */
    public static int getTaskNum(String input) {
        return Integer.parseInt(getInfo(input));
    }
}
