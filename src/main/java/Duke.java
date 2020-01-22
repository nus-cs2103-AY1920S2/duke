import jdk.jfr.Event;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */

        String greet = "Hello! I'm Duke\n  "  +
                "What can i do for you?";

        String bye = "Bye. Hope to see you again soon!";

        String gotIt = "Got it. I've added this task:";

        List<Task> store = new ArrayList<>();

        System.out.println(greet);

        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        int counter = 0;

        while(!command.equals("bye") ) {
            if (!command.contains("todo") || !command.contains("deadline") ||
                !command.contains("event") || !command.contains("done") ||
                !command.contains("list")) {
                throw new DukeException( " ))-: OOPS!!! I'm sorry, but I don't know what that means :-(");

            } else if (command.contains("todo")) {
                //String todo = command.substring(5);
                if (command.split(" ").length > 1 ) {
                    String todo = command.substring(5);
                    Task taskDo = new Todo(todo);
                    store.add(taskDo);
                    System.out.println(gotIt);
                    System.out.println(taskDo);
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    command = scan.nextLine();
                } else {
                    throw new DukeException( "))-: OOPS!!! The description of a todo cannot be empty. " );
                }

            } else if (command.contains("deadline")) {
                String by = command.split("/")[1].substring(3);
                String description = command.split("/")[0].substring(9);
                Task deadline = new Deadline(description, by);
                store.add(deadline);
                System.out.println(gotIt);
                System.out.println(deadline);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                command = scan.nextLine();
            } else if (command.contains("event")) {
                String at = command.split("/")[1].substring(3);
                String description = command.split("/")[0].substring(6);
                Task event = new Events(description, at);
                store.add(event);
                System.out.println(gotIt);
                System.out.println(event);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                command = scan.nextLine();
            } else if (command.contains("done")) {
                int num = Integer.parseInt(command.split(" ")[1]);
                Task t = store.get(num-1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(t);
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
