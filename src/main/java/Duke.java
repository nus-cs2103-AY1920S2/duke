import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        String input = "";
        while(!input.equals("bye")) {
            input = sc.next();
            switch (input) {
                case "bye":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println("    ____________________________________________________________");
                    break;
                default:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    " + input);
                    System.out.println("    ____________________________________________________________");
                    break;


            }
        }
    }
}
