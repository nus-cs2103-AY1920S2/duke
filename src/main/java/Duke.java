import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greetings);

        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    exit = true;
                    System.out.println("See you next time! xD");
                    break;
                case "list":
                    displayTasks(tasks);
                    break;
                default:
                    tasks.add(command);
                    System.out.println("added: " + command);
            }
        }
    }

    static void displayTasks(ArrayList<String> tasks) {
        int index = 0;
        for (String task : tasks) {
            String output = String.format("%d. ", ++index) + task;
            System.out.println(output);
        }
    }
}
