
public class UI {

    protected static void printIntro() {
        String intro =
                "Hello! I'm Duke\n" +
                        "What can I do for you?";

        System.out.println(wrapper(intro));
    }

    protected static void printMessage(String message) {
        System.out.println(wrapper(message));
    }

    protected static String wrapper(String string) {
        return "____________________________________________________________\n"
                + string
                + "\n"
                + "____________________________________________________________\n";
    }

}
