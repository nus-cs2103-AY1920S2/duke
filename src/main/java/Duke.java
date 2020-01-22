import java.util.Scanner;

public class Duke {
    /**
     * In this initial skeletal version of Duke, it starts by greeting the user,
     * simply echos commands entered by the user, and exits when the user types bye.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            }
            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________");
        }

    }
}
