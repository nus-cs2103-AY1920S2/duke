import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        runDuke();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi, I'm Duke!");
        System.out.println("What can I do for you?\n");
    }

    public static String getUserInput() {
        System.out.print("> ");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static void runDuke() {
        printGreeting();

        String input = getUserInput();
        while (!input.equals("bye")) {
            System.out.println(input + "\n");
            input = getUserInput();
        }

        System.out.println("Have a nice day!");
    }
}
