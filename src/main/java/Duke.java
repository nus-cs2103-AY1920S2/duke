import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        ArrayList<String> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");


        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks.get(i));
                }
            } else {
                System.out.println("     added: " + command);
                tasks.add(command);
            }
            System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");
            command = sc.nextLine();
        }

        System.out.println("\n    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ––––––––––––––––––––––––––––––––––––––––––––––––––––––––\n");
    }
}
