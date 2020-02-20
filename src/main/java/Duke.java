import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> instructions = new ArrayList<>(100);

    private static void greet() {
        System.out.println("Hello! I'm Duke\n What can I do for you?");
    }

    private static void addTask(String instruction) {
        instructions.add(new Task(instruction));
        System.out.println("added: " + instruction);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printList() {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i) != null) {
                System.out.println((i + 1) + ". " + instructions.get(i).toString());
            }
        }
    }

    private static void doneTask(int n) {
        instructions.get(n-1).setDone();
        System.out.println("Nice! I've marked this task as done: \n" + instructions.get(n-1).toString());
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
            } else if (instruction.split(" ")[0].equals("done")) {
                doneTask(Integer.parseInt(instruction.split(" ")[1]));
            } else {
                addTask(instruction);
            }
        }
    }
}