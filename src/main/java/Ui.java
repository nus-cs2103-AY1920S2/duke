public class Ui {
    public Ui() {

    }

    public void printIntro() {
        printLines("Hello! :) I'm Duke.\n" + "     How can I help you today?");
    }

    public void printGoodbye() {
        printLines("Goodbye. See you again soon!");
    }

    public static void printLines(String content) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + content);
        System.out.println("    ____________________________________________________________");
    }
}