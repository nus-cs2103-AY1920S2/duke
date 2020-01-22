import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int taskNumber = 0;

        printLogo();
        printGreet();

        String input = reader.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list(tasks, taskNumber);
            } else {
                String[] words = input.split(" ");
                if (words[0].equals("done")) {
                    markDone(tasks.get(Integer.valueOf(words[1]) - 1));
                } else {
                    taskNumber++;
                    add(input, tasks);
                }
            }
            input = reader.nextLine();
        }

        exit();
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreak();
    }

    private static void printBreak() {
        System.out.println("    ____________________________________________________");
    }

    private static void printGreet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBreak();
    }

    private static void echo(String input) {
        printBreak();
        System.out.println("    " + input);
        printBreak();
    }

    private static void exit() {
        printBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        printBreak();
    }

    private static void list(ArrayList<Task> tasks, int taskNumber) {
        printBreak();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= taskNumber; i++) {
            Task currTask = tasks.get(i - 1);
            System.out.println("    " + i + "." + currTask);
        }
        printBreak();
    }

    private static void add(String input, ArrayList<Task> tasks) {
        printBreak();
        System.out.println("    added: " + input);
        tasks.add(new Task(input));
        printBreak();
    }

    private static void markDone(Task currTask) {
        printBreak();
        System.out.println("    Nice! I've marked this task as done:");
        currTask.markAsDone();
        System.out.println("      " + currTask);
        printBreak();
    }
}
