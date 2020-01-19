import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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

            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                if (duke.getSize() == 0) {
                    System.out.println("Oops! There are no tasks in your list!");
                } else {
                    System.out.println("Here are your tasks in your list:");
                    System.out.print(duke);
                }

            } else {
                String command = "";
                String parameters = "";
                try {
                    String[] result = input.split(" ", 2);
                    command = result[0];
                    parameters = result[1];

                    if (command.equals("done")) {
                        int taskID = Integer.parseInt(parameters);
                        System.out.println(duke.markDone(taskID));

                    } else if (command.equals("todo")) {
                        String taskName = parameters;
                        System.out.println(duke.newTodo('T', taskName));

                    } else if (command.equals("deadline")) {
                        try {
                            String[] taskDetails = parameters.split("/by");
                            String taskName = taskDetails[0].strip();
                            String taskTime = " (by: " + taskDetails[1].strip() + ")";
                            System.out.println(duke.newDeadline('D', taskName, taskTime));

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Oops! This task needs a date/time!");
                        }

                    } else if (command.equals("event")) {
                        try {
                            String[] taskDetails = parameters.split("/at");
                            String taskName = taskDetails[0].strip();
                            String taskTime = " (by: " + taskDetails[1].strip() + ")";
                            System.out.println(duke.newEvent('E', taskName, taskTime));

                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Oops! This task needs a date/time!");
                        }
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                        System.out.println("Oops! Not enough information to create task!");
                    } else if (command.equals("done")) {
                        System.out.println("Oops! Task not found!");
                    } else {
                        System.out.println("Oops! Unknown action!");
                    }
                }
            }

            System.out.println("");

        }

        System.out.println("Thank you for using Duke.\nHave a nice day!\n");
        scanner.close();
    }
}
