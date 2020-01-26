import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greetings);
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            String msg = sc.nextLine();
            if (msg.equals("bye")) {
                exit = true;
                System.out.println("See you next time! xD");
            } else {
                System.out.println(msg);
            }
        }
    }
}
