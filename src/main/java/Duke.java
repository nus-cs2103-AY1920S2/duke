import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */

        String greet = "Hello! I'm Duke\n  "  +
                "What can i do for you?";

        String bye = "Bye. Hope to see you again soon!";

        List<Task> store = new ArrayList<>();

        System.out.println(greet);

        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        int counter = 0;

        while(!command.equals("bye") ) {
            if (command.contains("done")) {
                int num = Integer.parseInt(command.split(" ")[1]);
                Task t = store.get(num-1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(t);
                command = scan.nextLine();
            } else if (!command.equals("list")) {
                Task t = new Task(command);
                store.add(t);
                System.out.println("added: " + command);
                command = scan.nextLine();
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i =0 ;i< store.size(); i++) {
                    counter ++;
                    System.out.println(counter + ". " + store.get(i));
                    }
                command = scan.nextLine();
            }
        }

        System.out.println(bye);


    }
}
