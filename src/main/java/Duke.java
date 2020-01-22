import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            if (command.equals("bye")) {
                sayBye();
                break;
            } else if (command.equals("read") || command.equals("return")) {
                addToList(input);
            } else if (command.equals("list")) {
                printList();
            } else {
                sayInvalidInput();
            }
        }
    }

    public static void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    public static void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    public static void addToList(String input) {
        tasks.add(input);
        System.out.println("added: " + input);
    }

    public static void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public static void sayInvalidInput() {
        System.out.println("Apologies, I'm too dumb to understand that!");
    }
}
