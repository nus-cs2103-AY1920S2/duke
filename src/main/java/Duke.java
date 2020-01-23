import java.util.*;
import java.util.logging.Level;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String linedivider = "____________________________________________________________\n";
        System.out.println(linedivider + "Hello! I'm Duke\nWhat can I do for you?\n" + linedivider);
        Scanner sc = new Scanner(System.in);
        Functionality lvl = new Functionality();
        String entry = "";
        while (true) {
            entry = sc.nextLine();
            if (entry.equals("bye")) {
                break;
            }
            System.out.println(linedivider + lvl.levelone(entry) + "\n" + linedivider);
        }
        System.out.println(linedivider + "Bye. Hope to see you again soon!" + "\n" + linedivider);
    }
}
