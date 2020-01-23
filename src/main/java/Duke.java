import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukePrint("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> newList = new ArrayList<Task>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    list(newList);

                } else if (command.startsWith("done")) {
                    String[] arr = command.split(" ");
                    if (arr.length < 2) {
                        throw new DukeException("The task to be marked as done must be specified");
                    }
                    if (Integer.valueOf(arr[1]) - 1 >= newList.size()) {
                        throw new DukeException("Task " + arr[1] + " does not exist");
                    }
                    done(newList, Integer.valueOf(arr[1]) - 1);

                } else if (command.startsWith("delete")) {
                String[] arr = command.split(" ");
                if (arr.length < 2) {
                    throw new DukeException("The task to be deleted must be specified");
                }
                if (Integer.valueOf(arr[1]) - 1 >= newList.size()) {
                    throw new DukeException("Task " + arr[1] + " does not exist");
                }
                delete(newList, Integer.valueOf(arr[1]) - 1);

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

                    dukePrint("Got it. I've added this task:\n"+
                            newList.get(newList.size() - 1).toString()+"\n" +
                            "Now you have "+newList.size()+" tasks in the list.\n");
                }
            } catch (DukeException e) {
                System.out.print(horizontalLines()+"☹ OOPS!!! " + e.getMessage()+
                        "\n"+horizontalLines());
            } finally {
                command = sc.nextLine();
            }
        }

        dukePrint("Bye. Hope to see you again soon!\n");
        sc.close();
    }

    public static String horizontalLines() {
        return "__________________________________________________________________________________________________________\n";
    }

    public static void dukePrint (String input) {
        System.out.print(horizontalLines());
        System.out.print(input);
        System.out.print(horizontalLines());
    }

    public static void list(ArrayList<Task> newList) {
        if (newList.size() == 0) {
            dukePrint("You currently have no tasks in your list\n");
        } else {
            System.out.print(horizontalLines() + "Here are the tasks in your list:\n");
            for (int i = 0; i < newList.size(); i += 1) {
                System.out.print((i + 1)+". "+ newList.get(i).toString()+"\n");
            }
            System.out.print(horizontalLines());
        }
    }

    public static void done(ArrayList<Task> newList, int i) {
        newList.get(i).markAsDone();
        dukePrint("Nice! I've marked this task as done: \n"+
                newList.get(i).toString()+"\n");
    }

    public static void delete(ArrayList<Task> newList, int i) {
        Task task = newList.get(i);
        newList.remove(i);
        dukePrint("Noted. I've removed this task:\n"+task.toString()+"\n" +
                "Now you have "+newList.size()+" tasks in the list.\n");
    }
}
