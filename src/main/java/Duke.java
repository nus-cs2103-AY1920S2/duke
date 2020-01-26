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

            switch (inputBreakdown[0].toLowerCase()) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    dukeRunning = false;
                    break;

                case "deadline":
                    try {
                        String[] byDeadline = inputBreakdown[1].split(" /by ");

                        try {
                            taskList.add(new Deadline(false, taskNo++, byDeadline[0], byDeadline[1]));
                            print("Got it. I've added this task:\n\t[D][✗] "
                                    + byDeadline[0] + " (by: " + byDeadline[1] + ")" +
                                    "\nNow you have " + taskList.size() + " task(s) in the list.");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            taskNo--;
                            print(new MissingByDeadlineException().toString());
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print(new MissingDetailsException().toString());
                    }
                    break;

                case "done":
                    try {
                        int doneTaskNo = Integer.parseInt(inputBreakdown[1]) - 1;
                        taskList.get(doneTaskNo).taskCompleted = true;
                        print("Nice! I've marked this task as done:\n\t[✓] " + taskList.get(doneTaskNo).taskName);
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        print(new UnknownTaskException().toString());
                    }
                    break;

                case "event":
                    try {
                        String[] atEvent = inputBreakdown[1].split(" /at ");

                        try {
                            taskList.add(new Event(false, taskNo++, atEvent[0], atEvent[1]));
                            print("Got it. I've added this task:\n\t[E][✗] "
                                    + atEvent[0] + " (by: " + atEvent[1] + ")" +
                                    "\nNow you have " + taskList.size() + " task(s) in the list.");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            taskNo--;
                            print(new MissingAtEventException().toString());
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        print(new MissingDetailsException().toString());
                    }
                    break;

                case "list":
                    showList(taskList);
                    break;

                case "todo":
                    try {
                        taskList.add(new Todo(false, taskNo++, inputBreakdown[1]));
                        print("Got it. I've added this task:\n\t[T][✗] "
                                + inputBreakdown[1] + "\nNow you have " + taskList.size() + " task(s) in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        taskNo--;
                        print(new MissingDetailsException().toString());
                    }
                    break;

                default:
                    print(new UnknownCommandException().toString());
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

    static class Task {
        boolean taskCompleted;
        int taskNo;
        String taskName;

        Task(boolean taskCompleted, int taskNo, String taskName) {
            this.taskCompleted = taskCompleted;
            this.taskNo = taskNo + 1;
            this.taskName = taskName;
        }

        @Override
        public String toString() {
            if (taskCompleted) {
                return taskNo + ".[✓] " + taskName;
            } else {
                return taskNo + ".[✗] " + taskName;
            }
        }
    }

    static class Deadline extends Task {
        String byDeadline;

        Deadline(boolean taskCompleted, int taskNo, String taskName, String byDeadline) {
            super(taskCompleted, taskNo, taskName);
            this.byDeadline = "(by: " + byDeadline + ")";
        }

        @Override
        public String toString() {
            if (taskCompleted) {
                return taskNo + ".[D][✓] " + taskName + " " + byDeadline;
            } else {
                return taskNo + ".[D][✗] " + taskName + " " + byDeadline;
            }
        }
    }

    static class Event extends Task {
        String atEvent;

        Event(boolean taskCompleted, int taskNo, String taskName, String atEvent) {
            super(taskCompleted, taskNo, taskName);
            this.atEvent = "(at: " + atEvent + ")";
        }

        @Override
        public String toString() {
            if (taskCompleted) {
                return taskNo + ".[E][✓] " + taskName + " " + atEvent;
            } else {
                return taskNo + ".[E][✗] " + taskName + " " + atEvent;
            }
        }
    }

    static class Todo extends Task {
        Todo(boolean taskCompleted, int taskNo, String taskName) {
            super(taskCompleted, taskNo, taskName);
        }

        @Override
        public String toString() {
            if (taskCompleted) {
                return taskNo + ".[T][✓] " + taskName;
            } else {
                return taskNo + ".[T][✗] " + taskName;
            }
        }
    }

    static abstract class DukeException extends Exception {

        @Override
        public abstract String toString();
    }

    static class MissingAtEventException extends DukeException {

        @Override
        public String toString() {
            return "☹ OOPS!!! Remember to use \"/at\" for Events.";
        }
    }

    static class MissingByDeadlineException extends DukeException {

        @Override
        public String toString() {
            return "☹ OOPS!!! Remember to use \"/by\" for Deadlines.";
        }
    }

    static class MissingDetailsException extends DukeException {

        @Override
        public String toString() {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }
    }

    static class UnknownCommandException extends DukeException {

        @Override
        public String toString() {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    static class UnknownTaskException extends DukeException {

        @Override
        public String toString() {
            return "☹ OOPS!!! I'm sorry, the Task Number entered does not exist.";
        }
    }
}
