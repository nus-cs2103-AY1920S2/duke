import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    -------------------------------------------------\n"
                + "      Hello! I'm\n" + logo);
        System.out.println("      What can I do for you? :)\n"
                + "    -------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);
            //Task t = new Task(input);

            if (input.equals("bye")) {
                System.out.println("    -------------------------------------------------\n"
                        + "      Bye. Hope to see you again soon! :)\n"
                        + "    -------------------------------------------------");
                System.exit(0);

            } else if (input.equals("list")) {
                System.out.println("    -------------------------------------------------\n"
                        + "      Here are the tasks in your list:");
                for (int i = 1; i < taskList.size() + 1; i++) {
                    Task current = taskList.get(i - 1);
                    System.out.println("      " + i + ". " + current);
                }
                System.out.println("    -------------------------------------------------");

            } else if (arr[0].equals("done")) {

                try {
                    checkNum(arr.length);

                    int taskNum = Integer.parseInt(arr[1]) - 1;
                    if (taskList.size() > taskNum) {
                        Task current = taskList.get(taskNum);
                        current.markDone();
                        System.out.println("    -------------------------------------------------\n"
                                + "      Nice! I've marked this task as done:\n "
                                + "        " + current + "\n"
                                + "    -------------------------------------------------");
                    } else {
                        System.out.println("    -------------------------------------------------\n"
                                + "      Sorry, this task does not exist :(\n"
                                + "    -------------------------------------------------");
                    }

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (arr[0].equals("delete")) {

                try {
                    checkNum(arr.length);

                    int taskNum = Integer.parseInt(arr[1]) - 1;
                    if (taskList.size() > taskNum) {
                        Task current = taskList.get(taskNum);
                        taskList.remove(taskNum);
                        System.out.println("    -------------------------------------------------\n"
                                + "      I've removed this task:\n "
                                + "        " + current + "\n"
                                + "      Now you have " + taskList.size() + " tasks in the list.\n"
                                + "    -------------------------------------------------");
                    } else {
                        System.out.println("    -------------------------------------------------\n"
                                + "      Sorry, this task does not exist :(\n"
                                + "    -------------------------------------------------");
                    }

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                try {
                    checkAction(arr[0]);
                    checkDescription(arr.length);
                    Task newTask = new Task("task");

                    if (arr[0].equals("todo")) {
                        newTask = new ToDo(arr[1]);
                        taskList.add(newTask);

                    } else if (arr[0].equals("deadline")) {
                        String[] temp = arr[1].split("/by");
                        String task = temp[0];
                        String date = temp[1];
                        newTask = new Deadline(task, date);
                        taskList.add(newTask);

                    } else if (arr[0].equals("event")) {
                        String[] temp = arr[1].split("/at");
                        String task = temp[0];
                        String time = temp[1];
                        newTask = new Event(task, time);
                        taskList.add(newTask);
                    }

                    System.out.println("    -------------------------------------------------\n"
                            + "      Got it. I've added this task:\n"
                            + "        " + newTask + "\n"
                            + "      Now you have " + taskList.size() + " tasks in the list.\n"
                            + "    -------------------------------------------------");

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public static void checkDescription(int size) throws DukeException {
        if (size < 2) {
            throw new DukeException("    -------------------------------------------------\n"
                    + "      Please enter a Task description!\n"
                    + "    -------------------------------------------------");
        }
    }

    public static void checkNum(int size) throws DukeException {
        if (size < 2) {
            throw new DukeException("    -------------------------------------------------\n"
                    + "      Please enter a Task number!\n"
                    + "    -------------------------------------------------");
        }
    }

    public static void checkAction(String action) throws DukeException {
        if (!action.equals("todo") && !action.equals("deadline") && !action.equals("event")) {
            throw new DukeException("    -------------------------------------------------\n"
                    + "      Sorry, I didn't understand that :( Please try again.\n"
                    + "    -------------------------------------------------");
        }
    }
}
