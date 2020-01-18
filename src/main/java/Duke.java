import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (true) {
            switch (command) {
            case "bye":
                exit();
                break;
            default:
                echo(command);
                break;
            }
            command = sc.nextLine();
        }
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello, I'm Duke!\n"
                + "What can I do for you?";
        System.out.println(logo + style(greeting));
    }

    private static void exit() {
        String bye = "Goodbye. Hope to see you again soon!";
        System.out.println(style(bye));
        System.exit(0);
    }

    private static void echo(String message) {
        System.out.println(style(message));
    }

    private static String style(String message) {
        String horizontalLine = new String(new char[76]).replace("\0", "-") + "\n";
        message = horizontalLine + message + "\n" + horizontalLine;
        return message.lines()
            .map(x -> "    " + x + "\n")
            .reduce("", (x, y) -> x + y);
    }
}
