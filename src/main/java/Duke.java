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

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(borderDesign);

            if (line.equals("bye")) {
                System.out.println(exit);
                System.exit(0);

            } else {
                // Search for a command
                String[] comArs = line.split("\\s", 2);
                if (comArs[0].equals("done")) {
                    int index = Integer.parseInt(comArs[1]) - 1;
                    lst.get(index).markDone();
                    String done = "     Nice! I've marked this task as done: \n"
                            + "       " + lst.get(index);
                    System.out.println(done);

                } else if (line.equals("list")) {
                    for (int i = 1; i <= lst.size(); i++) {
                        String item = "     " + i + "." + lst.get(i - 1);
                        System.out.println(item);
                    }

                } else { // add a new task
                    Task t = new Task(line);
                    lst.add(t);
                    System.out.println("     added: " + t);
                }
                System.out.println(borderDesign + "\n");
            }
        }
    }
}
