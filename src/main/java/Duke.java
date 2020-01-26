import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Duke Setup
        boolean dukeRunning = true;
        int taskNo = 0;
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //Welcome Text
        print("Hello! I'm Duke\nWhat can I do for you?");

        //Main Program now in Switch, might need to turn cases into separate methods soon
        while (dukeRunning) {
            String input = sc.nextLine();
            String[] inputBreakdown = input.split(" ", 2);
            CommandType dukeSwitchCase;

            try {
                try {
                    dukeSwitchCase = CommandType.valueOf(inputBreakdown[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new UnknownCommandException();
                }

                switch (dukeSwitchCase) {
                    case BYE:
                        print("Bye. Hope to see you again soon!");
                        dukeRunning = false;
                        break;

                    case DEADLINE:
                        try {
                            String[] byDeadline = inputBreakdown[1].split(" /by ");

                            try {
                                taskList.add(new Deadline(false, taskNo++, byDeadline[0], byDeadline[1]));
                                print("Got it. I've added this task:\n\t[D][✗] "
                                        + byDeadline[0] + " (by: " + byDeadline[1] + ")" +
                                        "\nNow you have " + taskList.size() + " task(s) in the list.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                taskNo--;
                                throw new MissingByDeadlineException();
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingDetailsException();
                        }
                        break;

                    case DELETE:
                        try {
                            int deleteTaskNo = Integer.parseInt(inputBreakdown[1]) - 1;
                            Task deletedShadowTask = taskList.get(deleteTaskNo);
                            taskList.remove(deleteTaskNo);

                            for (Task task : taskList) {
                                task.taskNo = taskList.indexOf(task) + 1;
                            }

                            print("Noted. I've removed this task:\n\t" + deletedShadowTask
                                    + "\nNow you have " + taskList.size() + " task(s) in the list.");
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            throw new UnknownTaskException();
                        }
                        break;

                    case DONE:
                        try {
                            int doneTaskNo = Integer.parseInt(inputBreakdown[1]) - 1;
                            taskList.get(doneTaskNo).taskCompleted = true;
                            print("Nice! I've marked this task as done:\n\t[✓] " + taskList.get(doneTaskNo).taskName);
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            throw new UnknownTaskException();
                        }
                        break;

                    case EVENT:
                        try {
                            String[] atEvent = inputBreakdown[1].split(" /at ");

                            try {
                                taskList.add(new Event(false, taskNo++, atEvent[0], atEvent[1]));
                                print("Got it. I've added this task:\n\t[E][✗] "
                                        + atEvent[0] + " (by: " + atEvent[1] + ")" +
                                        "\nNow you have " + taskList.size() + " task(s) in the list.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                taskNo--;
                                throw new MissingAtEventException();
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new MissingDetailsException();
                        }
                        break;

                    case LIST:
                        showList(taskList);
                        break;

                    case TODO:
                        try {
                            taskList.add(new Todo(false, taskNo++, inputBreakdown[1]));
                            print("Got it. I've added this task:\n\t[T][✗] "
                                    + inputBreakdown[1] + "\nNow you have " + taskList.size() + " task(s) in the list.");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            taskNo--;
                            throw new MissingDetailsException();
                        }
                        break;

                    default:
                        break;
                }
            } catch (DukeException e) {
                print(e.toString());
            }
        }
    }

    //Custom print Method to print simple inputs
    static void print(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________\n");
    }

    //Custom showList Method to print the list with the horizontal borders + running index
    static void showList(List<Task> tasksList) {
        System.out.println("____________________________________________________________");

        if (tasksList.size() == 0) {
            System.out.println("List is empty.");
        } else {
            System.out.println("Here are the task(s) in your list:");

            for (Task task : tasksList) {
                System.out.println(task);
            }
        }

        System.out.println("____________________________________________________________\n");
    }
}