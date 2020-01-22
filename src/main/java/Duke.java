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
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("\t" + horizontalLine);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t" + horizontalLine);
                break;
            } else if (input.equals("list")) {
                System.out.println("\t" + horizontalLine);
                System.out.println("\tHere are the tasks in your list:");
                int taskNumber = 1;
                while (list[taskNumber - 1] != null) {
                    System.out.println("\t" + taskNumber + ". " + list[taskNumber - 1]);
                    taskNumber++;
                }
                System.out.println("\t" + horizontalLine);
            } else if (input.equals("done")) {
                int task = sc.nextInt();
                Task doneTask = list[task - 1];
                doneTask.setDone();
                System.out.println("\t" + horizontalLine);
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t" + list[task - 1]);
                System.out.println("\t" + horizontalLine);
            } else {
                System.out.println("\t" + horizontalLine);
                System.out.println("\tadded: " + input);
                System.out.println("\t" + horizontalLine);
                Task newTask = new Task(input);
                list[currentTask] = newTask;
                currentTask++;
            }


        }

    }
}
