import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String borderDesign = "    ____________________________________________________________";
        String greet = borderDesign + "\n     Hello! I'm Duke\n     What can I do for you?\n"
                + borderDesign + "\n";
        System.out.println(greet);

        String exit = "     Bye. Hope to see you again soon!\n" + borderDesign;

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<Task>();
        boolean first = true;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(borderDesign);

            if (line.equals("bye")) {
                System.out.println(exit);
                System.exit(0);

            } else if (line.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= lst.size(); i++) {
                    String item = "     " + i + "." + lst.get(i - 1);
                    System.out.println(item);
                }

            } else {
                // Search for a command
                String[] comArs = line.split("\\s", 2);
                if (comArs[0].equals("done")) {
                    int index = Integer.parseInt(comArs[1]) - 1;
                    lst.get(index).markDone();
                    String done = "     Nice! I've marked this task as done: \n"
                            + "       " + lst.get(index);
                    System.out.println(done);

                } else { // add a task
                    Task t;
                    if (comArs[0].equals("todo")) {
                        String details = line.substring(5, line.length());
                        t = new ToDo(details);

                    } else if (comArs[0].equals("event")) {
                        String details = line.substring(6, line.length());
                        String[] msgDate = details.split(" /at ", 2);
                        t = new Event(msgDate[0], msgDate[1]);

                    } else { // deadline
                        String details = line.substring(9, line.length());
                        String[] msgDate = details.split(" /by ", 2);
                        t = new Deadline(msgDate[0], msgDate[1]);
                    }

                    lst.add(t);
                    String grammar = "tasks";
                    if (first) {
                        grammar = "task";
                        first = false;
                    }
                    String tnew = "     Got it. I've added this task:\n"
                            + "       " + t + "\n"
                            + "     Now you have " + lst.size() + " " + grammar + " in the list.";

                    System.out.println(tnew);
                }
            }
            System.out.println(borderDesign + "\n");
        }
    }
}
