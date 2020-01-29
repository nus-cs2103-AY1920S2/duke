import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        //create list
        List<Task> list = new ArrayList<Task>();

        while (true) {
            String command = sc.nextLine();
            String arr[] = command.split(" ");
            String firstWord = arr[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                if (!list.isEmpty()) {
                    for (int i=0; i<list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println((i+1) + ".[" + t.getStatusIcon() + "] " + t.getDescription());
                    }
                }
            } else if (firstWord.equals("done")){
                String secNum = arr[1];
                System.out.println("Nice! I've marked this task as done:");
                Task t = list.get(Integer.parseInt(secNum) - 1);
                t.setDone();
                System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
            } else {
                Task t = new Task(command);
                list.add(t);
                System.out.println("added: " + command);
            }
        }
    }
}
