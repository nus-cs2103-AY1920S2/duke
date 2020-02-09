import collection.TaskCollection;
import storage.PersistentStorageObserver;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import validator.CommandParser;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A chatbot interface.
 */
public class Duke {
    static String terminateCommand = "bye";
    static TaskCollection taskCollection = new TaskCollection();
    static PersistentStorageObserver persistentStorageObserver = new PersistentStorageObserver(taskCollection);
    // Use a hashmap to store command key to prevent developer error in typing command keys across different functions
    static HashMap<String, Integer> commandCodeMapping = new HashMap<String, Integer>() {
        {
            put("list", 0);
            put("mark done", 1);
            put("event", 2);
            put("todo", 3);
            put("deadline", 4);
            put("delete", 5);
            put("find", 6);
            put("invalid command", 7);
        }
    };
    static CommandParser commandParser = new CommandParser(commandCodeMapping);

    /**
     * Switching logic for different commands.
     * @param command user command
     */
    private static void commandProcessor(String command) {
        try {
            Integer commandCode = commandParser.getCommandCode(command);
            if (commandCode == commandCodeMapping.get("list")) {
                for (int i = 0; i < taskCollection.size(); i++) {
                    System.out.printf(taskCollection.get(i).getFullDetail(i));
                }
            } else if (commandCode == commandCodeMapping.get("mark done")) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                if (taskIndex < taskCollection.size()) {
                    taskCollection.get(taskIndex).markDone();
                }
            } else if (commandCode == commandCodeMapping.get("delete")) {
                Integer taskIndex = Integer.parseInt(command.split(" ")[1]);
                if (taskIndex < taskCollection.size()) {
                    taskCollection.remove(taskIndex.intValue());
                }
            } else if (commandCode == commandCodeMapping.get("find")) {
                String findKeyword = command.split(" ")[1];
                ArrayList<Task> matchTasks = taskCollection.find(findKeyword);
                for (int i = 0; i < matchTasks.size(); i++) {
                    System.out.printf(matchTasks.get(i).getFullDetail(i));
                }
            } else if (commandCode == commandCodeMapping.get("todo")) {
                String todoContent = command.split(" ", 2)[1];
                Task todo = new Todo(todoContent);
                taskCollection.add(todo);
            } else if (commandCode == commandCodeMapping.get("deadline")) {
                String deadlineContent = command.split(" ", 2)[1];
                String deadlineDesc = deadlineContent.split("/by")[0];
                String deadlineTime = deadlineContent.split("/by")[1];
                Task deadline = new Deadline(deadlineDesc, deadlineTime);
                taskCollection.add(deadline);
            } else if (commandCode == commandCodeMapping.get("event")) {
                String eventContent = command.split(" ", 2)[1];
                String eventDesc = eventContent.split("/at")[0];
                String eventTime = eventContent.split("/at")[1];
                Task event = new Event(eventDesc, eventTime);
                taskCollection.add(event);
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
