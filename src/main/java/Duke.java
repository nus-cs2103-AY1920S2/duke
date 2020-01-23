import java.util.Scanner;
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
        ArrayList<String> items = new ArrayList<>();
        int itemCount = 0;

        while (sc.hasNext()) {
            String item = sc.nextLine();
            if (item.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (item.equals("list")) {
                for (String i: items) {
                    System.out.print(itemCount + 1);
                    System.out.println(". " + i);
                    itemCount++;
                }
                continue;
            }
            System.out.println("   > added: " + item);
            items.add(item);
        }
    }
}
