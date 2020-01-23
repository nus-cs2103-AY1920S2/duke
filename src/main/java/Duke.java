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
            case "todo": {
                TodoTask newTask = new TodoTask(input.getArgumentsAsString());
                String message = tasks.addTask(newTask);
                System.out.println(message);
                break;
            }
            case "deadline": {
                String[] parts = input.getArgumentsAsString().split(" /by ", 2);
                DeadlineTask newTask = new DeadlineTask(parts[0], parts[1]);
                String message = tasks.addTask(newTask);
                System.out.println(message);
                break;
            }
            case "event": {
                String[] parts = input.getArgumentsAsString().split(" /at ", 2);
                DeadlineTask newTask = new DeadlineTask(parts[0], parts[1]);
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
                Task newTask = new Task(input.toString());
                String message = tasks.addTask(newTask);
                System.out.println(message);
            }
            }
        }
        System.out.println("Goodbye!");
        sc.close();
    }
}
