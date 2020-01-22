import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    static ArrayList<String> arr;

    public static void main(String[] args) {
        String line = "-----------------------------------";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(line);
        arr = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            checkCommand(input);
            input = scanner.nextLine();
        }
        printWithFormat("Bye. Hope to see you again soon!");
    }

    public static void checkCommand(String input) {
        String line = "-----------------------------------";
        switch (input) {
            case "bye":
                printWithFormat(("Bye. Hope to see you again soon!"));
                break;
            case "list":
                System.out.println(line);
                for (int i = 1; i <= arr.size(); i++) {
                    String item = arr.get(i-1);
                    System.out.println(i + ". " + input );
                }
                System.out.println(line);
                break;
            default:
                arr.add(input);
                printWithFormat("added: " + input);
                break;
        }
    }

    public static void printWithFormat(String message) {
        String line = "-----------------------------------";
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
}
