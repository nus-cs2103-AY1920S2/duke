import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> added = new ArrayList<String>();


        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            //else if (input.equals("list")) {
            //    list(added);
            //}

            else {
                System.out.println(input);
                //added.add(input);
                //System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(ArrayList<String> added) {
        for (int i = 0; i < added.size(); i++) {
            System.out.println((i + 1) + ". " + added.get(i));
        }
    }
}
