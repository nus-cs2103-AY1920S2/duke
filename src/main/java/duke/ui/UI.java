package duke.ui;

/**
 * User Interface Manager.
 */
public class UI {

    public static void printIntro() {
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(wrapper(intro));
    }

    public static void printMessage(String message) {
        System.out.println(wrapper(message));
    }

    public static String wrapper(String string) {
        return "____________________________________________________________\n"
                + string
                + "\n"
                + "____________________________________________________________\n";
    }

}
