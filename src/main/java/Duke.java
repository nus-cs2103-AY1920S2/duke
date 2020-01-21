import java.util.Scanner;

public class Duke {
    static String separator = "____________________________________________________________";

    /**
     * Displays Duke logo.
     * @param args input
     */
    public static void main(String[] args) {
        System.out.println(separator + "\nHello! I'm Duke\nWhat can I do for you?\n" + separator);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.printf("%s\n%s\n%s\n", separator, input, separator);
            input = sc.next();
        }

        if (input.equals("bye")) {
            System.out.println(separator + "\nBye. Hope to see you again soon!\n" + separator);
        }
    }
}
