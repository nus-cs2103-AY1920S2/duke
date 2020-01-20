import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();
        System.out.println("Hi! I am Duke! What would you like to tell me today?:)");

        String command = sc.nextLine();
        String exit = "bye";
        while (!command.equals(exit)) {
            if (command.equals("list")) {
                for (int i = 1; i <= arr.size(); i++) {
                    System.out.println(i + ". " + arr.get(i - 1));
                }
            } else {
                arr.add(command);
                System.out.println("I have taken note: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Okay then! Goodbye!");
    }
}
