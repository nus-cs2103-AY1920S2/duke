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
        while(!op.equals("bye")) {
            output(op);
            op = sc.nextLine();
        }
        output("Bye. Hope to see you again soon!");
    }

    private static void output(String op) {
        String divider = "    -------------------------------------------------------";
        System.out.println(divider);
        System.out.println("     " + op);;
        System.out.println(divider);
    }
}
