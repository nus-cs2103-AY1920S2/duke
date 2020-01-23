import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;


public class Duke {
    static ArrayList<Task> arr;
    static Scanner scanner;

    Duke() {
        arr = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void start() {
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
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            checkCommand(input);
            input = scanner.nextLine();
        }
        printWithFormat("", "bye");
    }

    public static void checkCommand(String input) {
        String line = "-----------------------------------";
        String[] strArr = input.split(" ");
        String command = strArr[0];
        try {
            int length = strArr.length;
            switch (command) {
                case "bye":
                    printWithFormat("", "bye");
                    break;
                case "list":
                    printWithFormat("", "list");
                    break;
                case "done":
                    if (length > 1) {
                        int index = Integer.parseInt(strArr[1]) - 1;
                        Task taskToBeDone = arr.get(index);
                        taskToBeDone.setDone();
                        printWithFormat(taskToBeDone.toString(), "done");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "deadline":
                    if (length > 1) {
                        Deadline d = new Deadline(input);
                        arr.add(d);
                        printWithFormat(d.toString(), "task");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "event":
                    if (length > 1) {
                        Event e = new Event(input);
                        arr.add(e);
                        printWithFormat(e.toString(), "task");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "todo":
                    if (length > 1) {
                        Todo td = new Todo(input);
                        arr.add(td);
                        printWithFormat(td.toString(), "task");
                        break;
                    } else {
                        throw new DukeException("empty", command);
                    }
                case "delete":
                    int index = Integer.parseInt(strArr[1]) - 1;
                    Task taskToBeDeleted = arr.remove(index);
                    printWithFormat(taskToBeDeleted.toString(), "delete");
                    break;
                default:
                    throw new DukeException("invalid", command);
            }
        } catch (DukeException d){
            printWithFormat(d.getMessage(), "");
        }
    }

    public static void printWithFormat(String input, String type) {
        String line = "-----------------------------------";
        System.out.println(line);
        switch (type) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= arr.size(); i++) {
                    StringBuilder str = new StringBuilder();
                    Task task = arr.get(i-1);
                    String output = str.append(i).append(". ").append(task.toString()).toString();
                    System.out.println(output);
                }
                break;
            case "task":
                System.out.println("Got it. I've added this task:");
                System.out.println(input);
                int arrlength = arr.size();
                System.out.println("Now you have "+ arrlength + " tasks in the list.");
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "done":
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(input);
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                System.out.println(input);
                System.out.println("Now you have "+ arr.size() + " tasks in the list.");
                break;
            default:
                System.out.println(input);
                break;
        }
        System.out.println(line);
    }

}
