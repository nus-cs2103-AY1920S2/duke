import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");

        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Farewell, my friend.");
                break;
            }
            System.out.println(input);
        }
    }
}
