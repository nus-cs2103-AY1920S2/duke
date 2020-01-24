import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person keep track of various things.
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (!input.equals("bye")) {
            String[] inputs = input.split(" ");

            switch (inputs[0]) {
            case "list":
                printList(tasks);
                break;
            case "done":
                int completedTask = Integer.valueOf(inputs[1]);

                tasks.get(completedTask - 1).markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(completedTask - 1).obtainTaskInfo() + "\n");
                break;
            case "delete":
                int removeTask = Integer.valueOf(inputs[1]);

                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + tasks.get(removeTask - 1).obtainTaskInfo());

                tasks.remove(removeTask - 1);

                if (tasks.size() == 1) {
                    System.out.println("Now you have " + tasks.size() + " task in the list.\n");
                } else {
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                }

                break;
            default:
                try {
                    addTask(inputs, tasks);

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).obtainTaskInfo());

                    if (tasks.size() == 1) {
                        System.out.println("Now you have " + tasks.size() + " task in the list.\n");
                    } else {
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                    }
                } catch (DukeException exception) {
                    System.out.println(exception);
                }

                break;
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Adds a task to the list of saved tasks.
     *
     * @param inputs Components of task to be added.
     * @param tasks List of saved tasks.
     */
    private static void addTask(String[] inputs, ArrayList<Task> tasks) throws DukeException {
        String task;

        if (inputs[0].equals("todo")) {
            if (inputs.length == 1) {
                throw new DukeException("\u2639" + " OOPS!!! The description of a todo cannot be empty\n");
            }

            task = inputs[1];
            for (int i = 2; i < inputs.length; i++) {
                task = task.concat(" " + inputs[i]);
            }

            tasks.add(new ToDo(task, 'T'));
        } else if (inputs[0].equals("event") || inputs[0].equals("deadline")) {
            if (inputs.length == 1) {
                throw new DukeException("\u2639" + " OOPS!!! The description of a "
                        + inputs[0] + " cannot be empty\n");
            }

            int j = 2;

            task = inputs[1];
            while (j != inputs.length && inputs[j].charAt(0) != '/') {
                task = task.concat(" " + inputs[j]);
                j++;
            }

            if (j == inputs.length || (j + 1) == inputs.length) {
                throw new DukeException("\u2639" + " OOPS!!! This task requires a timing\n");
            }

            LocalDate date = LocalDate.parse(inputs[++j]);

            LocalTime timing = LocalTime.parse(inputs[++j]);

            if (inputs[0].equals("event")) {
                tasks.add(new Event(task, 'E', date, timing));
            } else {
                tasks.add(new Deadline(task, 'D', date, timing));
            }
        } else {
            throw new DukeException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Prints the list of tasks currently saved.
     *
     * @param tasks List of tasks that are saved.
     */
    private static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + "." + tasks.get(i - 1).obtainTaskInfo());
        }
        System.out.println();
    }
}