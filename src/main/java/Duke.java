import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String machine = "Dude: ";
    public static String user = "dude: ";
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        greeting();
        String command = sc.next();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list();
            } else if (command.equals("done")) {
                markAsDone();
            } else {
                command += sc.nextLine();
                addTask(command);
            }
            System.out.print(user);
            command = sc.next();
        }
        System.out.println(machine + "Okay see ya dude!");
    }

    public static void greeting() {
        String welcome = "\n"
                + machine + "Hi dude! I'm your Dude\n"
                + "      What do you want dude?\n"
                + user;
        System.out.print(welcome);
    }

    public static void list() {
        System.out.println(machine + "Here's your list of tasks dude:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("      " + i + ". " + list.get(i-1));
        }
    }

    public static void addTask(String command) {
        Task task = new Task(command);
        list.add(task);
        System.out.println(machine + task + " is added");
    }
    public static void markAsDone() {
        int i = sc.nextInt();
        Task task = list.get(i-1);
        task.markAsDone();
        System.out.println(machine + "Alright dude this task is marked as done:");
        System.out.println("      " + i + ". " + task);
    }

}
