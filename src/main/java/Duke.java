import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] data = new Task[100];
        int slot = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split("\\s+", 2);
            String action = inputArr[0];

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < slot; i++) {
                    System.out.println(i + 1 + ". " + data[i]);
                }
            } else if (action.equals("done")) {
                int option = Integer.parseInt(inputArr[1]);
                data[option-1].setDone();
                System.out.println("Nice! I've marked this task as done:\n  " + data[option-1]);
            } else {
                data[slot] = new Task(input);
                System.out.println("added: " + input);
                slot++;
            }
        }

    }
}
