import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Scanner sc = new Scanner(System.in);
        System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");


        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
            System.out.println("    " + command);
            System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");
            command = sc.nextLine();
        }

        System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");
    }
}
