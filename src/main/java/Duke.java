import java.util.Scanner;

public class Duke {

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void echo(String instruction) {
        System.out.println(" " + instruction);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Scanner scanner = new Scanner(System.in);

        greet();
        while (scanner.hasNextLine()) {
            String instruction = scanner.nextLine();
            if (!instruction.equals("bye")) {
                echo(instruction);
            } else {
                exit();
            }
        }
    }
}