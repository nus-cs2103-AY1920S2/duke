
public class UI {

    protected static void printIntro() {
        String intro =
                "Hello! I'm Duke\n" +
                        "What can I do for you?";

        System.out.println(stringWrapper(intro));
    }

    protected static String stringWrapper(String string) {
        return "____________________________________________________________\n"
                + string
                + "\n"
                + "____________________________________________________________\n";
    }

}
