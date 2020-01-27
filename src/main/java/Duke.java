import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        String nxt;
        nxt = sc.next();
        while (nxt.equals("bye") == false) {
            System.out.println("You said: " + nxt);
            nxt = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}