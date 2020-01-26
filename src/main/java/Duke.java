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

            switch (inputBreakdown[0]) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    dukeRunning = false;
                    break;

                case "deadline":
                    String[] byDeadline = inputBreakdown[1].split(" /by ");
                    taskList.add(new Deadline(false, taskNo++, byDeadline[0], byDeadline[1]));
                    print("Got it. I've added this task:\n\t[D][✗] "
                            + byDeadline[0] + " (by: " + byDeadline[1] + ")" +
                            "\nNow you have " + taskList.size() + " task(s) in the list.");
                    break;

                case "done":
                    if (inputBreakdown.length <= 1) {
                        print("Invalid task number!");
                    } else {
                        int doneTaskNo = Integer.parseInt(inputBreakdown[1]) - 1;
                        taskList.get(doneTaskNo).taskCompleted = true;
                        print("Nice! I've marked this task as done:\n\t[✓] " + taskList.get(doneTaskNo).taskName);
                    }

                    break;

                case "event":
                    String[] atEvent = inputBreakdown[1].split(" /at ");
                    taskList.add(new Event(false, taskNo++, atEvent[0], atEvent[1]));
                    print("Got it. I've added this task:\n\t[E][✗] "
                            + atEvent[0] + " (by: " + atEvent[1] + ")" +
                            "\nNow you have " + taskList.size() + " task(s) in the list.");
                    break;

                case "list":
                    showList(taskList);
                    break;

                case "todo":
                    taskList.add(new Todo(false, taskNo++, inputBreakdown[1]));
                    print("Got it. I've added this task:\n\t[T][✗] "
                            + inputBreakdown[1] + "\nNow you have " + taskList.size() + " task(s) in the list.");
                    break;

                default:
                    //taskList.add(new Task(false, taskNo++, input));
                    print("Please try: bye, deadline, done, event, list, todo.");
                    break;
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

            for (Task task :tasksList) {
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
            if(taskCompleted) {
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
            if(taskCompleted) {
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
            if(taskCompleted) {
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
            if(taskCompleted) {
                return taskNo + ".[T][✓] " + taskName;
            } else {
                return taskNo + ".[T][✗] " + taskName;
            }
        }
    }
}
