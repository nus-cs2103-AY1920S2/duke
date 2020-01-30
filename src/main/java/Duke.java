import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingText = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greetingText);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    String byeText = "    ____________________________________________________________\n" +
                            "     Bye. Hope to see you again soon!\n" +
                            "    ____________________________________________________________";
                    System.out.println(byeText);
                    break;
                } else if (input.equals("list")) {
                    printList(taskList);
                } else if (input.contains("done")) {
                    String[] inputArr = input.split(" ");
                    int taskNum = Integer.parseInt(inputArr[1]);
                    taskDone(taskList, taskNum);
                } else if (input.contains("todo")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        addList(new ToDo(input.substring(5)), taskList);
                    }
                } else if (input.contains("deadline")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        int index = input.indexOf("/");
                        addList(new Deadline(input.substring(9, index), input.substring(index + 4)), taskList);
                    }
                } else if (input.contains("event")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    } else {
                        int index = input.indexOf("/");
                        addList(new Event(input.substring(6, index), input.substring(index + 4)), taskList);
                    }
                } else if (input.contains("delete")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("OOPS!!! Please specify a task number.");
                    } else {
                        String[] inputArr = input.split(" ");
                        int taskNum = Integer.parseInt(inputArr[1]);
                        deleteTask(taskList, taskNum);
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public static void deleteTask(ArrayList<Task> list, int taskNum) {
        Task removedTask = list.get(taskNum - 1);
        list.remove(taskNum - 1);
        System.out.println("   ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       " + removedTask + "\n" +
                "     Now you have " + list.size() +" tasks in the list.");
    }

    public static void taskDone(ArrayList<Task> list, int taskNum) {
        Task doneTask = list.get(taskNum - 1);
        doneTask.toggleDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       " + doneTask + "\n" +
                "    ____________________________________________________________");
    }

    public static void addList(Task task, ArrayList<Task> list) {
        list.add(task);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + list.size() + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (Task s : list) {
            System.out.println("     " + (list.indexOf(s)+1) + ". " + s);
        }
        System.out.println("    ____________________________________________________________");
    }

}
