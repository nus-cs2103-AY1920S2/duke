import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        String line = "____________________________________________________________";
        System.out.println("\t" + line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t" + line);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> things = new ArrayList<>();
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            System.out.println("\t" + line);
            if (next.equals("list")) {
                int i = 1;
                for (String s: things) {
                    System.out.println("\t" + i + ". " + s);
                    i++;

                }
            } else {
                things.add(next);
                System.out.println("\tadded: " + next);
            }
            System.out.println("\t" + line);
            next = sc.nextLine();
        }
        System.out.println("\t" + line);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + line);
    }
}
