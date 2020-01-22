import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.printf("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> newList = new ArrayList<Task>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    if (newList.size() == 0) {
                        System.out.printf("You currently have no tasks in your list\n");
                    } else {
                        System.out.printf("Here are the tasks in your list:\n");
                        for (int i = 0; i < newList.size(); i += 1) {
                            System.out.printf("%d. %s\n", i + 1, newList.get(i).toString());
                        }
                    }

                } else if (command.startsWith("done")) {
                    String[] arr = command.split(" ");
                    if (arr.length < 2) {
                        throw new DukeException("The task to be marked as done must be specified");
                    }
                    int i = Integer.valueOf(arr[1]) - 1;
                    if (i >= newList.size()) {
                        throw new DukeException("Task " + arr[1] + " does not exist");
                    }
                    Task task = newList.get(i);
                    newList.get(i).markAsDone();
                    System.out.printf("Nice! I've marked this task as done: \n%s\n",
                            newList.get(i).toString());

                } else {
                    String[] arr = command.split("/");
                    String[] description = (arr[0].split(" ", 2));

                    if (command.startsWith("todo")) {
                        if (description.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        newList.add(new ToDo(description[1]));
                    } else if (command.startsWith("deadline")) {
                        if (description.length < 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } if (arr.length < 2) {
                            throw new DukeException("The time of a deadline cannot be empty.");
                        }
                        String[] time = (arr[1].split(" ", 2));
                        if (time.length < 2) {
                            throw new DukeException("The time of a deadline cannot be empty.");
                        }
                        newList.add(new Deadline(description[1], time[1]));

                    } else if (command.startsWith("event")) {
                        if (description.length < 2) {
                            throw new DukeException("The description of an event cannot be empty.");
                        } if (arr.length < 2) {
                            throw new DukeException("The time of an event cannot be empty.");
                        }
                        String[] time = (arr[1].split(" ", 2));
                        if (time.length < 2) {
                            throw new DukeException("The time of an event cannot be empty.");
                        }
                        newList.add(new Event(description[1], time[1]));
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.printf("Got it. I've added this task:\n%s\n" +
                                    "Now you have %d tasks in the list.\n",
                            newList.get(newList.size() - 1).toString(), newList.size());
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!!" + e.getMessage());
            } finally {
                command = sc.nextLine();
            }
        }

        System.out.printf("Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
