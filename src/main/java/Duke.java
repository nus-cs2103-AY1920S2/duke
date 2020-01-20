import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("bye")) {
            System.out.println(sc.next());
        }
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }
}
