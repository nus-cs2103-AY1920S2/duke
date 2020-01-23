import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String machine = "Dude: ";
    public static String user = "dude: ";
    public static ArrayList<String> commandList = new ArrayList<>();
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        commandList.add("list");
        commandList.add("done");
        commandList.add("todo");
        commandList.add("event");
        commandList.add("deadline");
        greeting();
        String command = sc.next();

        while (!command.equals("bye")) {
            try{
                checkCommand(command);
                if (command.equals("list")) {
                    list();
                } else if (command.equals("done")) {
                    markAsDone();
                } else {
                    String details = sc.nextLine();
                    try {
                        addTask(command, details);
                    } catch (EmptyDescriptionException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
            catch (InvalidCommandException ex) {
                System.out.println(ex.getMessage());
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
            Task task = list.get(i-1);
            System.out.println("      " + i + ". " + task);
        }
    }

    public static void markAsDone() {
        int i = sc.nextInt();
        Task task = list.get(i-1);
        task.markAsDone();
        System.out.println(machine + "Alright dude this task is marked as done:");
        System.out.println("      " + i + ". " + task);
    }

    public static void checkCommand(String command) throws InvalidCommandException {
        if (!commandList.contains(command)) {
            throw new InvalidCommandException("      Sorry dude but that won't command me!");
        }
    }

    public static void addTask(String command, String details) throws EmptyDescriptionException{
        if (details.equals("")) {
            throw new EmptyDescriptionException("      Wait dude your task is...?");
        }
        String[] arr = new String[2];
        switch (command) {
            case "todo":
                addTodo(details);
                break;
            case "event":
                arr = details.split("/at");
                addEvent(arr[0], arr[1]);
                break;
            case "deadline":
                arr = details.split("/by");
                addDeadline(arr[0], arr[1]);
                break;
        }
    }

    public static void addTodo(String details) {
        Task task = new Todo(details);
        list.add(task);
        System.out.println(machine + "Dude now you have even more things to do:");
        System.out.println("      " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        list.add(task);
        System.out.println(machine + "That's strange dude your pile of deadlines suddenly grew:");
        System.out.println("      " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void addEvent(String description, String at) {
        Task task = new Event(description, at);
        list.add(task);
        System.out.println(machine + "Woohoo what an eventful life:");
        System.out.println("      " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
}
