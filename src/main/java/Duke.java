import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        //create list
        List<String> list = new ArrayList<String>();

        while (true) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                if (!list.isEmpty()) {
                    for (int i=0; i<list.size(); i++) {
                        System.out.println((i+1) + ". " + list.get(i));
                    }
                }
            } else {
                list.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
