import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A chatbot interface.
 */
public class Duke {
    static String terminateCommand = "bye";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Switching logic for different commands.
     * @param command user command
     */
    private static void commandProcessor(String command) {
        switch (command.split(" ")[0]) {
        case "list":
            // In case command might be like : list all your homework, then it should be added as a task
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%s[%s][%c] %s\n",
                            i, tasks.get(i).getShortName(),
                            (char)(Integer.parseInt(tasks.get(i).getStatusIcon(), 16)),
                            tasks.get(i).description);
                }
                return;
            }
            break;
        case "done":
            if (command.split(" ").length == 2) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                if (taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markDone();
                    return;
                }
            }
            break;
        default:
            Task t;
            String taskType = command.split(" ", 2)[0];
            String content = command.split(" ", 2)[1];
            if (taskType.equals("todo")) {
                t = new Todo(content);
            } else if (taskType.equals("deadline")) {
                String desc = content.split("/by")[0];
                String deadlineTime = content.split("/by")[1];
                t = new Deadline(desc, deadlineTime);
            } else if (taskType.equals("event")) {
                String desc = content.split("/at")[0];
                String eventTime = content.split("/at")[1];
                t = new Event(desc, eventTime);
            } else {
                return;
            }
            tasks.add(t);
            System.out.println("Got it. I've added this task: " + command);
            System.out.printf("Now you have %s tasks in this list\n", tasks.size());
        }
    }

    /**
     * Long-polling for user commands.
     */
    private static void inputProcessor() {
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();

        while (!userCommand.equals(terminateCommand)) {
            commandProcessor(userCommand);
            userCommand = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Main entry to the bot program.
     * @param args external parameters.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        inputProcessor();
    }
}
