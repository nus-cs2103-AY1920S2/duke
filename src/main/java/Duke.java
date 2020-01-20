import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> commandList = new ArrayList<String>(
                List.of("todo", "event", "deadline", "list", "done", "delete", "bye"));
        DukeList duke = new DukeList();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you today?");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().strip();

            String command = "";
            String message = "";

            if (!input.contains(" ")) {
                command = input;
            } else {
                String[] s = input.split(" ", 2);
                command = s[0];
                message = s[1];
            }

            if (commandList.contains(command)) {
                if (command.equals("todo")) {
                    if (!message.equals("")) {
                        String taskName = message;
                        System.out.println(duke.newTodo('T', taskName));
                    } else {
                        System.out.println("Oops! This task needs a name!");
                    }

                } else if (command.equals("event")) {
                    try {
                        String[] taskDetails = message.split("/at");
                        String taskName = taskDetails[0].strip();
                        String taskTime = " (at: " + taskDetails[1].strip() + ")";
                        System.out.println(duke.newEvent('E', taskName, taskTime));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (message.equals("")) {
                            System.out.println("Oops! This task needs a name and date/time!");
                        } else {
                            System.out.println("Oops! This task needs a date/time!");
                        }
                    }

                } else if (command.equals("deadline")) {
                    try {
                        String[] taskDetails = message.split("/by");
                        String taskName = taskDetails[0].strip();
                        String taskTime = " (by: " + taskDetails[1].strip() + ")";
                        System.out.println(duke.newEvent('D', taskName, taskTime));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (message.equals("")) {
                            System.out.println("Oops! This task needs a name and date/time!");
                        } else {
                            System.out.println("Oops! This task needs a date/time!");
                        }
                    }

                } else if (command.equals("list")) {
                    if (duke.getSize() == 0) {
                        System.out.println("Yay! There are no tasks in your list!");
                    } else {
                        System.out.println("Here are your tasks in your list:");
                        System.out.print(duke);
                    }

                } else if (command.equals("done")) {
                    try {
                        int taskID = Integer.parseInt(message);
                        System.out.println(duke.markDone(taskID));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Task not found!");
                    }

                } else if (command.equals("delete")) {
                    try {
                        int taskID = Integer.parseInt(message);
                        System.out.println(duke.deleteTask(taskID));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Task not found!");
                    }

                } else if (command.equals("bye")) {
                    break;

                } else {}
            } else {
                System.out.println("Oops! Command not found!");
            }

            System.out.println("");

        }

        System.out.println("Thank you for using Duke.\nHave a nice day!\n");
        scanner.close();
    }
}
