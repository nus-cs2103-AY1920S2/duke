import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greetings);

        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            String command = st.nextToken();
            switch (command) {
                case "bye":
                    exit = true;
                    System.out.println("See you next time! xD");
                    break;
                case "list":
                    displayTasks(tasks);
                    break;
                case "done":
                    int taskNum = Integer.parseInt(st.nextToken());
                    Task t = tasks.get(taskNum - 1);
                    t.markAsDone();
                    System.out.println("Nice! Congratulations for completing this task:\n" + t);
                    break;
                default:
                    Task task = new Task(line);
                    tasks.add(task);
                    System.out.println("added: " + task);
            }
        }
    }

    static void displayTasks(ArrayList<Task> tasks) {
        int index = 0;
        for (Task task : tasks) {
            String output = String.format("%d. ", ++index) + task;
            System.out.println(output);
        }
    }
}
