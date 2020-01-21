import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(intro);
        String input = sc.next();
        while (sc.hasNextLine()) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                System.out.println(input);
                input = sc.next();
            }
        }
    }
}