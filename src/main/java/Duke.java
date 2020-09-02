import java.util.Scanner;

public class Duke {
    public enum CommandStatus{ SUCCESS, FAIL }

    static final int NUM_ARGS_DEADLINE = 2;
    static final int NUM_ARGS_EVENT = 2;

    static Scanner scanner = new Scanner(System.in);
    static Task tasksList[] = new Task[100];
    static int tasksListIndex = 0;
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

        for (Task task : tasksList) {
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
        if (commands.length < 2){
            return CommandStatus.FAIL;
        }
        String taskDescription = commands[1];

        // Looks for a task with a matching description task to mark as done
        boolean taskIsFinished = false;
        for (Task task : tasksList) {
            if (task == null) {
                taskIsFinished = false;
            } else if (task.description.equals(taskDescription) && task.isDone == false) {
                task.isDone = true;
                taskIsFinished =  true;

                System.out.print("Nice! I've marked this task as done:\n\t");
                System.out.println(task);
                printLineSeparator();

                break;
            }
        }

        if (taskIsFinished){
            return CommandStatus.SUCCESS;
        } else {
            return CommandStatus.FAIL;
        }
    }

    public static CommandStatus handleAddCommand(String[] commands) {
        Task newTask = createTask(commands);
        if (newTask == null){
            return  CommandStatus.FAIL;
        }

        tasksList[tasksListIndex] = newTask;
        tasksListIndex++;

        printSuccessfulAddEntry(newTask);

        return CommandStatus.SUCCESS;
    }

    public static Task createTask(String[] commands){
        String taskDescription = commands[1];
        String[] taskDetails = new String[2];

        Task newTask;
        switch (commands[0]) {
            case "todo" :
                newTask = new Todo(taskDescription);
                break;
            case "deadline" :
                taskDetails = taskDescription.split(" /by ");
                if (taskDetails.length < NUM_ARGS_DEADLINE){
                    return null;
                }

                newTask = new Deadlines(taskDetails[0], taskDetails[1]);
                break;
            case "event" :
                taskDetails = taskDescription.split(" /at ");
                if (taskDetails.length < NUM_ARGS_EVENT){
                    return null;
                }

                newTask = new Event(taskDetails[0], taskDetails[1]);
                break;
            default:
                return null;
        }

        return newTask;
    }
    public static void printSuccessfulAddEntry(Task newTask) {
        printLineSeparator();
        System.out.println("Got it. I've added this task:\n\t" + newTask);
        System.out.print("Now you have " + tasksListIndex + " task");
        if (tasksListIndex > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
        printLineSeparator();
    }
}
