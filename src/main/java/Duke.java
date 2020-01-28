import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

enum Command {
    EXIT_COMMAND,
    LIST_COMMAND,
    DONE_COMMAND,
    DELETE_COMMAND,
    TODO_COMMAND,
    DEADLINE_COMMAND,
    EVENT_COMMAND
}

public class Duke {
    public static void addTaskReport(Task task, int numOfTasks) {
        System.out.println("\t Got it. I've added this task: \n" +
                "\t\t" + task + "\n" +
                "\t Now you have " + numOfTasks + " tasks in the list.");
    }

    public static void persist(ArrayList<Task> tasks) {

        final String FILE_PATH = "./TASKS";
        StringBuilder buffer = new StringBuilder();
        for (Task task : tasks) {
            buffer.append(task.format()).append("\n");
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH));
            writer.write(buffer.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");


        Scanner scanner = new Scanner(System.in);
        String desc = "";
        String timestamp = "";

        main:
        while (scanner.hasNextLine()) {
            System.out.println("\t____________________________________________________________");
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
                        System.out.println("\t Bye. Hope to see you again soon!");
                        System.out.println("\t____________________________________________________________");
                        break main;
                    case LIST_COMMAND:
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.println("\t " + i + ". " + tasks.get(i - 1));
                        }
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
                        System.out.println("\t Nice! I've marked this task as done: ");
                        System.out.println("\t\t" + task);
                        Duke.persist(tasks);
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

                    task = tasks.remove(taskId - 1);

                    System.out.println("\t Noted. I've removed this task: ");
                    System.out.println("\t\t" + task);
                    Duke.persist(tasks);
                    break;

                    case TODO_COMMAND:
                        task = new TodoTask(parameters);
                        tasks.add(task);
                        addTaskReport(task, tasks.size());
                        Duke.persist(tasks);
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
                        addTaskReport(task, tasks.size());
                        Duke.persist(tasks);
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
                        addTaskReport(task, tasks.size());
                        Duke.persist(tasks);
                        break;
                }
                System.out.println("\t____________________________________________________________");
            } catch (DukeException e) {
                System.out.println("\t " + e);
                System.out.println("\t____________________________________________________________");
            }
        }

    }
}
