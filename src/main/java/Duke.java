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
        output("Hello! I'm Duke\n     What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while(!op.equals("bye")) {
            if (op.equals("list")) {
                show_list(list);
                op = sc.nextLine();
            } else {
                output("added: " + op);
                list.add(op);
                op = sc.nextLine();
            }
        }
        output("Bye. Hope to see you again soon!");
    }

    private static void output(String op) {
        String divider = "    -------------------------------------------------------";
        System.out.println(divider);
        System.out.println("     " + op);;
        System.out.println(divider);
    }
    private static void show_list(ArrayList<String> list) {
        String divider = "    -------------------------------------------------------";
        System.out.println(divider);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("     " + (i+1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }
}
