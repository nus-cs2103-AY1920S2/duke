import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Tracker tracker = new Tracker();
        Scanner scanner = new Scanner(System.in);
        Command command = new Command(scanner.next());

        while (!command.isBye()) {
            if (command.isList()) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < tracker.getTotalTasks(); i++) {
                    int itemNo = i + 1;
                    Task task = tracker.showList().get(i);
                    System.out.println(itemNo + "." + task);
                }
            } else if (command.isDone()) {
                int itemNo = scanner.nextInt() - 1;
                System.out.println("Nice! I've marked this task as done");
                tracker.markDone(itemNo);

            } else {
                String content = scanner.nextLine();
                Task task;

                switch (command.getCmd()) {
                    case "deadline":
                        String [] deadlineArray = content.split(" /by ");
                        task = new Deadline(deadlineArray[0], deadlineArray[1]);
                        break;
                    case "event":
                        String [] eventArray = content.split(" /at ");
                        task = new Event(eventArray[0], eventArray[1]);
                        break;
                    default:
                        task = new ToDo(content);
                }

                tracker.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have " + tracker.getTotalTasks() + " tasks in the list.");
            }

            command = new Command(scanner.next());
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}