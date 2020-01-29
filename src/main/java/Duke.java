import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

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

        while (!input.equals("bye")) {
            String[] inputs = input.split(" ", 2);
            try {
                if (inputs[0].equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
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
                        System.out.println("Nice! I've marked this task as done:\n    " + tasks.get(taskNumber));
                    } catch (Exception e) {
                        throw new DukeException("☹ OOPS!!! Please provide a task number within range.");
                    }
                } else if (inputs[0].equals("todo")) {
                    try {
                        Task todo = new Todo(inputs[1]);
                        tasks.add(todo);
                        System.out.println("Got it. I've added this task:\n    " +
                                todo + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (inputs[0].equals("deadline")) {
                    try {
                        String[] taskDetails = inputs[1].split("/by ");
                        try {
                            Task deadline = new Deadline(taskDetails[0].trim(), LocalDate.parse(taskDetails[1]));
                            tasks.add(deadline);
                            System.out.println("Got it. I've added this task:\n    " +
                                    deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
                        } catch (Exception e) {
                            throw new DukeException("☹ OOPS!!! Please provide a date using '/by ' with the format yyyy-mm-dd.");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (inputs[0].equals("event")) {
                    try {
                        String[] taskDetails = inputs[1].split("/on ");
                        try {
                            Task event = new Event(taskDetails[0].trim(), LocalDate.parse(taskDetails[1]));
                            tasks.add(event);
                            System.out.println("Got it. I've added this task:\n    " +
                                    event + "\nNow you have " + tasks.size() + " tasks in the list.");
                        } catch (Exception e) {
                            throw new DukeException("☹ OOPS!!! Please provide a date using '/on ' with the format yyyy-mm-dd..");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                } else if (inputs[0].equals("delete")) {
                    try {
                        Task deletedTask = tasks.remove(Integer.parseInt(inputs[1]) - 1);
                        System.out.println("Noted. I've removed this task: \n    " + deletedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
