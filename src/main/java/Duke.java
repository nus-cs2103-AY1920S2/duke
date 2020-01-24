import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person keep track of various things.
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            loadFileContents(tasks);
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

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

                try {
                    updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("Something went wrong: " + exception.getMessage());
                }

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

                try {
                    updateFile(tasks);
                } catch (IOException exception) {
                    System.out.println("Something went wrong: " + exception.getMessage());
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

                    try {
                        updateFile(tasks);
                    } catch (IOException exception) {
                        System.out.println("Something went wrong: " + exception.getMessage());
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
     * Saves tasks in hard disk when task list changes.
     *
     * @param tasks List of saved tasks.
     */
    private static void updateFile(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("data/duke.txt");

        if (tasks.size() == 0) {
            writer.write("");
            writer.close();
            return;
        }

        writer.write(tasks.get(0).obtainTaskInfo());
        writer.close();

        FileWriter appender = new FileWriter("data/duke.txt", true);

        for (int i = 1; i < tasks.size(); i++) {
            appender.write(System.lineSeparator() + tasks.get(i).obtainTaskInfo());
        }

        appender.close();
    }

    /**
     * Prints the tasks that have been saved in duke.txt.
     */
    private static void loadFileContents(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File("data/duke.txt");

        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()) {
            String task = readFile.nextLine();
            char taskType = task.charAt(1);
            char isDone = task.charAt(4);
            String description = task.substring(7);

            switch (taskType) {
            case 'D':
                int position = description.indexOf("by");
                String date = description.substring(position + 3, position + 14);
                String time = description.substring(position + 15);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);

                formatter = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime localTime = LocalTime.parse(time, formatter);

                description = description.substring(0, position - 1);

                if (isDone == 'X') {
                    tasks.add(new Deadline(description, taskType, localDate, localTime, false));
                } else {
                    tasks.add(new Deadline(description, taskType, localDate, localTime, true));
                }

                break;
            case 'E':
                int pos = description.indexOf("at");
                String eventDate = description.substring(pos + 3, pos + 14);
                String eventTime = description.substring(pos + 15);

                DateTimeFormatter forFormatting = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate localEventDate = LocalDate.parse(eventDate, forFormatting);

                forFormatting = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime localEventTime = LocalTime.parse(eventTime, forFormatting);

                description = description.substring(0, pos - 1);

                if (isDone == 'X') {
                    tasks.add(new Event(description, taskType, localEventDate, localEventTime, false));
                } else {
                    tasks.add(new Event(description, taskType, localEventDate, localEventTime, true));
                }

                break;
            default:
                if (isDone == 'X') {
                    tasks.add(new ToDo(description, taskType, false));
                } else {
                    tasks.add(new ToDo(description, taskType, true));
                }

                break;
            }
        }
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

            task = inputs[1];

            int j = 2;
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