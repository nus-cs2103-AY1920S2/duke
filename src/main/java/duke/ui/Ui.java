package duke.ui;

/** User Interface Manager. */
public class Ui {

    public static void printIntro() {
        String intro = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
        System.out.println(wrap(intro));
    }

    public static void printMessage(String message) {
        System.out.println(wrap(message));
    }

    public static String wrap(String string) {
        return "____________________________________________________________"
                + System.lineSeparator()
                + string
                + System.lineSeparator()
                + "____________________________________________________________"
                + System.lineSeparator();
    }

}
