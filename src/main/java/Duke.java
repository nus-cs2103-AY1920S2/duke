import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<String> todo;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        todo = new ArrayList<>();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("list")) {
                showList();
            } else if (input.equals("bye")) {
                exit();
                break;
            } else {
                add(input);
            }
        }
    }

    public static void add(String s) {
        todo.add(s);
        System.out.println("added: " + s);
    }

    public static void showList() {
        int count = 1;
        for (String task : todo) {
            System.out.println(count + ". " + task);
            count++;
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
