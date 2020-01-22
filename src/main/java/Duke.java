import java.util.Scanner;
public class Duke  {
    public static void main(String[] args) {
        Duke.greet();
        Duke.chat();
    }
    public static void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public static void chat() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while(!message.equals("bye")) {
            System.out.println(message);
            message = sc.nextLine();
        }
        System.out.println(message);
    }
}