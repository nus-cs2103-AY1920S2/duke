import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */

        String greet = "Hello! I'm Duke\n  "  +
                "What can i do for you?";

        String bye = "Bye. Hope to see you again soon!";

        List<String> store = new ArrayList<>();

        System.out.println(greet);

        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        int counter = 0;

        while(!command.equals("bye") ) {
            if (!command.equals("list")) {
                store.add(command);
                System.out.println("added: " + command);
                command = scan.nextLine();
            } else {
                while(store.size() != 0) {
                    counter++;
                    System.out.println(counter + ". " + store.get(0));
                    store.remove(0);
                }
                command = scan.nextLine();
            }
        }

        System.out.println(bye);


    }
}
