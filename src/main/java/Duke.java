import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static String nonEmpty(String tag) {
        return "    ____________________________________________________________\n"
                + "     ☹ OOPS!!! " + tag + " cannot be empty.\n"
                + "    ____________________________________________________________";
    }

    public static String dontKnow() {
        return "    ____________________________________________________________\n"
                + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "    ____________________________________________________________";
    }

    public static void main(String[] args) throws DukeException {
        // greeting
        String logo = "      ____        _\n"
                + "     |  _ \\ _   _| | _____\n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println(logo);
        printLine();
        System.out.println("    Hey there! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        try {
            while (true) {
                String token = scanner.nextLine();
                // split[0] => first word of the line
                String[] split = token.split(" ", 2);
                if (token.equals("bye")) { // greet goodbye
                    printLine();
                    System.out.println("    Bye. Hope to see you again soon!");
                    printLine();
                    break;
                } else if (token.equals("list")) { // print all tasks in list
                    printLine();
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("     " + (i + 1) + ". " + list.get(i));
                    }
                    printLine();
                } else if (split[0].equals("done")) { // mark task as complete
                    int num = Integer.parseInt(split[1]) - 1;
                    list.get(num).markDone();
                    printLine();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + list.get(num));
                    printLine();
                } else if (split[0].equals("delete")) {
                    int num = Integer.parseInt(split[1]) - 1;
                    printLine();
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       " + list.get(num));
                    list.remove(num);
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                    printLine();
                } else { // creating new task
                    // error: invalid task
                    if (!split[0].equals("todo") && !split[0].equals("deadline") && !split[0].equals("event")) {
                        throw new DukeException(dontKnow());
                    }
                    // error: missing description
                    if (split.length <= 1) {
                        throw new DukeException(nonEmpty("The description of a " + split[0]));
                    }
                    char tag = split[0].toUpperCase().charAt(0);
                    Task task = new Task(token); // placeholder
                    // create task according to type of task
                    if (tag == 'T') {
                        task = new Todo(split[1]);
                        list.add(new Todo(split[1]));
                    } else if (tag == 'D') {
                        String[] arr = split[1].split(" /by ");
                        // error: task is missing deadline
                        if (arr.length <= 1) {
                            throw new DukeException(nonEmpty("Deadline of a task"));
                        }
                        task = new Deadline(split[1]);
                        list.add(new Deadline(split[1]));
                    } else if (tag == 'E') {
                        String[] arr = split[1].split(" /at ");
                        // error: event is missing time
                        if (arr.length <= 1) {
                            throw new DukeException(nonEmpty("Time of an event"));
                        }
                        task = new Event(split[1]);
                        list.add(new Event(split[1]));
                    }
                    printLine();
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + task);
                    System.out.println("     Now you have " + list.size() + " tasks in the list.");
                    printLine();
                }
            }
        }
        catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}