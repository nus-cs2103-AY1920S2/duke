import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        //Greet
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        //Echo
        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("bye")) break;
            System.out.println(next);
        }
        //Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
