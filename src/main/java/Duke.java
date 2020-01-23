import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm your task manager!\n");
        System.out.println("What tasks do you have dude?\n");
        Scanner io = new Scanner(System.in);

        String longCommand = io.nextLine();
        String[] keywords = longCommand.split(" ", 2);

        while(!keywords[0].equals("bye")) {
            System.out.println("    -----");
            switch (keywords[0]) {
                case "list":
                    handleList();
                    break;
                case "done":
                    int index = Integer.parseInt(keywords[1]) - 1;
                    handleDone(index);
                    break;
                case "todo": {
                    handleTodo(keywords[1]);
                    break;
                }
                case "event": {
                    handleEvent(keywords[1]);
                    break;
                }
                case "deadline": {
                    handleDeadline(keywords[1]);
                    break;
                }
                default:
                    System.out.println("    Not a valid command");
                    break;
            }
            System.out.println("    -----");
            longCommand = io.nextLine();
            keywords = longCommand.split(" ", 2);
        }
        System.out.println("    -----");
        System.out.println("    Bye bye friend!");
        System.out.println("    -----");
    }

    public static void handleList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= Task.tasks.size(); i++) {
            System.out.println("    " + i + ". " + Task.tasks.get(i - 1));
        }
    }

    public static void handleDone(int index) {
        System.out.println("    Nice! I've marked this task as done:");
        Task.tasks.get(index).done();
        System.out.println("    " + Task.tasks.get(index));
    }

    public static void handleEvent(String desc) {
        String[] strArr = desc.split(" /at ", 2);
        String todo = strArr[0];
        String time = strArr[1];
        Event task = new Event(todo, time);
        System.out.println("    Got it. I've added this task:");
        System.out.printf("    %s\n", task);
        Task.tasks.add(task);
        System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
    }

    public static void handleTodo(String desc) {
        Todo task = new Todo(desc);
        System.out.println("    Got it. I've added this task:");
        System.out.printf("    %s\n", task);
        Task.tasks.add(task);
        System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
    }

    public static void handleDeadline(String desc) {
        String[] strArr = desc.split(" /by ", 2);
        String todo = strArr[0];
        String time = strArr[1];
        Deadline task = new Deadline(todo, time);
        System.out.println("    Got it. I've added this task:");
        System.out.printf("    %s\n", task);
        Task.tasks.add(task);
        System.out.printf("    Now you have %d tasks in the list.\n", Task.tasks.size());
    }
}


