import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();
        int slot = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String action = inputArr[0];
            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < slot; i++) {
                        System.out.println(i + 1 + ". " + data.get(i));
                    }
                } else if (action.equals("done")) {
                    int option = Integer.parseInt(inputArr[1]);
                    data.get(option - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + data.get(option - 1));
                } else if (action.equals("delete")) {
                    int option = Integer.parseInt(inputArr[1]);
                    System.out.println("Noted. I've removed this task: \n  " + data.get(option - 1));
                    data.remove(option - 1);
                    slot--;
                    System.out.println("Now you have " + slot + " tasks in the list.");
                } else {
                    switch (action) {
                        case "todo":
                            if (inputArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            } else {
                                System.out.println("Got it. I've added this task: ");
                                data.add(new Todo(inputArr[1]));
                            }
                            break;
                        case "deadline":
                            if (inputArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            } else {
                                String[] dArr = inputArr[1].split(" /by ", 2);
                                if (dArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! You forgot to specify a date/time for the deadline.");
                                } else {
                                    System.out.println("Got it. I've added this task: ");
                                    data.add(new Deadline(dArr[0], dArr[1]));
                                }
                            }
                            break;
                        case "event":
                            if (inputArr.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            } else {
                                String[] eArr = inputArr[1].split(" /at ", 2);
                                if (eArr.length == 1) {
                                    throw new DukeException("☹ OOPS!!! You forgot to specify a date/time for the event.");
                                } else {
                                    System.out.println("Got it. I've added this task: ");
                                    data.add(new Event(eArr[0], eArr[1]));
                                }
                            }
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("  " + data.get(slot));
                    slot++;
                    System.out.println("Now you have " + slot + " tasks in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
