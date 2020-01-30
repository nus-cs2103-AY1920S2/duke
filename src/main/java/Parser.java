import java.util.Scanner;

public class Parser {
    static Scanner sc = new Scanner(System.in);

    public Parser() {
    }

    public static String readLine() {
        return sc.nextLine();
    }

    public static boolean stillHasInputs() {
        return sc.hasNext();
    }

    public static String getCommand(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs[0];
    }

    public static String getArgs(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs[1];
    }

    public static boolean commandEquals(String command, String input) {
        return getCommand(input).equals(command);
    }

    public static boolean hasNoArgs(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs.length == 1;
    }

    public static int getTaskNo(String input) {
        return Integer.parseInt(getArgs(input));
    }
}
