import java.util.Scanner;

public class Duke {
    public enum CommandStatus{ SUCCESS, FAIL }

    static Scanner scanner = new Scanner(System.in);
    static Task taskList[] = new Task[100];
    static int taskListIndex = 0;
    static boolean programIsRunning = true;

    public static void main(String[] args) {
        printHomeScreen();

        while(programIsRunning){
            String commands[] = new String[2];
            commands = getInputSeparateCommands();
            CommandStatus commandResult = CommandStatus.FAIL;

            if  (commands[0].equals("bye")) {
                commandResult =  handleByeCommand();
            } else if (commands[0].equals("list")) {
                commandResult = handleListCommand();
            } else if (commands[0].equals("done")) {
                commandResult = handleDoneCommand (commands);
            } else if (commands[0].equals("todo") || commands[0].equals("deadline") || commands[0].equals("event")){
                commandResult = handleAddCommand(commands);
            } else {
                System.out.println("I don't understand that command");
            }

            if  (commandResult == CommandStatus.FAIL){
                System.out.println("Command Failed");
            }
        }
        printExitScreen();
    }

    public static void printHomeScreen(){
        printLineSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    public static void printExitScreen() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void printLineSeparator(){
        System.out.println("---------------------------------------");
    }

    public static CommandStatus handleByeCommand(){
        programIsRunning = false;
        return CommandStatus.SUCCESS;
    }

    public static String[] getInputSeparateCommands(){
        String input = scanner.nextLine();
        input = input.trim();

        String commands[] = new String[2];
        commands = input.split(" ", 2);

        return commands;
    }

    public static CommandStatus handleListCommand() {
        int entryNum = 1;
        printLineSeparator();

        for (Task task : taskList) {
            if (task == null) {
                break;
            } else {
                System.out.print(String.valueOf(entryNum) + ".");
                System.out.println(task);
                entryNum++;
            }
        }
        printLineSeparator();

        return CommandStatus.SUCCESS;
    }


    public static CommandStatus handleDoneCommand(String commands[]) {
        String taskDescription = commands[1];

        // Looks for the matching description task to mark as done
        boolean taskFinished = false;
        for (Task task : taskList) {
            if (task == null) {
                taskFinished = false;
            } else if (task.description.equals(taskDescription)) {
                task.isDone = true;

                taskFinished =  true;
                System.out.print("Nice! I've marked this task as done:\n\t");
                System.out.println(task);
                printLineSeparator();
            }
        }

        return taskFinished ? CommandStatus.SUCCESS : CommandStatus.FAIL;
    }

    public static CommandStatus handleAddCommand(String[] commands) {
        String taskDescription = commands[1];
        String taskDetails[] = new String[2];

        Task newTask;
        switch (commands[0]) {
            case "todo" :
                newTask = new Todo(taskDescription);
                break;

            case "deadline" :
                taskDetails = taskDescription.split(" /by ");

                if (taskDetails.length < 2){
                    return CommandStatus.FAIL;
                }

                newTask = new Deadlines(taskDetails[0], taskDetails[1]);
                break;

            case "event" :
                taskDetails = taskDescription.split(" /at ");

                if (taskDetails.length < 2){
                    return CommandStatus.FAIL;
                }

                newTask = new Event(taskDetails[0], taskDetails[1]);
                break;

            default:
                return CommandStatus.FAIL;
        }

        taskList[taskListIndex] = newTask;
        taskListIndex++;

        printSuccessfulAddEntry(newTask);

        return CommandStatus.SUCCESS;
    }

    public static void printSuccessfulAddEntry(Task newTask) {
        printLineSeparator();
        System.out.println("Got it. I've added this task:\n\t" + newTask);
        System.out.println("Now you have " + taskListIndex + " task"
                + ((taskListIndex > 1) ? "s" : "")
                + " in the list.");
        printLineSeparator();
    }
}
