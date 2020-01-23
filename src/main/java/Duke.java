import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        TaskList tasks = new TaskList();

        System.out.println("Hello from\n" + logo);
        while (isRunning && sc.hasNextLine()) {
            UserInput input = new UserInput(sc.nextLine());
            try {
                switch (input.getCommand()) {
                case "": {
                    break;
                }
                case "todo": 
                case "deadline": 
                case "event": {
                    try {
                        Task newTask = Duke.createTask(input);
                        String message = tasks.addTask(newTask);
                        System.out.println(message);
                        break;
                    } catch (DukeException e) {
                        System.out.println(e);
                        break;
                    }
                }
                case "list": {
                    System.out.println(tasks);
                    break;
                }
                case "done": {
                    try {
                        String message = Duke.markTask(input, tasks);
                        System.out.println(message);
                        break;
                    } catch (DukeException e) {
                        System.out.println(e);
                        break;
                    }
                }
                case "delete": {
                    try {
                        String message = Duke.deleteTask(input, tasks);
                        System.out.println(message);
                        break;
                    } catch (DukeException e) {
                        System.out.println(e);
                        break;
                    }
                }
                case "bye": {
                    isRunning = false;
                    break;
                }
                default: {
                    throw new DukeUnrecognisedCommandException(input.getCommand());
                }
                }
            } catch (DukeUnrecognisedCommandException e) {
                System.out.println(e);
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }

    public static String markTask(UserInput input, TaskList tasks) throws DukeException {
        if (input.getArguments().length == 0) {
            throw new DukeNoArgumentsException(input.getCommand());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        try {
            int taskIndex = Integer.parseInt(input.getArguments()[0]) - 1;
            Task task = tasks.getTask(taskIndex);
            return task.markAsCompleted();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(input.getArguments()[0]);
        }
    }

    public static String deleteTask(UserInput input, TaskList tasks) throws DukeException {
        if (input.getArguments().length == 0) {
            throw new DukeNoArgumentsException(input.getCommand());
        }
        if (tasks.isEmpty()) {
            throw new DukeEmptyTaskListException();
        }
        try {
            int taskIndex = Integer.parseInt(input.getArguments()[0]) - 1;
            return tasks.removeTask(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeInvalidTaskException(input.getArguments()[0]);
        }
    }

    public static Task createTask(UserInput input) throws DukeException {
        String taskType = input.getCommand();
        if (input.getArguments().length == 0) {
            throw new DukeNoArgumentsException(taskType);
        }
        switch (taskType) {
        case "todo": {
            return new TodoTask(input.getArgumentsAsString());
        }
        case "deadline": {
            String[] parts = input.getArgumentsAsString().split(" /by ", 2);
            if (parts.length != 2) {
                throw new DukeInvalidArgumentsException(taskType, 2, parts.length);
            }
            return new DeadlineTask(parts[0], parts[1]);
        }
        case "event": {
            String[] parts = input.getArgumentsAsString().split(" /at ", 2);
            if (parts.length != 2) {
                throw new DukeInvalidArgumentsException(taskType, 2, parts.length);
            }
            return new EventTask(parts[0], parts[1]);
        }
        default: {
            throw new DukeUnrecognisedTaskException(taskType);
        }
        }
    }
}
