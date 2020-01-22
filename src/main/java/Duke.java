import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printLine();
        System.out.println("Hey there! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (true) {
            String token = scanner.nextLine();
            if (token.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (token.equals("list")) {
                printLine();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                printLine();
            } else {
                list.add(token);
                printLine();
                System.out.println("added: " + token);
                printLine();
            }
        }
    }
}

