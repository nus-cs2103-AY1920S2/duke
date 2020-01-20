import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

//        System.out.println("Hello from\n" + logo);

        printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Chat logic
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            printFormattedOutput(input);
            input = sc.nextLine();
        }

        printFormattedOutput("Bye. Hope to see you again soon!");

    }

    public static void printFormattedOutput(String output) {
        String bar = "    __________________________________________________\n";

        System.out.println(bar + "    " + output + "\n" + bar);
    }
}
