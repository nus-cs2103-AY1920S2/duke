import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        printMessage("Greetings! I'm Duke!\n\tHow may I help you?");

        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = readNextInput();
                String[] inputSplit = input.split(" ", 2);

                Command cmd = Command.getCommand(inputSplit[0]);

                switch (cmd) {
                    case BYE:
                        printMessage("Bye! Hope you visit again soon!");
                        isRunning = false;
                        break;
                    case LIST:
                        displayList(tasks);
                        break;
                    case DONE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! Missing command parameters!");
                        } else {
                            int index = Integer.valueOf(inputSplit[1]) - 1;
                            if (index < 0 || index >= tasks.size()) {
                                throw new OutOfRangeException();
                            } else {
                                Task task = tasks.get(index);
                                task.markAsDone();
                                printMessage("Nice! I've marked this task as done:\n\t" + task.toString());
                            }
                        }
                        break;
                    case DELETE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! Missing command parameters!");
                        } else {
                            int index = Integer.valueOf(inputSplit[1]) - 1;
                            if (index < 0 || index >= tasks.size()) {
                                throw new OutOfRangeException();
                            } else {
                                Task taskToDelete = tasks.get(index);
                                tasks.remove(index);
                                printMessage("Noted! I've removed this task:\n\t\t" + taskToDelete.toString() +
                                        "\n\tNow you have " + tasks.size() + " tasks in the list.");
                            }
                        }
                        break;
                    case DEADLINE:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a deadline cannot " +
                                    "be empty!");
                        } else {
                            String[] arguments = inputSplit[1].split(" /by ", 2);
                            if (arguments.length < 2) {
                                throw new InsufficientArgumentsException("☹ OOPS!!! Missing deadline parameters!");
                            } else {
                                tasks.add(new Deadline(arguments[0], arguments[1]));
                                printMessage("Got it! I've added the task:\n\t\t" +
                                        tasks.get(tasks.size() - 1).toString() + "\n\tNow you have " + tasks.size() +
                                        " tasks in the list.");
                            }
                        }
                        break;
                    case EVENT:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of an event cannot" +
                                    " be empty");
                        } else {
                            String[] arguments = inputSplit[1].split(" /at ", 2);
                            if (arguments.length < 2) {
                                throw new InsufficientArgumentsException("☹ OOPS!!! Missing event parameters!");
                            } else {
                                tasks.add(new Event(arguments[0], arguments[1]));
                                printMessage("Got it! I've added the task:\n\t\t" + tasks.get(tasks.size() - 1).toString() + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                            }
                        }
                        break;
                    case TODO:
                        if (inputSplit.length < 2) {
                            throw new InsufficientArgumentsException("☹ OOPS!!! The description of a to-do cannot be " +
                                    "empty");
                        } else {
                            tasks.add(new Todo(inputSplit[1]));
                            printMessage("Got it! I've added the task:\n\t\t" +
                                    tasks.get(tasks.size() - 1).toString() + "\n\tNow you have " +
                                    tasks.size() + " tasks in the list.");
                        }
                        break;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException ex) {
                printMessage(ex.getMessage());
            }
        }
    }

    public static String readNextInput() {
        return sc.nextLine();
    }

    public static void printMessage(String msg) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + msg);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void displayList(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                result.append(String.format("%d. %s\n\t", i + 1, tasks.get(i)));
            } else {
                result.append(String.format("%d. %s", i + 1, tasks.get(i)));
            }
        }

        printMessage(result.toString());
    }
}
