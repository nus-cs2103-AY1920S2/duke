import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String[] events = new String[100];
        int eventNumber = 0;

        printLogo();
        printGreet();

        String input = reader.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list(events, eventNumber);
            } else {
                eventNumber++;
                add(input, events, eventNumber);
            }
            input = reader.nextLine();
        }

        exit();
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreak();
    }

    private static void printBreak() {
        System.out.println("    ____________________________________________________");
    }

    private static void printGreet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBreak();
    }

    private static void echo(String input) {
        printBreak();
        System.out.println("    " + input);
        printBreak();
    }

    private static void exit() {
        printBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        printBreak();
    }

    private static void list(String[] events, int eventNumber) {
        printBreak();
        for (int i = 1; i <= eventNumber; i++) {
            System.out.println("    " + i + ". " + events[eventNumber - 1]);
        }
        printBreak();
    }

    private static void add(String input, String[] events, int eventNumber) {
        printBreak();
        System.out.println("    added: " + input);
        events[eventNumber - 1] = input;
        printBreak();
    }
}
