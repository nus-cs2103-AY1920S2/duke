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

        
        while (true) {
            String input = sc.next();

            if (input.equals("bye")) {
                break;
            }

            System.out.println(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
