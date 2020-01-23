import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            try {
                String t = sc.nextLine();
                String input[] = t.split(" ", 2);
                if (input[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (input[0].equals("list")) {
                    if (tasks.size() == 0) {
                        System.out.println("There is no task in your list! Please continue...");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + ".  " + tasks.get(i - 1));
                    }
                    continue;
                } else if (input[0].equals("done")) {
                    int idx = Integer.parseInt(input[1]) - 1;
                    if (tasks.size() <= idx || idx < 0) {
                        throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
                    }
                    tasks.get(idx).finishTask();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(idx));
                } else if (input[0].equals("todo")) {
                    if (input.length == 1) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    System.out.println("Got it. I've added this task:");
                    Todo todo = new Todo(t.substring(5));
                    System.out.println("   " + todo);
                    tasks.add(todo);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                } else if (input[0].equals("deadline")) {
                    if (input.length == 1) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    System.out.println("Got it. I've added this task:");
                    String deadline = t.split("/")[1].substring(3);
                    String task = t.split("/")[0].split(" ", 2)[1];
                    Deadline newDeadline = new Deadline(task, deadline);
                    System.out.println("   " + newDeadline);
                    tasks.add(newDeadline);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                } else if (input[0].equals("event")) {
                    if (input.length == 1) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    System.out.println("Got it. I've added this task:");
                    String event = t.split("/")[1].substring(3);
                    String task = t.split("/")[0].split(" ", 2)[1];
                    Event newEvent = new Event(task, event);
                    System.out.println("   " + newEvent);
                    tasks.add(newEvent);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                } else if (input[0].equals("delete")) {
                    int idx = Integer.parseInt(input[1]) - 1;
                    if (tasks.size() <= idx || idx < 0) {
                        throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(idx));
                    tasks.remove(tasks.get(idx));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                }   else {
                    throw new CommandNotFoundException("Sorry I don't recognize this command SIA!");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }
}