import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greeting message
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (true) {
            input = sc.next();
            if (input.equals("bye")) {
                printBye();
                break;
            }
            printLine(input);
        }
    }

    public static void printLine(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________");

    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
