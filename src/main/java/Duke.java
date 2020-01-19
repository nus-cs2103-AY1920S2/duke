import java.util.Scanner;

public class Duke {
    /**
     * main method for Duke.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am \n" + logo + "your personal buddy. What's up?\n" +
                            "____________________________________________________________\n");

        while(true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                divider("Aww okay, see you next time!");
                return;
            }
            divider(input);
        }
    }

    private static void divider(String s) {
        System.out.println("____________________________________________________________\n"
                            + s + "\n"
                            + "____________________________________________________________\n");
    }
}
