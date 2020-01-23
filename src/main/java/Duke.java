import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hey! I'm Duke!");
        System.out.println("What can I help you with?");
        boolean done = false;
        while (done != true) {
            String output = sc.nextLine();
            if (!output.equals("bye")) {
                System.out.println(output + "!");
            } else {
                done = true;
            }
        }
        System.out.println("Bye!! Hope to see you again soon!");
        sc.close();
    }
}
