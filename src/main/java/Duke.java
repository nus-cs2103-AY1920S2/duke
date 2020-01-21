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
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ".[" + tasks[j].getStatusIcon() + "] " + tasks[j]);
                }
            } else if (input.substring(0, 4).equals("done")) {
                int taskNumber = Integer.parseInt(input.substring(5,6)) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    [\u2713] " + tasks[taskNumber]);
            } else {
                tasks[i] = new Task(input);
                i++;
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
