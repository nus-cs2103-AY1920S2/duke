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

        //Main Program Loop, possible change to Switch Case later on
        while (dukeRunning) {
            String input = sc.nextLine();
            String[] inputBreakdown = input.split(" ", 2);

            switch (inputBreakdown[0]) {
                case "bye":
                    print("Bye. Hope to see you again soon!");
                    dukeRunning = false;
                    break;

                case "done":
                    if (inputBreakdown.length <= 1) {
                        print("Invalid task number!");
                    } else {
                        int doneTaskNo = Integer.parseInt(inputBreakdown[1]) - 1;
                        Task doneTask = taskList.get(doneTaskNo);

                        taskList.set(doneTaskNo, new Task(true, doneTaskNo, doneTask.taskName));
                        print("Nice! I've marked this task as done:\n[✓] " + doneTask.taskName);
                    }

                    break;

                case "list":
                    showList(taskList);
                    break;

                default:
                    taskList.add(new Task(false, taskNo++, input));
                    print("added: " + input);
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
            System.out.println("Here are the tasks in your list:");

            for (Task task :tasksList) {
                System.out.println(task);
            }
        }

        System.out.println("____________________________________________________________\n");
    }

    private static class Task {
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
}
