import java.util.Scanner;

public class Duke {
    public enum CommandStatus{ SUCCESS, FAIL }

    public static final int NUM_ARGS_DEADLINE = 2;
    public static final int NUM_ARGS_EVENT = 2;

    public static Scanner scanner = new Scanner(System.in);
    public static Task[] tasksList = new Task[100];
    public static int tasksListIndex = 0;
    public static boolean programIsRunning = true;

    public static void main(String[] args) {
        printHomeScreen();

        while(programIsRunning){
            String[] input = new String[2];
            input = getInputSeparateCommandAndDescription();

            String command = input[0];
            String description = "";
            if (input.length == 2)
               description = input[1];

            CommandStatus commandResult = CommandStatus.FAIL;

            switch(command) {
            case "bye" :
                commandResult = handleByeCommand();
                break;
            case "list" :
                commandResult = handleListCommand();
                break;
            case "done" :
                commandResult = handleDoneCommand(command, description);
                break;
            case "todo" : // Fallthrough
            case "deadline" : // Fallthrough
            case "event" :
                commandResult = handleAddCommand(command, description);
                break;
            default :
                System.out.println("I don't understand that command");
                break;
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

    public static String[] getInputSeparateCommandAndDescription(){
        String input = scanner.nextLine();
        input = input.trim();

        String[] commands = new String[2];
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


        public static CommandStatus handleDoneCommand(String command, String description) {
        if (description == null){
            return CommandStatus.FAIL;
        }
        // Looks for a task with a matching description task to mark as done
        for (Task task : tasksList) {
            if (task == null) {
                return CommandStatus.FAIL;
            } else if (task.description.equals(description) && task.isDone == false) {
                task.isDone = true;


                System.out.print("Nice! I've marked this task as done:\n\t");
                System.out.println(task);
                printLineSeparator();

                return CommandStatus.SUCCESS;
            }
        }

        return CommandStatus.FAIL;
    }

    public static CommandStatus handleAddCommand(String command, String description) {
        Task newTask = null;

        switch (command){
        case "todo" :
            newTask = createToDo(description);
            break;
        case "deadline" :
            newTask = createDeadline(description);
            break;
        case "event":
            newTask = createEvent(description);
            break;
        default:
            break;
        }

        if (newTask == null){
            return  CommandStatus.FAIL;
        }

        tasksList[tasksListIndex] = newTask;
        tasksListIndex++;

        printSuccessfulAddEntry(newTask);

        return CommandStatus.SUCCESS;
    }

    public static Todo createToDo(String description){
        return new Todo(description);
    }

    public static Deadlines createDeadline(String description){
        String[] taskDetails = description.split(" /by ");
        if (taskDetails.length < NUM_ARGS_DEADLINE){
            return null;
        }

        return new Deadlines(taskDetails[0], taskDetails[1]);
    }

    public static Event createEvent(String description){
        String[] taskDetails = description.split(" /at ");
        if (taskDetails.length < NUM_ARGS_DEADLINE){
            return null;
        }

        return new Event(taskDetails[0], taskDetails[1]);
    }
    
    public static void printSuccessfulAddEntry(Task newTask) {
        printLineSeparator();

        System.out.print("Got it. I've added this task:" + System.lineSeparator() +
                "\t" + newTask + System.lineSeparator()
                +"Now you have " + tasksListIndex + " task");

        if (tasksListIndex > 1) {
            System.out.print("s");
        }

        System.out.println(" in the list.");

        printLineSeparator();
    }
}
