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
                case "todo":
                    Todo todo = new Todo(st.nextToken("").trim());
                    tasks.add(todo);
                    String todoMsg = "Got it. I've added this task:\n" + todo
                            + String.format("\nnow you have %d tasks in the list.", tasks.size());
                    System.out.println(todoMsg);
                    break;
                case "deadline":
                    String ddlStuff = st.nextToken("/").trim();
                    String ddlTime = st.nextToken("/").trim().substring(3);
                    Deadline ddl = new Deadline(ddlStuff, ddlTime);
                    tasks.add(ddl);
                    String ddlMsg = "Got it. I've added this task:\n" + ddl
                            + String.format("\nnow you have %d tasks in the list.", tasks.size());
                    System.out.println(ddlMsg);
                    break;
                case "event":
                    String eventStuff = st.nextToken("/").trim();
                    String eventTime = st.nextToken("/").trim().substring(3);
                    Event event = new Event(eventStuff, eventTime);
                    tasks.add(event);
                    String eventMsg = "Got it. I've added this task:\n" + event
                            + String.format("\nnow you have %d tasks in the list.", tasks.size());
                    System.out.println(eventMsg);
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
