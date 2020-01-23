import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I am from North Korea\n" +
                "What can I do for you?");
        System.out.println("____________________________________\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                break;
            } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                // mark task as done
                int task = Integer.parseInt(input.substring(5, input.length()));
                tasks.get(task - 1).markAsDone();
                System.out.println("Good job! I have marked the following task as done:");
                System.out.println(tasks.get(task - 1));

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i));
                }
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA");

    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
