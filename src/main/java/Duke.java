import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<String> store = new ArrayList<>();

        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")){
                for (int i = 0; i < store.size(); i++) {
                    System.out.println("" + (i + 1) + ". " + store.get(i));
                }
            } else {
                store.add(command);
                System.out.println("added: " + command);
            }
        }

    }
}
