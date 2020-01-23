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
            switch (input.getCommand()) {
            case "": {
                break;
            }
            case "todo": 
            case "deadline": 
            case "event": {
                Task newTask = Duke.createTask(input);
                if (newTask == null) {
                    System.out.println("Cannot add new task!");
                    break;
                }
                String message = tasks.addTask(newTask);
                System.out.println(message);
                break;
            }
            case "list": {
                System.out.println(tasks);
                break;
            }
            case "done": {
                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(input.getArguments()[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println(String.format(
                            "'%s' is not valid task number!",
                            input.getArguments()[0]
                    ));
                    break;
                }
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    System.out.println(String.format(
                            "Task #%d supplied is not within 1 to %d!",
                            (taskIndex + 1), tasks.size()
                    ));
                    break;
                }
                Task task = tasks.getTask(taskIndex);
                String message = task.markAsCompleted();
                System.out.println(message);
                break;
            }
            case "bye": {
                isRunning = false;
                break;
            }
            default: {
                System.out.println(String.format(
                        "Oops what's '%s'?",
                        input.getCommand()
                ));
            }
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }

    public static Task createTask(UserInput input) {
        String taskType = input.getCommand();
        if (input.getArguments().length == 0) {
            System.out.println(String.format(
                    "No description given for %s task!",
                    taskType
            ));
            return null;
        }
        switch (taskType) {
        case "todo": {
            return new TodoTask(input.getArgumentsAsString());
        }
        case "deadline": {
            String[] parts = input.getArgumentsAsString().split(" /by ", 2);
            if (parts.length != 2) {
                System.out.println("Invalid arguments for deadline task!");
                return null;
            }
            return new DeadlineTask(parts[0], parts[1]);
        }
        case "event": {
            String[] parts = input.getArgumentsAsString().split(" /at ", 2);
            if (parts.length != 2) {
                System.out.println("Invalid arguments for event task!");
                return null;
            }
            return new EventTask(parts[0], parts[1]);
        }
        default: {
            break;
        }
        }
        System.out.println(String.format("What is a '%s' task?", taskType));
        return null;
    }
}
