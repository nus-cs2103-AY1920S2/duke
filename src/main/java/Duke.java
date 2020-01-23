import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t" + line);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String next = sc.nextLine();
        while (!next.trim().equals("bye")) {
            System.out.println("\t" + line);
            if (next.trim().equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int i = 1;
                for (Task s: tasks) {
                    System.out.println("\t" + i + "." + s.toString());
                    i++;
                }
            } else if ((next.trim().split(" ")[0]).equals("done")) {
                Integer taskNumber = Integer.valueOf((next.split(" ")[1]));
                Task updatedTask = tasks.get(taskNumber - 1).setDone();
                tasks.set(taskNumber - 1, updatedTask);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t\t" + updatedTask);
            } else if (next.trim().split(" ")[0].equals("delete")) {
                Integer taskNumber = Integer.valueOf((next.split(" ")[1]));
                Task removedTask = tasks.get(taskNumber - 1);
                tasks.remove(removedTask);
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t\t" + removedTask);
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            } else {
                try {
                    Task newTask;
                    String taskName;
                    String instruction = next.trim().split(" ")[0];
                    if (next.trim().split(" ")[0].equals("todo")) {
                        try {
                            taskName = next.trim().substring(5);
                            newTask = new ToDo(taskName);
                            tasks.add(newTask);
                            printTask(newTask, tasks.size());
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } else {
                        try {
                            taskName = next.substring(next.indexOf(" ") + 1, next.indexOf("/"));
                            String taskDate;
                            if (next.trim().split(" ")[0].equals("deadline")) {
                                taskDate = next.substring(next.indexOf("/") + 1);
                                newTask = new Deadline(taskName, taskDate);
                                tasks.add(newTask);
                                printTask(newTask, tasks.size());
                            } else if (next.trim().split(" ")[0].equals("event")) {
                                taskDate = next.substring(next.indexOf("/") + 1);
                                newTask = new Event(taskName, taskDate);
                                tasks.add(newTask);
                                printTask(newTask, tasks.size());
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                } catch (DukeException de) {
                    System.out.println(de);
                }
            }
            System.out.println("\t" + line);
            next = sc.nextLine();
        }
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }

    static void printTask(Task newTask, int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTask);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }
}
