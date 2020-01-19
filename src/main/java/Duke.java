import java.util.Scanner;

public class Duke {
    private static final String indent = "    ";
    private static final String horizontal_line = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        print(logo + "\nHello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            print(input);
            input = sc.nextLine();
        }
        print("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void print(String text) {
        String[] lines = text.split("\n");
        System.out.println(indent + horizontal_line);
        for (String line : lines) {
            System.out.println(indent + line);
        }
        System.out.println(indent + horizontal_line + "\n");
    }
}
