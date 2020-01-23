import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static List<Task> todo;

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
            } else if (input.split(" ")[0].equals("done")) {
                int taskNum = Integer.parseInt(input.split(" ")[1]);
                done(taskNum);
            } else {
                add(input);
            }
        }
    }

    public static void add(String s) {
        todo.add(new Task(s));
        System.out.println("added: " + s);
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task t : todo) {
            System.out.println(count + ".[" + t.getStatusIcon() + "] " + t);
            count++;
        }
    }

    public static void done(int i) {
        todo.get(i-1).markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" +
                "  [" + todo.get(i-1).getStatusIcon() + "] " + todo.get(i-1));
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
