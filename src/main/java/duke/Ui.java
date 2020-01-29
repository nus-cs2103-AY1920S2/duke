import exceptions.DukeException;

public class Ui {

    /**
     * Welcomes the user with a message
     */
    public static void welcome() {
        printLine();
        print("Hello, Duke here! :D \n"
                + "I'm feeling good and ready to go! "
                + "What can I do for you?");
        printLine();
    }

    /**
     * Says goodbye to the user
     */
    public static void goodbye() {
        printLine();
        print("====> Alright byeee thanks for coming and see ya soon! <==== \n:D :D :D :D :D");
        printLine();
    }

    /**
     * Prints a given String
     *
     * @param s String to be printed
     * @param e
     */
    public static void printExceptionMessage(String e) {
        printLine();
        print(e);
        printLine();
    }

    /**
     * Prints a horizontal formatting line
     */
    private static void printLine() {
        print(Constant.FORMAT_LINE);
    }

    /**
     * Prints a given String
     *
     * @param s String to be printed
     */
    private static void print(String s) {
        System.out.println(s);
    }

}
