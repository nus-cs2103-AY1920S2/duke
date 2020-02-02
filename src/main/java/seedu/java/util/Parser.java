package seedu.java.util;

/**
 * All static method. Handles input from user.
 * readCommand(String)
 * readTask(String)
 * readTiming(String)
 * readNum(String)
 */
public class Parser {
    /**
     * Static method. Returns a Command (inferred instruction)
     * @param input from UI
     * @return Command
     * @throws Exception
     */
    public static Command readCommand(String input) throws Exception {
        return Command.convert(input.split(" ")[0]);
    }

    /**
     * Reads user's input and returns a String.
     * @param input - from UI
     * @return String - task that the user wants to do
     * @throws Exception - when the parser fails to interpret the task
     */
    public static String readTask(String input) throws Exception  {
        String[] arr = input.split(" ");
        String task = "";
        int i = 1;
        while (i < arr.length && !arr[i].startsWith("/"))  {
            task += arr[i++] + " ";
        }
        if (task.equals("")) {
            throw new Exception();
        } else {
            return task;
        }
    }

    /**
     * Reads user's input and returns a timing in the form of a string.
     * @param input - from UI
     * @return String - the timing
     * @throws Exception - when the parser fails to interpret the task
     */
    public static String readTiming(String input) throws Exception {
        String[] arr = input.split(" ");
        String timing = "";
        int i = 1;
        while (i < arr.length) {
            if (arr[i].startsWith("/")) {
                timing += arr[i++].split("/")[1] + ":";
                while (i < arr.length) {
                    timing += " " + arr[i];
                    i++;
                }
                break;
            }
            i++;
        }
        return timing;
    }

    /**
     * Static method. Returns a number (inferred index to locate task on tasklist)
     * @param input from UI
     * @return integer
     */
    public static int readNum(String input) {
        return Integer.valueOf(input.split(" ")[1]);
    }

}