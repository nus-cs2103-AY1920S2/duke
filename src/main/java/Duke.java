import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> commandList = new ArrayList<String>(
                List.of("todo", "event", "deadline", "list", "done", "delete", "bye", "save"));

        List<String> commandLog = new ArrayList<String>();
        TaskList tasklist = Storage.load();

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
                        System.out.println(tasklist.newTodo('T', false, taskName));
                    } else {
                        System.out.println("Oops! This task needs a name!");
                    }

                } else if (command.equals("event")) {
                    try {
                        String[] taskDetails = message.split("/at");
                        String taskName = taskDetails[0].strip();
                        String taskTime = taskDetails[1].strip();
                        System.out.println(tasklist.newEvent('E', false, taskName, taskTime));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (message.equals("")) {
                            System.out.println("Oops! This task needs a name and date/time!");
                        } else {
                            System.out.println("Oops! This task needs a date/time!");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Oops! The date/time might be in the wrong format!");
                    }

                } else if (command.equals("deadline")) {
                    try {
                        String[] taskDetails = message.split("/by");
                        String taskName = taskDetails[0].strip();
                        String taskTime = taskDetails[1].strip();
                        System.out.println(tasklist.newDeadline('D', false, taskName, taskTime));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (message.equals("")) {
                            System.out.println("Oops! This task needs a name and date/time!");
                        } else {
                            System.out.println("Oops! This task needs a date/time!");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Oops! The date/time might be in the wrong format!");
                    }

                } else if (command.equals("list")) {
                    if (tasklist.getSize() == 0) {
                        System.out.println("Yay! There are no tasks in your list!");
                    } else {
                        System.out.println("Here are your tasks in your list:");
                        System.out.print(tasklist);
                    }

                } else if (command.equals("done")) {
                    try {
                        int taskID = Integer.parseInt(message);
                        System.out.println(tasklist.markDone(taskID));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Task not found!");
                    }

                } else if (command.equals("delete")) {
                    try {
                        int taskID = Integer.parseInt(message);
                        System.out.println(tasklist.deleteTask(taskID));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oops! Task not found!");
                    }

                } else if (command.equals("save")) {
                    Storage.save(tasklist);
                    System.out.println("Changes saved!");

                } else if (command.equals("bye")) {
                    if (commandLog.isEmpty() || commandLog.get(commandLog.size() - 1).equals("save")) {
                        break;
                    } else {
                        System.out.println("There are unsaved changes in your list! Do you wish to save?");
                        System.out.println("Y: Save Changes");
                        System.out.println("N: Discard Changes");
                        System.out.println("Press any other key to Cancel and Return");
                        String choice = scanner.nextLine();
                        if (choice.equals("Y") || choice.equals("y")) {
                            Storage.save(tasklist);
                            System.out.println("Changes saved!");
                            break;
                        } else if (choice.equals("N") || choice.equals("n")) {
                            break;
                        } else {
                            continue;
                        }
                    }

                } else {}
                commandLog.add(command);

            } else {
                System.out.println("Oops! Command not found!");
            }

            System.out.println("");

        }

        System.out.println("Thank you for using Duke.\nHave a nice day!\n");
        scanner.close();
    }
}
