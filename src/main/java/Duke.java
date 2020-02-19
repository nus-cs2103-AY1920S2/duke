import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> instructions = new ArrayList<>(100);

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void addTask(String instruction) {
        instructions.add(instruction);
        System.out.println("added: " + instruction);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) != null) {
                System.out.println((i + 1) + ". " + instructions.get(i));
            }
        }
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
            if (instruction.equals("bye")) {
                exit();
            } else if (instruction.equals("list")) {
                printList();
            } else {
                addTask(instruction);
            }
        }
    }