import java.util.*;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Scanner sc = new Scanner(System.in);
        String Hello = "Hello! I'm Duke\nWhat can i do for you?";
        System.out.println(Hello);

        int flag = 0;

        while(flag == 0) {
            String input = sc.nextLine();
            String output;

            if (input.equals("bye")) {
                output = "Bye. Hope to see you again soon!";
                flag = 1;
            } else {
                output = input;
            }

            System.out.println(output);
        }
    }
}
