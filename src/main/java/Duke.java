import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        String exit = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.print(exit);
                System.exit(0);
            } else {
                String echo = "    ____________________________________________________________\n"
                        + "    " + line + "\n"
                        + "    ____________________________________________________________\n\n";
                System.out.print(echo);
            }
        }
    }
}
