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

            // To Do
            else if (input.length() > 5 && input.substring(0, 5).equals("todo ")) {
                added.add(new ToDo(input.substring(5)));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + added.get(added.size() - 1).toString());
                System.out.println("Now you have " + added.size() + "tasks in the list.");
            }

            // Deadline
            else if (input.length() > 9 && input.substring(0, 9).equals("deadline ")) {
                int slashIndex = getSlash(input);
                added.add(new Deadline(input.substring(9, slashIndex - 1), input.substring(slashIndex + 4)));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + added.get(added.size() - 1).toString());
                System.out.println("Now you have " + added.size() + "tasks in the list.");
            }

            // Event
            else if (input.length() > 6 && input.substring(0, 6).equals("event ")) {
                int slashIndex = getSlash(input);
                added.add(new Event(input.substring(6, slashIndex - 1), input.substring(slashIndex + 4)));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + added.get(added.size() - 1).toString());
                System.out.println("Now you have " + added.size() + "tasks in the list.");
            }

            // Done
            else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                String num = input.substring(5);
                int index = Integer.parseInt(num) - 1;

                added.get(index).markAsDone();  // there will be error when index out of bounds, need handle
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t" + added.get(index).toString());
            }

            else {
                System.out.println("Error");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(ArrayList<Task> added) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < added.size(); i++) {
            System.out.println((i + 1) + ". " + added.get(i).toString());
        }
    }

    public static int getSlash(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '/') {
                return i;
            }
        }
        return -1;
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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}