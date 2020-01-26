import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] tokens;
        boolean done = false;
        ArrayList<Task> list = new ArrayList<>();
        Task t;
        int index;
        while (true) {
            input = scanner.nextLine();
            tokens = input.split(" ");
            switch (tokens[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    done = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    break;
                case "done":
                    index = Integer.parseInt(tokens[1]);
                    list.get(index - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(list.get(index - 1));
                    break;
                default:
                    t = new Task(input);
                    list.add(t);
                    System.out.println("added: " + input);
            }
            if (done)
                break;
        }
    }
}
