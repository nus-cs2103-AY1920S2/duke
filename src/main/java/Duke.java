import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String indent = "    ";
    private static final String horizontal_line = "____________________________________________________________";

    private static List<String> list = new ArrayList<>();

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
            if (input.equals("list")) {
                printList();
            } else {
                list.add(input);
                print("added: " + input);
            }
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

    private static void printList() {
        System.out.println(indent + horizontal_line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(indent + (i + 1) + ". " + list.get(i));
        }
        System.out.println(indent + horizontal_line + "\n");
    }
}
