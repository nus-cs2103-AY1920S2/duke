package jiachen.duke;

import java.util.Scanner;

/**
 * The enum Command.
 */
enum Command {
    /**
     * Exit command command.
     */
    EXIT_COMMAND,
    /**
     * List command command.
     */
    LIST_COMMAND,
    /**
     * Done command command.
     */
    DONE_COMMAND,
    /**
     * Delete command command.
     */
    DELETE_COMMAND,
    /**
     * Todo command command.
     */
    TODO_COMMAND,
    /**
     * Deadline command command.
     */
    DEADLINE_COMMAND,
    /**
     * Event command command.
     */
    EVENT_COMMAND
}

/**
 * The type Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /** the Main entry point into the Duke program */
    public void run() {

        ui.printHeader();

        Scanner scanner = new Scanner(System.in);
        String desc = "";
        String timestamp = "";

        main:
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] separateLine = line.split(" ", 2);
            String commandStr = separateLine[0];
            String parameters = separateLine.length > 1 ? separateLine[1] : "";

            Task task;
            String[] taskInfo;
            String[] splitted;
            int taskId;

            try {

                Command command;

                switch (commandStr) {
                    case "bye":
                        command = Command.EXIT_COMMAND;
                        break;
                    case "list":
                        command = Command.LIST_COMMAND;
                        break;
                    case "done":
                        command = Command.DONE_COMMAND;
                        break;
                    case "delete":
                        command = Command.DELETE_COMMAND;
                        break;
                    case "todo":
                        command = Command.TODO_COMMAND;
                        break;
                    case "deadline":
                        command = Command.DEADLINE_COMMAND;
                        break;
                    case "event":
                        command = Command.EVENT_COMMAND;
                        break;
                    default:
                        throw new InvalidDukeCommandException();
                }
                switch (command) {
                    case EXIT_COMMAND:
                        ui.print("Bye. Hope to see you again soon!");
                        break main;
                    case LIST_COMMAND:
                        ui.printTasks(tasks);
                        break;
                    case DONE_COMMAND:
                        splitted = line.split(" ");
                        if (splitted.length < 2) {
                            throw new InvalidDukeFormatException("The index of a done cannot be empty.");
                        }
                        taskId = Integer.parseInt(splitted[1]);
                        if (taskId <= 0 || taskId > tasks.size()) {
                            throw new InvalidDukeFormatException("Invalid task index provided!");
                        }

                        task = tasks.get(taskId - 1);
                        task.markAsDone();
                        ui.printDoneTask(task);
                        storage.save(tasks);
                        break;
                    case DELETE_COMMAND: {
                        splitted = line.split(" ");
                        if (splitted.length < 2) {
                            throw new InvalidDukeFormatException("The index of a delete cannot be empty.");
                        }
                        taskId = Integer.parseInt(splitted[1]);
                        if (taskId <= 0 || taskId > tasks.size()) {
                            throw new InvalidDukeFormatException("Invalid task index provided!");
                        }
                    }
                    ui.printRemoveTask(tasks.remove(taskId - 1));
                    storage.save(tasks);
                    break;

                    case TODO_COMMAND:
                        task = new TodoTask(parameters);
                        tasks.add(task);
                        ui.printNewTask(task, tasks.size());
                        storage.save(tasks);
                        break;
                    case DEADLINE_COMMAND:
                        taskInfo = parameters.split("/by");
                        desc = "";
                        timestamp = "";
                        if (taskInfo.length > 0) {
                            desc = taskInfo[0].trim();
                        }
                        if (taskInfo.length > 1) {
                            timestamp = taskInfo[1].trim();
                        }

                        task = new DeadlineTask(desc, timestamp);

                        tasks.add(task);
                        ui.printNewTask(task, tasks.size());
                        storage.save(tasks);
                        break;

                    case EVENT_COMMAND:
                        taskInfo = parameters.split("/at");

                        if (taskInfo.length > 0) {
                            desc = taskInfo[0].trim();
                        }
                        if (taskInfo.length > 1) {
                            timestamp = taskInfo[1].trim();
                        }

                        task = new EventTask(desc, timestamp);

                        tasks.add(task);
                        ui.printNewTask(task, tasks.size());
                        storage.save(tasks);
                        break;
                }
            } catch (DukeException e) {
        ui.printError(e.toString());
      }
    }
  }
}
