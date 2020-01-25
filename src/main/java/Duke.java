import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    -----------------------------------\n"
                + "      Hello! I'm\n" + logo);
        System.out.println("      What can I do for you?\n"
                + "    -----------------------------------");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> strArr = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String arr[] = input.split(" ", 2);
            Task t = new Task(input);

            if (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println("    -----------------------------------");
                    for (int i = 1; i < strArr.size() + 1; i++) {
                        Task current = strArr.get(i-1);
                        System.out.println("      " + i + ". [" + current.getStatusIcon() + "] " + current.getTask());
                    }
                    System.out.println("    -----------------------------------");

                } else if (arr[0].equals("done")) {
                    Task current = strArr.get(Integer.parseInt(arr[1]) - 1);
                    current.markDone();
                    System.out.println("    -----------------------------------\n"
                            + "      Nice! I've marked this task as done:\n "
                            + "        [" + current.getStatusIcon() + "] " + current.getTask() + "\n"
                            + "    -----------------------------------");

                } else {
                    strArr.add(t);
                    System.out.println("    -----------------------------------\n"
                            + "      added: " + input +"\n"
                            + "    -----------------------------------");
                }
            } else {
                System.out.println("    -----------------------------------\n"
                        + "      Bye. Hope to see you again soon!\n"
                        + "    -----------------------------------");
                System.exit(0);
            }
        }
    }
}
