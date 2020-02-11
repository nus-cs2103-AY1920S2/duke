package duke;

/**
 * UI class to abstract all UI related printing.
 */
public class UI {

    /**
     * Commonly used beautifying line.
     */
    public static String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Prints a single line.
     */
    static void println() {
        System.out.println(LINE);
    }

    /**
     * Prints string s wrapped in lines.
     *
     * @param s s
     */
    static String say(String s) {
//        String returnString = LINE + "\n" + s + "\n" + LINE;
        return s;
    }

    /**
     * Lists items in arr one by one.
     *
     * @param arr arr
     */
    static String listHelper(TaskList arr) {
        String returnString = "";
        for (var i = 0; i < arr.size(); i++) {
            Task t = arr.get(i);
            returnString += "\n" + (i + 1) + ": " + t.toString();
        }
        return returnString;
    }

    static String list(TaskList arr) {
        String returnString = "Here are the items in your list:" + UI.listHelper(arr);
        return say(returnString);
    }

    /**
     * Feedback if a task is done.
     *
     * @param curr curr
     */
    static String done(Task curr) {
        return say("Marked as done:\n" + curr.toString());
    }

    /**
     * Feedback if a task is added.
     *
     * @param curr curr
     */
    static String added(Task curr) {
        return say("Added: " + curr.toString());
    }

    /**
     * Feedback if a task is deleted.
     *
     * @param curr curr
     */
    static String delete(Task curr) {
        return say("Deleted item:\n" + curr.toString());
    }

    /**
     * Feedback failure.
     */
    static String failure() {
        return say("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Says goodbye.
     */
    static String goodbye() {
        return say("Bye! Hope to see you again!");
    }

    static String showLoadingError() {
        return say("Failed to load from file. Are you sure the path is correct?");
    }

    static String results(TaskList tl) {
        String returnString = "Here are your search results:\n" + UI.listHelper(tl);
        return say(returnString);
    }
}
