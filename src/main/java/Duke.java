import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] tasks = new Task[100];
        int i = 0;

        while (!input.equals("bye")) {
            String[] inputs = input.split(" ", 2);
            if (inputs[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ".[" + tasks[j].getTaskType() + "]["
                            + tasks[j].getStatusIcon() + "] " + tasks[j]);
                }
            } else if (inputs[0].equals("done")) {
                int taskNumber = Integer.parseInt(inputs[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n    [\u2713] " + tasks[taskNumber]);
            } else if (inputs[0].equals("todo")) {
                tasks[i] = new Task(inputs[0], inputs[1]);
                System.out.println("Got it. I've added this task:\n    [" +
                        tasks[i].getTaskType() + "][\u2718] " + tasks[i] + "\nNow you have " + (i + 1) + " tasks in the list.");
                i++;
            } else if (inputs[0].equals("deadline") || inputs[0].equals("event")) {
                String[] taskDetails = inputs[1].split("/");
                tasks[i] = new Task(inputs[0], taskDetails[0].substring(0, taskDetails[0].length() - 1));
                tasks[i].setDate(taskDetails[1]);
                System.out.println("Got it. I've added this task:\n    [" +
                        tasks[i].getTaskType() + "][\u2718] " + tasks[i] + "\nNow you have " + (i + 1) + " tasks in the list.");
                i++;
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
