import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A chatbot interface.
 */
public class Duke {
    static String terminateCommand = "bye";
    static ArrayList<Task> tasks = new ArrayList<Task>();
    // Use a hashmap to store command key to prevent developer error in typing command keys across different functions
    static HashMap<String, Integer> commandCodeMapping = new HashMap<String, Integer>() {
        {
            put("list", 0);
            put("mark done", 1);
            put("event", 2);
            put("todo", 3);
            put("deadline", 4);
            put("delete", 5);
            put("invalid command", 6);
        }
    };

    /**
     * This function is to verify if command valid type (for later extension) so that main functions don't need to check
     * anymore.
     * @param command user command
     * @return
     */
    private static Integer getCommandCode(String command) {
        try {
            String firstCommandType = command.split(" ")[0];
            String[] commandSplit = command.split(" ");
            if (command.equals("list")) {
                return commandCodeMapping.get("list");
            } else if (firstCommandType.equals("done") && commandSplit.length == 2) {
                return commandCodeMapping.get("mark done");
            } else if (firstCommandType.equals("delete") && commandSplit.length == 2) {
                return commandCodeMapping.get("delete");
            } else {
                String taskType = command.split(" ", 2)[0];
                if (taskType.equals("todo")) {
                    return commandCodeMapping.get("todo");
                } else {
                    String content = command.split(" ", 2)[1];
                    if (taskType.equals("deadline")) {
                        String time = content.split("/by")[1];
                        LocalDate.parse(time.trim(), DateTimeFormatter.BASIC_ISO_DATE);
                        return commandCodeMapping.get("deadline");
                    } else if (taskType.equals("event")) {
                        String time = content.split("/at")[1];
                        LocalDate.parse(time.trim(), DateTimeFormatter.BASIC_ISO_DATE);
                        return commandCodeMapping.get("event");
                    }
                }
            }
        } catch (Exception e) {
            return commandCodeMapping.get("invalid command");
        }
        return commandCodeMapping.get("invalid command");
    }

    /**
     * Switching logic for different commands.
     * @param command user command
     */
    private static void commandProcessor(String command) {
        try {
            Integer commandCode = getCommandCode(command);
            if (commandCode == commandCodeMapping.get("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf(tasks.get(i).getFullDetail(i));
                }
            } else if (commandCode == commandCodeMapping.get("mark done")) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                if (taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markDone();
                }
            } else if (commandCode == commandCodeMapping.get("delete")) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                System.out.println("HIEU");
                if (taskIndex < tasks.size()) {
                    tasks.remove(taskIndex.intValue());
                }
            } else if (commandCode == commandCodeMapping.get("todo")) {
                String todoContent = command.split(" ", 2)[1];
                Task todo = new Todo(todoContent);
                tasks.add(todo);
            } else if (commandCode == commandCodeMapping.get("deadline")) {
                String deadlineContent = command.split(" ", 2)[1];
                String deadlineDesc = deadlineContent.split("/by")[0];
                String deadlineTime = deadlineContent.split("/by")[1];
                Task deadline = new Deadline(deadlineDesc, deadlineTime);
                tasks.add(deadline);
            } else if (commandCode == commandCodeMapping.get("event")) {
                String eventContent = command.split(" ", 2)[1];
                String eventDesc = eventContent.split("/at")[0];
                String eventTime = eventContent.split("/at")[1];
                Task event = new Event(eventDesc, eventTime);
                tasks.add(event);
            } else if (commandCode == commandCodeMapping.get("invalid command")) {
                System.out.printf("OOPS Wrong command\n");
            }
        } catch (Exception e) {
            System.out.println(e);
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
