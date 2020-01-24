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
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (!input.equals("bye")) {
                System.out.println("    -----------------------------------\n"
                        + "      " + input +"\n"
                        + "    -----------------------------------");
            } else {
                System.out.println("    -----------------------------------\n"
                        + "      Bye. Hope to see you again soon!\n"
                        + "    -----------------------------------");
                System.exit(0);
            }
        }
    }
}
