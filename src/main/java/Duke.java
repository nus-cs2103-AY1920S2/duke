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

        DataManager data = new DataManager();
        Tracker tracker = new Tracker();
        Scanner scanner = new Scanner(System.in);
        Input command;

        if (data.hasPreviousData()) {
            data.loadData(tracker);
        }

        while (true) {
            try {
                command = new Input(scanner.next());

                if (command.isBye()) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.isList()) {
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
                } else if (command.isDelete()) {
                    int itemNo = scanner.nextInt() - 1;
                    System.out.println("Noted. I've removed this task:");
                    tracker.delete(itemNo);
                } else {
                    Input content;

                    try {
                        content = new Input(scanner.nextLine(), command);
                        tracker.add(content.getTask());

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + content.getTask());
                        System.out.println("Now you have " + tracker.getTotalTasks() + " task(s) in the list.");
                    } catch (DukeException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}