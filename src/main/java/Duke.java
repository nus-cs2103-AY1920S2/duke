import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What tasks do you have dude?\n");
        Scanner io = new Scanner(System.in);
        String command = io.next();
        String desc = io.nextLine();

        while(!command.equals("bye")) {
            System.out.println("   -----");
            if (command.equals("list")) {
                System.out.println("   Here are the tasks in your list:");
                for (int i = 1; i <= Task.tasks.size(); i++) {
                    System.out.println("   " + i + ". " + Task.tasks.get(i - 1));
                }
            } else if (command.equals("done")) {
                int index = Integer.parseInt(desc.substring(1)) - 1;
                if (Task.tasks.size() <= index) {
                    System.out.println("   There is no task number " + (index + 1));
                    System.out.println("   -----");
                    command = io.nextLine();
                    continue;
                }
                System.out.println("   Nice! I've marked this task as done:");
                Task.tasks.get(index).done();
                System.out.println("   " + Task.tasks.get(index));
            } else if (command.equals("todo")) {
                Todo task = new Todo(desc.substring(1));
                System.out.println("   Got it. I've added this task:");
                System.out.printf("   %s\n", task);
                Task.tasks.add(task);
                System.out.printf("   Now you have %d tasks in the list.\n", Task.tasks.size());
            } else if (command.equals("event")) {
                Event task = new Event(desc.substring(1));
                System.out.println("   Got it. I've added this task:");
                System.out.printf("   %s\n", task);
                Task.tasks.add(task);
                System.out.printf("   Now you have %d tasks in the list.\n", Task.tasks.size());
            } else if (command.equals("deadline")) {
                Deadline task = new Deadline(desc.substring(1));
                System.out.println("   Got it. I've added this task:");
                System.out.printf("   %s\n", task);
                Task.tasks.add(task);
                System.out.printf("   Now you have %d tasks in the list.\n", Task.tasks.size());
            } else {
                System.out.println("   Not a valid command");
            }
            System.out.println("   -----");
            command = io.next();
            desc = io.nextLine();
        }
        System.out.println("   -----");
        System.out.println("   Bye bye friend!");
        System.out.println("   -----");
    }
}
