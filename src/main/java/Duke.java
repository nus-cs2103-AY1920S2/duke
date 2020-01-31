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

        Storage data = new Storage();
        Tracker tracker = new Tracker();
        Scanner scanner = new Scanner(System.in);
        Parser command;

        if (data.hasPreviousData()) {
            data.loadData(tracker);
        }

        while (true) {
            try {
                command = new Parser(scanner.next());

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
                    int index = scanner.nextInt() - 1;
                    System.out.println("Nice! I've marked this task as done");
                    tracker.markDone(index);
                    System.out.println("  " + tracker.showList().get(index));
                    data.saveData(tracker.showList());
                } else if (command.isDelete()) {
                    int index = scanner.nextInt() - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + tracker.showList().get(index));
                    tracker.delete(index);
                    data.saveData(tracker.showList());
                } else {
                    Parser content;

                    try {
                        content = new Parser(scanner.nextLine(), command);
                        tracker.add(content.getTask());
                        data.saveData(tracker.showList());

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