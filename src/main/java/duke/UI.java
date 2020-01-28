package duke;

/**
 * UI class to abstract all UI related printing.
 */
public class UI {
    /**
     * Commonly used beautifying line.
     */
    public static String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Prints a single line.
     */
    static void println() {
        System.out.println(line);
    }

    /**
     * Prints string s wrapped in lines.
     * @param s s
     */
    static void say(String s) {
        System.out.println(line);
        System.out.println(s);
        System.out.println(line);
    }

    /**
     * Lists items in arr one by one.
     * @param arr arr
     */
    static void list(TaskList arr) {
        println();
        System.out.println("Here are the items in your list:");
        for (var i = 0; i < arr.size(); i++) {
            Task t = arr.get(i);
            System.out.println(i + 1 + ": " + t.toString());
        }
        System.out.println(UI.line);
    }

    /**
     * Feedback if a task is done.
     * @param curr curr
     */
    static void done(Task curr) {
        say("Marked as done:\n" + curr.toString());
    }

    /**
     * Feedback if a task is added.
     * @param curr curr
     */
    static void added(Task curr) {
        UI.say("Added: " + curr.toString());
    }

    /**
     * Feedback if a task is deleted.
     * @param curr curr
     */
    static void delete(Task curr) {
        UI.say("Deleted item:\n" + curr.toString());
    }

    /**
     * Feedback failure.
     */
    static void failure() {
        UI.say("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Says goodbye.
     */
    static void goodbye() {
        UI.say("Bye! Hope to see you again!");
    }

    static void showLoadingError() {
        UI.say("Failed to load from file. Are you sure the path is correct?");
    }
}
