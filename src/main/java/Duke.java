import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks;

    public static void main(String[] args) {
        String horizontalLine = "    ________________________________________________________";
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + horizontalLine);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(horizontalLine + "\n");

        String[] command = sc.nextLine().split(" ", 2);
        while (!command[0].equals("bye")) {
            try {
                System.out.println("\n" + horizontalLine);
                Task task = null;
                switch(command[0]) {
                    case "list":
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
                        }
                        break;
                    case "done":
                        try {
                            task = tasks.get(Integer.parseInt(command[1]) - 1);
                            task.markAsDone();
                        } catch (NumberFormatException e) {
                            throw new DukeException("OOPS!!! Please give me the task number.");
                        } catch (Exception e) {
                            throw new DukeException("OOPS!!! Which task is done?");
                        }
                        break;
                    case "delete":
                        try {
                            task = tasks.get(Integer.parseInt(command[1]) - 1);
                            tasks.remove(task);
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + task);
                            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Which task should I remove?");
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Do you have this task number?");
                        } catch (NumberFormatException e) {
                            throw new DukeException("OOPS!!! Please give me the task number to delete.");
                        }
                        break;
                    case "todo":

                        try {
                            task = new Todo(command[1]);
                            addTask(task);
                        } catch (Exception e) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        try {
                            String[] info = command[1].split(" /by ");
                            task = new Deadline(info[0], info[1]);
                            addTask(task);
                        } catch (Exception e) {
                            throw new DukeException("OOPS!!! Missing information regarding deadline.");
                        }
                        break;
                    case "event":
                        try {
                            String[] info = command[1].split(" /at ");
                            task = new Event(info[0], info[1]);
                            addTask(task);
                        } catch (Exception e) {
                            throw new DukeException(("OOPS!!! Missing information regarding event."));
                        }
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("     " + e);
            }
            System.out.println(horizontalLine + "\n");
            command = sc.nextLine().split(" ", 2);
        }

        System.out.println("\n" + horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine + "\n");
    }

    public static void addTask(Task task) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        tasks.add(task);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }
}
