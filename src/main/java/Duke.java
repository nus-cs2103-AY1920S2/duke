import java.util.*;

public class Duke {
    public static void main(String[] args) {
/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
*/
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = sc.next();
            System.out.println(userInput);
        } while (!userInput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
