package duke;


import duke.task.*;
import duke.exception.DukeException;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static final String TASKS_LIST_SAVE_PATH = "data/taskList.txt";
    public static final int SAVE_FILE_ELEMENTS_PER_ROW = 3;

    public static final int NUM_ARGS_DEADLINE = 2;
    public static final int NUM_ARGS_EVENT = 2;

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Task> tasksList = new ArrayList<>();
    public static boolean programIsRunning = true;

    // MAIN LOOP //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        printHomeScreen();

        try {
            loadTasksList();
        } catch (DukeException exception){
            handleException(exception);
        }

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
                    saveTasksList();
                    break;
                case "todo": // Fallthrough
                case "deadline": // Fallthrough
                case "event":
                    handleAddCommand(command, description);
                    saveTasksList();
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

    // GENERAL HELPERS //////////////////////////////////////////////////////////////////////////
    public static String[] getInputSeparateCommandAndDescription(){
        String input = scanner.nextLine();
        input = input.trim();

        String[] commands;
        commands = input.split(" ", 2);

        return commands;
    }

    // PRINT HELPERS //////////////////////////////////////////////////////////////////////////
    public static void print(String text){
        System.out.println(text);
    }
    public static void printHomeScreen(){
        printLineSeparator();
        print("Hello! I'm Duke" + System.lineSeparator() +
                "What can I do for you?");
        printLineSeparator();
    }

    public static void printExitScreen() {
        print("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void printNumOfTasks() {
        int numOfTasks = tasksList.size();
        System.out.print("Now you have " + numOfTasks + " task");

        if(numOfTasks > 1) {
            System.out.print("s");
        }

        print(" in the list.");
    }
    public static void printLineSeparator(){
        print("---------------------------------------");
    }

    public static void printSuccessfulAddEntry(Task newTask) {
        printLineSeparator();

        System.out.print("Got it. I've added this task:" + System.lineSeparator() +
                "\t" + newTask + System.lineSeparator());
        printNumOfTasks();

        printLineSeparator();
    }

    public static void printSuccessfulRemoveEntry(Task deletedTask) {
        printLineSeparator();

        System.out.print("Got it. I've deleted this task:" + System.lineSeparator() +
                "\t" + deletedTask + System.lineSeparator());
        printNumOfTasks();

        printLineSeparator();
    }

    // READ WRITE//////////////////////////////////////////////////////////////////////////
    public static void loadTasksList() throws DukeException{
        try {
            File saveFile = new File(TASKS_LIST_SAVE_PATH); // create a File for the given file path
            Scanner saveFileScanner = new Scanner(saveFile); // create a Scanner using the File as the source

            while (saveFileScanner.hasNext()) {
                String curLine = saveFileScanner.nextLine();
                String[] taskDesc = curLine.split(" \\| ");

                if (taskDesc.length != SAVE_FILE_ELEMENTS_PER_ROW){
                    throw new DukeException("Incorrect formatting in save file");
                }
                Task newTask = createTask(taskDesc[0].toLowerCase(), taskDesc[2]);
                tasksList.add(newTask);
                newTask.isDone = Boolean.parseBoolean(taskDesc[1]);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to read save File");
        }

        printLineSeparator();
        print("Succesfully loaded Data!");
        printLineSeparator();
    }

    public static void saveTasksList() throws DukeException {
        File saveFile = new File(TASKS_LIST_SAVE_PATH);
        boolean fileExists = saveFile.exists();

        try {
            if (!fileExists) {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            }

        } catch (IOException exception) {
            throw new DukeException("Cannot create directories and files");
        }

        try {
            FileWriter saveFileWriter = new FileWriter(TASKS_LIST_SAVE_PATH);

            for (Task task : tasksList){
                String textLineToAdd = task.getClass().getSimpleName() + " | " +
                        task.isDone + " | " +
                        task.description + "\n";
                saveFileWriter.write(textLineToAdd);
            }

            saveFileWriter.close();
        } catch (IOException exception) {
            throw new DukeException("Cannot write to task list save file");
        }
    }

    // COMMAND HANDLERS //////////////////////////////////////////////////////////////////////////
    public static void handleByeCommand(){
        programIsRunning = false;
    }

    public static void handleListCommand() {
        int entryNum = 1;

        printLineSeparator();

        for(Task task : tasksList) {
            if(task == null) {
                break;
            } else {
                print(entryNum + "." + task);
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

                print("Nice! I've marked this task as done:" + System.lineSeparator() +
                        "\t" + task);
                printLineSeparator();

                return;
            }
        }
    }

    public static Task handleAddCommand(String command, String description) throws DukeException {
        Task newTask = createTask(command, description);

        if(newTask == null){
            throw new DukeException("Unable to create the new Task");
        }

        tasksList.add(newTask);
        printSuccessfulAddEntry(newTask);

        return newTask;
    }

    public static void handleException(DukeException exception){
        print("Exception!" + System.lineSeparator() +
                "\t" + exception.description);
    }


    // TASK CREATION /////////////////////////////////////////////////////////////////////////////
    public static Task createTask(String command, String description) throws DukeException {
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

        return newTask;
    }

    public static Todo createToDo(String description) throws DukeException{
        if (description.equals("")){
            throw new DukeException("The todo format is: todo <desc>");
        }

        return new Todo(description);
    }

    public static Deadline createDeadline(String description) throws DukeException{
        String[] taskDetails = description.split(" /by ");
        if(taskDetails.length < NUM_ARGS_DEADLINE){
            throw new DukeException("The deadline format is: deadline <desc> /by <time>");
        }

        return new Deadline(taskDetails[0], taskDetails[1]);
    }

    public static Event createEvent(String description) throws DukeException{
        String[] taskDetails = description.split(" /at ");
        if(taskDetails.length < NUM_ARGS_EVENT){
            throw new DukeException("The event format is: event <desc> /at <time>");
        }

        return new Event(taskDetails[0], taskDetails[1]);
    }
}