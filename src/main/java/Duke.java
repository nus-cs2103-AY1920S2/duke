import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String format = "    -----------------------------------------------------------------\n";
        System.out.println(format + "      Hello! I'm\n" + logo);
        System.out.println("      What can I do for you? :)\n" + format);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println(format + "      Bye. Hope to see you again soon! :)\n" + format);
                System.exit(0);

            } else if (input.equals("list")) {
                System.out.println(format + "      Here are the tasks in your list:");

                for (int i = 1; i < taskList.size() + 1; i++) {
                    Task current = taskList.get(i - 1);
                    System.out.println("      " + i + ". " + current);
                }
                System.out.println(format);

            } else if (arr[0].equals("done")) {

                try {
                    checkNum(arr.length);
                    checkValid(arr[1]);
                    int taskNum = Integer.parseInt(arr[1]) - 1;

                    if (taskList.size() > taskNum) {
                        Task current = taskList.get(taskNum);
                        current.markDone();
                        System.out.println(format
                                + "      Nice! I've marked this task as done:\n "
                                + "        " + current + "\n"
                                + format);
                    } else {
                        System.out.println(format + "      Sorry, this task does not exist :(\n" + format);
                    }

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (arr[0].equals("delete")) {

                try {
                    checkNum(arr.length);
                    checkValid(arr[1]);
                    int taskNum = Integer.parseInt(arr[1]) - 1;

                    if (taskList.size() > taskNum) {
                        Task current = taskList.get(taskNum);
                        taskList.remove(taskNum);
                        System.out.println(format
                                + "      I've removed this task:\n "
                                + "        " + current + "\n"
                                + "      Now you have " + taskList.size() + " tasks in the list.\n"
                                + format);
                    } else {
                        System.out.println(format
                                + "      Sorry, this task does not exist :(\n"
                                + format);
                    }

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                try {
                    String taskType = arr[0];
                    String[] temp;
                    String task = "";
                    checkAction(arr[0]);
                    checkDescription(arr.length);
                    Task newTask = new Task("");

                    switch (taskType) {
                    case "todo":
                        newTask = new ToDo(arr[1]);
                        taskList.add(newTask);
                        break;

                    case "deadline":
                        temp = arr[1].split("/by ");
                        task = temp[0];
                        String date = temp[1];
                        try {
                            LocalDate localDate = LocalDate.parse(date);
                            newTask = new Deadline(task, localDate);
                            taskList.add(newTask);
                        } catch (DateTimeParseException ex) {
                            System.out.println(format
                                    + "      Please enter a date in this format: YYYY-MM-DD !\n"
                                    + format);
                        }
                        break;

                    case "event":
                        temp = arr[1].split("/at ");
                        task = temp[0];
                        String time = temp[1];
                        try {
                            LocalDateTime localDateTime = LocalDateTime.parse(time);
                            newTask = new Event(task, localDateTime);
                            taskList.add(newTask);
                        } catch (DateTimeParseException ex) {
                            System.out.println(format
                                    + "      Please enter a date & time in this format: YYYY-MM-DDTHH:MM !\n"
                                    + format);
                        }

                    }

                    if (!newTask.description.equals("")) {
                        System.out.println(format
                                + "      Got it. I've added this task:\n"
                                + "        " + newTask + "\n"
                                + "      Now you have " + taskList.size() + " tasks in the list.\n"
                                + format);
                    }

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public static void checkDescription(int size) throws DukeException {
        if (size < 2) {
            throw new DukeException("    -----------------------------------------------------------------\n"
                    + "      Please enter a Task description!\n"
                    + "    -----------------------------------------------------------------");
        }
    }

    public static void checkValid(String input) throws DukeException {
        String[] arr = input.split(" ");

        if (arr.length > 1) {
            throw new DukeException("    -----------------------------------------------------------------\n"
                    + "      Please enter a only one Task number!\n"
                    + "    -----------------------------------------------------------------");
        }
    }

    public static void checkNum(int size) throws DukeException {
        if (size < 2) {
            throw new DukeException("    -----------------------------------------------------------------\n"
                    + "      Please enter a Task number!\n"
                    + "    -----------------------------------------------------------------");
        }
    }

    public static void checkAction(String action) throws DukeException {
        if (!action.equals("todo") && !action.equals("deadline") && !action.equals("event")) {
            throw new DukeException("    -----------------------------------------------------------------\n"
                    + "      Sorry, I didn't understand that :( Please try again.\n"
                    + "    -----------------------------------------------------------------");
        }
    }
}
