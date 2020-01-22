import java.util.Scanner;

public class Duke {
    /**
     * Add the ability to store whatever text entered by the user and display them
     * back to the user when requested.
     * Add the ability to mark tasks as done.
     */
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int currentTask = 0;
        String horizontalLine = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("\t" + horizontalLine);
        System.out.println("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        System.out.println("\t" + horizontalLine);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ", 2);
            System.out.println("\t" + horizontalLine);
            if (input[0].equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t" + horizontalLine);
                break;
            } else if (input[0].equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int taskNumber = 1;
                while (list[taskNumber - 1] != null) {
                    System.out.println("\t" + taskNumber + ". " + list[taskNumber - 1]);
                    taskNumber++;
                }
            } else if (input[0].equals("done")) {
                System.out.println(input[1]);
                Task doneTask = list[Integer.parseInt(input[1]) - 1];
                doneTask.setDone();
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t\t" + list[Integer.parseInt(input[1]) - 1]);
            } else {
                System.out.println("\tGot it. I've added this task: ");
                Task newTask;
                if (input[0].equals("todo")) {
                    newTask = new Todo(input[1]);
                } else if (input[0].equals("deadline")) {
                    String[] task = input[1].split("/");
                    newTask = new Deadline(task[0], task[1]);
                } else {
                    String[] task = input[1].split("/");
                    newTask = new Event(task[0], task[1]);
                }
                list[currentTask] = newTask;
                currentTask++;
                System.out.println("\t\t" + newTask);
                System.out.println(String.format("\tNow you have %d tasks in the list.", Task.getTotalTasks()));
            }
            System.out.println("\t" + horizontalLine);
        }
    }
}
