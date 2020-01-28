package duke;

public class UI {
    public static String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    static void println() {
        System.out.println(line);
    }

    static void say(String s) {
        System.out.println(line);
        System.out.println(s);
        System.out.println(line);
    }

    static void listHelper(TaskList arr) {
        for (var i = 0; i < arr.size(); i++) {
            Task t = arr.get(i);
            System.out.println(i + 1 + ": " + t.toString());
        }
    }

    static void list(TaskList arr) {
        println();
        System.out.println("Here are the items in your list:");
        UI.listHelper(arr);
        println();
    }

    static void done(Task curr) {
        say("Marked as done:\n" + curr.toString());
    }

    static void added(Task curr) {
        UI.say("Added: " + curr.toString());
    }

    static void delete(Task curr) {
        UI.say("Deleted item:\n" + curr.toString());
    }

    static void failure() {
        UI.say("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    static void goodbye() {
        UI.say("Bye! Hope to see you again!");
    }

    static void showLoadingError() {
        UI.say("Failed to load from file. Are you sure the path is correct?");
    }

    static void results(TaskList tl) {
        println();
        System.out.println("Here are your search results:");
        UI.listHelper(tl);
        println();
    }
}
