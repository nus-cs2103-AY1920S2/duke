import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;


public class Duke {
    static ArrayList<Task> arr;
    static Scanner scanner;

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

        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            checkCommand(input);
            input = scanner.nextLine();
        }
        printWithFormat("Bye. Hope to see you again soon!");
    }

    public static void checkCommand(String input) {
        String line = "-----------------------------------";
        String[] strArr = input.split(" ");
        String command = strArr[0];
        switch (command) {
            case "bye":
                printWithFormat(("Bye. Hope to see you again soon!"));
                break;
            case "list":
                System.out.println(line);
                for (int i = 1; i <= arr.size(); i++) {
                    StringBuilder str = new StringBuilder();
                    Task task = arr.get(i-1);
                    String output = str.append(i).append(". ").append(task.toString()).toString();
                    System.out.println(output);
                }
                System.out.println(line);
                break;
            case "done":
                int index = Integer.parseInt(strArr[1]) - 1;
                System.out.println("Nice! I've marked this task as done: ");
                Task taskToBeDone = arr.get(index);
                taskToBeDone.setDone();
                System.out.println(taskToBeDone.toString());
                System.out.println(line);
                break;
            default:
                Task t = new Task(input);
                arr.add(t);
                printWithFormat("added: " + t.toString());
                break;
        }
    }

    public static void printWithFormat(String input) {
        String line = "-----------------------------------";
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }
}
