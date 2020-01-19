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
        output(new String[]{"Hello! I'm Duke\n     What can I do for you?"});
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while(!op.equals("bye")) {
            if (op.equals("list")) {
                show_list(list);
            } else {
                try {
                    String[] temp = op.split(" ", 2);
                    String cmd = temp[0];
                    switch (cmd) {
                        case "done":
                            done(temp, list);
                            break;
                        case "todo":
                            todo(temp, list);
                            break;
                        case "deadline":
                            deadline(temp, list);
                            break;
                        case "event":
                            event(temp, list);
                            break;
                        default:
                            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    output(new String[]{e.getMessage()});
                }

            }
            op = sc.nextLine();
        }
        output(new String[]{"Bye. Hope to see you again soon!"});
    }
    private static void done(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            if (temp[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description of done cannot be empty.");
            }
            String body = temp[1];
            Task task = list.get(Integer.parseInt(body) - 1);
            task.markAsDone();
            output(new String[]{"Nice! I've marked this task as done:",
                    " " + task.toString()});
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Index is out of bounds.");
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of done have to be a number");
        }
    }
    private static void todo(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            String body = temp[1];
            if (body.trim().equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task task = new ToDo(body);
            int size = list.size();
            output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + (size + 1) + " tasks in the list."});
            list.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    private static void deadline(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            String body = temp[1];
            String[] task_date = body.split(" /by ");
            if (body.trim().equals("") || task_date[0].trim().equals("") ||
                    task_date[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
            }
            Task task = new Deadline(task_date[0], task_date[1]);
            int size = list.size();
            output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + (size + 1) + " tasks in the list."});
            list.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description or by of a deadline cannot be empty.");
        }
    }
    private static void event(String[] temp, ArrayList<Task> list) throws DukeException {
        try {
            String body = temp[1];
            String[] task_date = body.split(" /at ");
            if (body.trim().equals("") || task_date[0].trim().equals("") ||
                    task_date[1].trim().equals("")) {
                throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
            }
            Task task = new Event(task_date[0], task_date[1]);
            int size = list.size();
            output(new String[]{"Got it. I've added this task:", " " + task.toString(),
                    "Now you have " + (size + 1) + " tasks in the list."});
            list.add(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description or at of an event cannot be empty.");
        }
    }

    private static void output(String[] op) {
        String divider = "    ----------------------------------------------------------";
        System.out.println(divider);
        for (String s: op) {
            System.out.println("     " + s);
        }
        System.out.println(divider);
    }
    private static void show_list(ArrayList<Task> list) {
        String divider = "    ----------------------------------------------------------";
        System.out.println(divider);
        System.out.println("     Here are the tasks in your list:");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("     " + (i+1) + ". " + list.get(i).toString());
        }
        System.out.println(divider);
    }
}
