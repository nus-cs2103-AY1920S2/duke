import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                String t = sc.nextLine();
                String input[] = t.split(" ", 2);
                String userCommand = input[0];
                if (userCommand.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (userCommand.equals("list")) {
                    if (tasks.size() == 0) {
                        System.out.println("There is no task in your list! Please continue...");
                        continue;
                    }
                    printTasks();
                } else if (userCommand.equals("done")) {
                    int idx = Integer.parseInt(input[1]) - 1;

                    done(idx);
                } else if (userCommand.equals("todo")) {
                    if (input.length == 1) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String task = t.substring(5);

                    toDo(task);
                } else if (userCommand.equals("deadline")) {
                    if (input.length == 1) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String deadline = t.split("/by")[1].substring(1);
                    String task = t.split(" /by")[0].split(" ", 2)[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadline, formatter);

                    deadLine(task, deadlineDate);
                } else if (userCommand.equals("event")) {
                    if (input.length == 1) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String event = t.split("/at")[1].substring(1);
                    String task = t.split(" /at")[0].split(" ", 2)[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime eventDate = LocalDateTime.parse(event, formatter);

                    event(task, eventDate);
                } else if (userCommand.equals("delete")) {
                    int idx = Integer.parseInt(input[1]) - 1;
                    if (tasks.size() <= idx || idx < 0) {
                        throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
                    }

                    delete(idx);
                }   else {
                    throw new CommandNotFoundException("Sorry I don't recognize this command SIA!");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            } catch (DateTimeParseException ex) {
                System.out.println("Remember the format is dd/MM/yyyy HHmm. Try again!");
                continue;
            }
        }
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ".  " + tasks.get(i - 1));
        }
    }

    public static void done(int idx) throws InvalidIndexException {
        if (tasks.size() <= idx || idx < 0) {
            throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
        }
        tasks.get(idx).finishTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(idx));
    }

    public static void toDo(String task) {
        System.out.println("Got it. I've added this task:");
        Todo todo = new Todo(task);
        System.out.println("   " + todo);
        tasks.add(todo);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public static void deadLine(String task, LocalDateTime deadline) {
        System.out.println("Got it. I've added this task:");
        Deadline newDeadline = new Deadline(task, deadline);
        System.out.println("   " + newDeadline);
        tasks.add(newDeadline);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public static void event(String task, LocalDateTime event) {
        System.out.println("Got it. I've added this task:");
        Event newEvent = new Event(task, event);
        System.out.println("   " + newEvent);
        tasks.add(newEvent);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public static void delete(int idx) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(idx));
        tasks.remove(tasks.get(idx));
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }
}