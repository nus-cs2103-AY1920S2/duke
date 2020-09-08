package duke;

import java.util.Scanner;
import duke.task.*;
import duke.exception.DukeException;

public class Duke {
    public static final int NUM_ARGS_DEADLINE = 2;
    public static final int NUM_ARGS_EVENT = 2;

    public static Scanner scanner = new Scanner(System.in);
    public static Task[] tasksList = new Task[100];
    public static int tasksListIndex = 0;
    public static boolean programIsRunning = true;

    public static void main(String[] args) {
        printHomeScreen();

        while(programIsRunning){
            String[] input;
            input = getInputSeparateCommandAndDescription();

            String command = input[0];
            String description = "";
            if(input.length == 2)
               description = input[1];

            try {
                switch(command) {
                case "bye":
                    handleByeCommand();
                    break;
                case "list":
                    handleListCommand();
                    break;
                case "done":
                    handleDoneCommand(description);
                    break;
                case "todo": // Fallthrough
                case "deadline": // Fallthrough
                case "event":
                    handleAddCommand(command, description);
                    break;
                default:
                    throw new DukeException("Duke does not understand that command");
                }
            } catch(DukeException exception) {
                handleException(exception);
            }
        }
        printExitScreen();
    }

    public static void printHomeScreen(){
        printLineSeparator();
        System.out.println("Hello! I'm Duke" + System.lineSeparator() +
                            "What can I do for you?");
        printLineSeparator();
    }

    public static void printExitScreen() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void printLineSeparator(){
        System.out.println("---------------------------------------");
    }

    public static void printSuccessfulAddEntry(Task newTask) {
        printLineSeparator();

        System.out.print("Got it. I've added this task:" + System.lineSeparator() +
                "\t" + newTask + System.lineSeparator()
                +"Now you have " + tasksListIndex + " task");

        if(tasksListIndex > 1) {
            System.out.print("s");
        }

        System.out.println(" in the list.");

        printLineSeparator();
    }

    public static void handleByeCommand(){
        programIsRunning = false;
    }

    public static String[] getInputSeparateCommandAndDescription(){
        String input = scanner.nextLine();
        input = input.trim();

        String[] commands;
        commands = input.split(" ", 2);

        return commands;
    }

    public static void handleListCommand() {
        int entryNum = 1;

        printLineSeparator();

        for(Task task : tasksList) {
            if(task == null) {
                break;
            } else {
                System.out.println(entryNum + "." + task);
                entryNum++;
            }
        }

        printLineSeparator();
    }

    public static void handleDoneCommand(String description) throws DukeException{
        if(description.equals("")){
            throw new DukeException("The done command format is: done <taskDescription>");
        }

        // Looks for a task with matching the description task and marks it as done
        for(Task task : tasksList) {
            if(task == null) {
                throw new DukeException("Task not found");
            } else if(task.description.equals(description) && !task.isDone ) {
                task.isDone = true;

                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                                    "\t" + task);
                printLineSeparator();

                return;
            }
        }
    }

    public static void handleAddCommand(String command, String description) throws DukeException {
        Task newTask = null;

        switch(command){
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

        if(newTask == null){
            throw new DukeException("Unable to create the new Task");
        }

        tasksList[tasksListIndex] = newTask;
        tasksListIndex++;

        printSuccessfulAddEntry(newTask);
    }

    public static void handleException(DukeException exception){
        System.out.println("Exception!" + System.lineSeparator() +
                            "\t" + exception.description);
    }
    public static Todo createToDo(String description) throws DukeException{
        if (description.equals("")){
            throw new DukeException("The todo format is: todo <desc>");
        }
        return new Todo(description);
    }

    public static Deadlines createDeadline(String description) throws DukeException{
        String[] taskDetails = description.split(" /by ");
        if(taskDetails.length < NUM_ARGS_DEADLINE){
            throw new DukeException("The deadline format is: deadline <desc> /by <time>");
        }

        return new Deadlines(taskDetails[0], taskDetails[1]);
    }

    public static Event createEvent(String description) throws DukeException{
        String[] taskDetails = description.split(" /at ");
        if(taskDetails.length < NUM_ARGS_EVENT){
            throw new DukeException("The event format is: event <desc> /at <time>");
        }

        return new Event(taskDetails[0], taskDetails[1]);
    }

}
