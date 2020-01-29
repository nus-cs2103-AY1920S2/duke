package duke.ui;

/**
 * User Interface Manager.
 */
public class UI {

    public static void printIntro() {
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(wrap(intro));
    }

    public static void printMessage(String message) {
        System.out.println(wrap(message));
    }

    public static String wrap(String string) {
        return "____________________________________________________________\n"
                + string
                + "\n"
                + "____________________________________________________________\n";
    }

}
