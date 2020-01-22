import java.util.Scanner;

public class Duke {
    /**
     * Add the ability to store whatever text entered by the user and display them
     * back to the user when requested.
     */
    public static void main(String[] args) {
        String[] list = new String[100];
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
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t" + horizontalLine);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t" + horizontalLine);
                break;
            } else if (input.equals("list")) {
                int taskNumber = 1;
                while (list[taskNumber - 1] != null) {
                    System.out.println(taskNumber + ". " + list[taskNumber - 1]);
                    taskNumber++;
                }
            } else {
                System.out.println("\t" + horizontalLine);
                System.out.println("\tadded: " + input);
                System.out.println("\t" + horizontalLine);
                list[currentTask] = input;
                currentTask++;
            }


        }

    }
}
