public class Parser {

    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }

    public static String parseCommand(String input) throws GooseUnrecognisedException {
        String[] inputArr = input.split(" ");
        if (checkInput(inputArr)) {
            throw new GooseUnrecognisedException("Honk honk??");
        }
        return inputArr[0];
    }

    private static boolean isValid(String input) {
        String[] inputArr = input.split(" ");
        return inputArr[0].equals("done") || inputArr[0].equals("deadline") ||
                inputArr[0].equals("event") || inputArr[0].equals("todo") || inputArr[0].equals("delete") ||
                inputArr[0].equals("find") || isBye(inputArr[0]) || input.equalsIgnoreCase("list");
    }
}