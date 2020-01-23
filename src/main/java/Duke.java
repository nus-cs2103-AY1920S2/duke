import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String machine = "Dude: ";
    public static String user = "dude: ";
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        greeting();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list();
            } else {
                addTask(command);
            }
            System.out.print(user);
            command = sc.nextLine();
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
        list.add(command);
        System.out.println(machine + command + " is added");
    }
}
