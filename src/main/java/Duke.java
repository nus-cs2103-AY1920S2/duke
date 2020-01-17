import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n"+
                "____________________________________________________________";

        String lines = "        ____________________________________________________________";

        System.out.println(welcome_message);

        while(sc.hasNext()) {
            String input = sc.next();
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("        Bye. Hope to see you again soon");
                System.out.println(lines);
                break;
            }
            // Print out the input
            System.out.println(lines);
            System.out.println("        "+input);
            System.out.println(lines);

        }
    }
}