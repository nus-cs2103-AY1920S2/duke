import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.compareTo("bye") != 0) {
                System.out.println("    ____________________________________________________________\n"
                        + "     " + input + "\n    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________\n"
                        + "     Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________");
                break;
            }
        }
    }
}
