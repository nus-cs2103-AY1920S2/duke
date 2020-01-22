import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
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
                Task task = new Task(input);
                addToList(task);
            } else if (command.equals("list")) {
                printList();
            } else if (command.equals("done")) {
                int index = Integer.parseInt(inputs[1]);
                markTaskAsDone(index);
            } else {
                sayInvalidInput();
            }
        }
    }

    private static void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    private static void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    private static void addToList(Task task) {
        tasks.add(task);
        System.out.println("added: " + task.getDescription());
    }

    private static void printList() {
        System.out.println("Stop procrastinating. Do it now!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
        }
    }

    private static void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("Good job! One off your chest!");
        System.out.println(task.toString());
    }

    private static void sayInvalidInput() {
        System.out.println("Apologies, I'm too dumb to understand that!");
    }
}
