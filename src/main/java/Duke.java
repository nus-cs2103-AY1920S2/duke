import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi! I am Duke! What would you like to tell me today?:)");

        String command = sc.nextLine();
        String exit = "bye";
        while (!command.equals(exit)) {
            System.out.println(command);
            command = sc.nextLine();
        }
        System.out.println("Okay then! Goodbye!");
    }
}
