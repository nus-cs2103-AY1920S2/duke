package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public abstract class Command {
    // INVALID, delete
    public String[] inputArr;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

    /**
     * Returns the index of a string in a String array. If the string is not found,
     * -1 is returned.
     *
     * @param s   String to be found
     * @param arr Array to be searched
     * @return Index of string
     */
    public static int findIndex(String s, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a string that is concatenated from the start index to end index of
     * input string array. String at position end index is not included.
     * 
     * @param arr   Array to be searched
     * @param start Start index
     * @param end   End index
     * @return Concatenated string
     */
    public static String combineString(String[] arr, int start, int end) {
        String ans = "";
        for (int i = start; i < end; i++) {
            ans += arr[i];
            if (i != end - 1) {
                ans += " ";
            }
        }
        return ans;
    }
}