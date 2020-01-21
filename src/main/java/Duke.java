import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static Scanner sc;
    static ArrayList<Task> tasks;
    static int count;

    public static void readCommand(String str) {
        Command command = Command.lookUp(str);
        switch (command) {
            case LIST:
                System.out.println("Here are all your tasks:");
                for (Task task: tasks) {
                    System.out.println(task.getId() + "." + task);
                }
                break;
            case DONE:
                int id = sc.nextInt();
                tasks.get(id - 1).setDone(true);
                System.out.println("Nice! I've marked this task as done: \n" +
                        "  " + tasks.get(id - 1));
                break;
            case TODO:
                String todo = sc.nextLine();
                Task task = new Todo(tasks.size() + 1, todo);
                tasks.add(task);
                System.out.println("I've added this task: \n" +
                        "  " + task + "\n Now you have " +
                        tasks.size() + " tasks in the list." );
                break;
            case EVENT:

                break;
            case DEADLINE:

                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String lineBreak = "_________________________" +
                "_________________________";
        sc = new Scanner(System.in);
        tasks = new ArrayList<>();
        count = 1;

        System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);

        while (sc.hasNext()) {
            String next = sc.next();
            if (next.equals("bye")) break;
            else {
                System.out.println(lineBreak);
                readCommand(next);
                System.out.println(lineBreak);
            }
        }
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
