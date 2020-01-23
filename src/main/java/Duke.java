import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        int i = 0;

        while (!input.equals("bye")) {
            String[] inputs = input.split(" ", 2);
            try {
                if (inputs[0].equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < i; j++) {
                        System.out.println(j + 1 + "." + tasks.get(j).getTaskType()
                                + tasks.get(j).getStatusIcon() + " " + tasks.get(j));
                    }
                } else if (inputs[0].equals("done")) {
                    try {
                        int taskNumber = Integer.parseInt(inputs[1]) - 1;
                        if (tasks.get(taskNumber).isTaskDone()) {
                            System.out.println("Task is already done!");
                            input = sc.nextLine();
                            continue;
                        }
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n    [\u2713] " + tasks.get(taskNumber));
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
                    }
                } else if (inputs[0].equals("todo")) {
                    try {
                        tasks.add(new Task(inputs[0], inputs[1]));
                        System.out.println("Got it. I've added this task:\n    " +
                                tasks.get(i).getTaskType() + "[\u2718] " + tasks.get(i) + "\nNow you have " + (i + 1) + " tasks in the list.");
                        i++;
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (inputs[0].equals("deadline") || inputs[0].equals("event")) {
                    try {
                        String[] taskDetails = inputs[1].split("/");
                        tasks.add(new Task(inputs[0], taskDetails[0].trim()));
                        try {
                            tasks.get(i).setDate(taskDetails[1]);
                            System.out.println("Got it. I've added this task:\n    " +
                                    tasks.get(i).getTaskType() + "[\u2718] " + tasks.get(i) + "\nNow you have " + (i + 1) + " tasks in the list.");
                            i++;
                        } catch (IndexOutOfBoundsException e) {
                            if (inputs[0].equals("deadline")) {
                                throw new DukeException("☹ OOPS!!! Please provide a date using '/by '.");
                            } else {
                                throw new DukeException("☹ OOPS!!! Please provide a date using '/on '.");
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if (inputs[0].equals("deadline")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                    }
                } else if (inputs[0].equals("delete")) {
                    try {
                        Task deletedTask = tasks.remove(Integer.parseInt(inputs[1]) - 1);
                        i--;
                        System.out.println("Noted. I've removed this task: \n    " + deletedTask.getTaskType()
                                + deletedTask.getStatusIcon() + " " + deletedTask);
                        System.out.println("Now you have " + i + " tasks in the list.");
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
