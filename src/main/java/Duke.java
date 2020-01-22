import java.util.Scanner;

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
        Task[] tasks = new Task[100];
        int i = 0;

        while (!input.equals("bye")) {
            String[] inputs = input.split(" ", 2);
            try {
                if (inputs[0].equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < i; j++) {
                        System.out.println(j + 1 + ".[" + tasks[j].getTaskType() + "]["
                                + tasks[j].getStatusIcon() + "] " + tasks[j]);
                    }
                } else if (inputs[0].equals("done")) {
                    try {
                        int taskNumber = Integer.parseInt(inputs[1]) - 1;
                        if (tasks[taskNumber].isTaskDone()) {
                            System.out.println("Task is already done!");
                            input = sc.nextLine();
                            continue;
                        }
                        tasks[taskNumber].markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n    [\u2713] " + tasks[taskNumber]);
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
                    }
                } else if (inputs[0].equals("todo")) {
                    try {
                        tasks[i] = new Task(inputs[0], inputs[1]);
                        System.out.println("Got it. I've added this task:\n    [" +
                                tasks[i].getTaskType() + "][\u2718] " + tasks[i] + "\nNow you have " + (i + 1) + " tasks in the list.");
                        i++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (inputs[0].equals("deadline") || inputs[0].equals("event")) {
                    try {
                        String[] taskDetails = inputs[1].split("/");
                        tasks[i] = new Task(inputs[0], taskDetails[0].trim());
                        try {
                            tasks[i].setDate(taskDetails[1]);
                            System.out.println("Got it. I've added this task:\n    [" +
                                    tasks[i].getTaskType() + "][\u2718] " + tasks[i] + "\nNow you have " + (i + 1) + " tasks in the list.");
                            i++;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            if (inputs[0].equals("deadline")) {
                                throw new DukeException("☹ OOPS!!! Please provide a date using '/by '.");
                            } else {
                                throw new DukeException("☹ OOPS!!! Please provide a date using '/on '.");
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (inputs[0].equals("deadline")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
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
