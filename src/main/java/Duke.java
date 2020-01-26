import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static String buffer;
    static String input;
    static String[] tokens;
    static ArrayList<Task> list;


    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        boolean done = false;
        list = new ArrayList<>();
        String param = "";
        int index;
        Task t;
        while (true) {
            input = scanner.nextLine();
            tokens = input.split(" ");
            switch (tokens[0]) {
                case "todo":
                    buffer = tokens[1];
                    for (int i = 2; i < tokens.length; i++) {
                        buffer += " " + tokens[i];
                    }
                    t = new ToDo(buffer);
                    addTask(t);
                    break;

                case "deadline":
                    buffer = tokens[1];
                    for (int i = 2; i < tokens.length; i++) {
                        if (tokens[i].equals("/by")) {
                            param = tokens[i + 1];
                            for (int j = i + 2; j < tokens.length; j++) {
                                param += " " + tokens[j];
                            }
                            break;
                        }
                        buffer += " " + tokens[i];
                    }
                    t = new Deadline(buffer, param);
                    addTask(t);
                    break;
                case "event":
                    buffer = tokens[1];
                    for (int i = 2; i < tokens.length; i++) {
                        if (tokens[i].equals("/at")) {
                            param = tokens[i + 1];
                            for (int j = i + 2; j < tokens.length; j++) {
                                param += " " + tokens[j];
                            }
                            break;
                        }
                        buffer += " " + tokens[i];
                    }
                    t = new Event(buffer, param);
                    addTask(t);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    done = true;
                    break;
                case "list":
                    if (list.size() == 0) {
                        System.out.println("You have no tasks in your list.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + "." + list.get(i));
                        }
                    }
                    break;
                case "done":
                    index = Integer.parseInt(tokens[1]);
                    list.get(index - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index - 1));
                    break;
                default:
                    t = new Task(input);
            }
            if (done)
                break;
        }

    }

    private static void addTask(Task t) {
        list.add(t);
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
    }

}
