import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "     -----------------------------------------------------------------\n";
        String greet = line
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + line;
        System.out.println(greet);

        Scanner input = new Scanner(System.in);

        while(!input.hasNext("bye")) {
            String next = input.next();
            System.out.println(line + "     " + next + "\n" + line);
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}
