import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> memory = new ArrayList<>();
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");
        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("I shall not trouble you anymore. Farewell, partner.");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < memory.size(); i++) {
                    System.out.printf("%d - %s\n", i + 1, memory.get(i));
                }
            } else {
                memory.add(input);
                System.out.printf("Added: %s\n", input);
            }
        }
    }
}
