import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> added = new ArrayList<Task>();


        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            else if (input.equals("list")) {
                list(added);
            }

            else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                String num = input.substring(5);
                int index = Integer.parseInt(num) - 1;

                added.get(index).markAsDone();  // there will be error when index out of bounds, need handle
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + added.get(index).getStatusIcon() + "]" + " " + added.get(index).description);
            }

            else {
                added.add(new Task(input));
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(ArrayList<Task> added) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < added.size(); i++) {
            System.out.println((i + 1) + ".[" + added.get(i).getStatusIcon() + "] " + added.get(i).description);
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
}