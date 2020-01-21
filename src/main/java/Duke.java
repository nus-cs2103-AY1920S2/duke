import java.util.Scanner;

public class Duke {

    /**
     * Formats a string to an output produced by Duke.
     * @param s String to be formatted.
     * @return Formatted string ready to be printed.
     */
    public static String dukeFormat(String s) {
        String fill = "----------------------------------------";
        String indent = "    ";
        return indent + fill + "\n" + indent + s + "\n" + indent + fill;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String getInput = null;

        System.out.println(dukeFormat("Hello I'm your mum. What can I do for you?"));

        getInput = sc.next();
        while (!getInput.equals("bye")) {
            System.out.println(dukeFormat(getInput));
            getInput = sc.next();
        }

        System.out.println(dukeFormat("Bye, have a good day!"));
    }
}
