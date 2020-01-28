import java.lang.reflect.Array;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "     -----------------------------------------------------------------\n";
        String tab = "      ";
        String greet = line
                + tab + "Hello! I'm Duke\n"
                + tab + "What can I do for you?\n"
                + line;
        System.out.println(greet);

        ArrayList list = new ArrayList();

        Scanner input = new Scanner(System.in);

        while (!input.hasNext("bye")) {
            String next = input.nextLine();

            if (next.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.println(tab + num + ": " + list.get(i));
                }
                System.out.print(line);
            } else {
                System.out.println(line + "     added: " + next + "\n" + line);
                list.add(next);
            }
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}
