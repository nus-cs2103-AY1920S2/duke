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
    public static final int NUM_ARGS_DEADLINE = 2;
    public static final int NUM_ARGS_EVENT = 2;

    // Error Messages
    public static final String BAD_COMMAND = "Duke does not understand that command";
    public static final String CANNOT_READ_SAVE_FILE = "Cannot to read save File";
    public static final String CANNOT_CREATE_DIRECTORIES_AND_FILES = "Cannot create directories and files";
    public static final String CANNOT_WRITE_TO_TASK_LIST_SAVE_FILE = "Cannot write to task list save file";
    public static final String CANNOT_CREATE_TASK = "Unable to create the new Task";
    public static final String TASK_NOT_FOUND = "Task not found";
    public static final String DONE_COMMAND_FORMAT = "The done command format is: done <taskDescription>";
    public static final String TODO_FORMAT = "The todo format is: todo <desc>";
    public static final String EVENT_FORMAT = "The event format is: event <desc> /at <time>";
    public static final String DEADLINE_FORMAT = "The deadline format is: deadline <desc> /by <time>";

    public static ArrayList<Task> tasksList = new ArrayList<>();
    public static boolean programIsRunning = true;

    // MAIN LOOP //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        enter();
        mainLoop();
        exit();
    }

    public static void enter() {
        printHomeScreen();

        // Load save file
        try {
            loadTasksList();
        } catch (DukeException exception){
            handleException(exception);
        }
    }

    public static void mainLoop() {
        while(programIsRunning){
            String input = getInput();
            String[] separatedInput = separateCommandAndArguments(input);

            String command = separatedInput[0];
            String arguments = null;
            if (separatedInput.length > 1){
                arguments = separatedInput[1];
            }

            try {
                switch(command) {
                case "bye":
                    handleByeCommand();
                    break;
                case "list":
                    handleListCommand();
                    break;
                case "done":
                    handleDoneCommand(arguments);
                    break;
                case "todo": // Fallthrough
                case "deadline": // Fallthrough
                case "event":
                    handleAddCommand(command, arguments);
                    break;
                case "delete":
                    handleDeleteCommand(arguments);
                    break;
                default:
                    throw new DukeException(BAD_COMMAND);
                }
            } catch(DukeException exception) {
                handleException(exception);
            }
        }
    }

    public static void exit() {
        printExitScreen();
    }


    // INPUT PARSING ////// //////////////////////////////////////////////////////////////////////////
    public static String getInput(){
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        input = input.trim();

        return input;
    }

    public static String[] separateCommandAndArguments(String input) {
        return input.split(" ", 2);
    }

    // PRINT HELPERS //////////////////////////////////////////////////////////////////////////
    public static void print(String text){
        System.out.println(text);
    }

    public static void printLineSeparator(){
        print("---------------------------------------");
    }

    public static void printNumOfTasks() {
        int numOfTasks = tasksList.size();
        print("Now you have " + numOfTasks + " task(s)in the list.");
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

    public static void printSuccessfulAddEntry(Task newTask) {
        printLineSeparator();

        print("Got it. I've added this task:" + System.lineSeparator() +
                "\t" + newTask);
        printNumOfTasks();

        printLineSeparator();
    }

    public static void printSuccessfulRemoveEntry(Task deletedTask) {
        printLineSeparator();

        print("Got it. I've deleted this task:" + System.lineSeparator() +
                "\t" + deletedTask);
        printNumOfTasks();

        printLineSeparator();
    }

    public static void printList() {
        int entryNum = 1;

        printLineSeparator();

        for(Task task : tasksList) {
            if(task == null) {
                break;
            }

            print(entryNum + "." + task);
            entryNum++;
        }

        printLineSeparator();
    }

    // READ WRITE//////////////////////////////////////////////////////////////////////////
    public static void loadTasksList() throws DukeException{
        try {
            File saveFile = new File(TASKS_LIST_SAVE_PATH);
            Scanner saveFileScanner = new Scanner(saveFile);

            while (saveFileScanner.hasNext()) {
                String curLine = saveFileScanner.nextLine();
                String[] taskDesc = curLine.split("\\|");

                String command = taskDesc[0];
                String taskArgs = "";
                switch (command) {
                case "todo":
                    taskArgs = taskDesc[2];
                    break;
                case "deadline":
                    taskArgs = taskDesc[2] + " /by " + taskDesc[3];
                    break;
                case "event":
                    taskArgs = taskDesc[2] + " /at " + taskDesc[3];
                    break;
                }

                Task newTask = createTask(taskDesc[0], taskArgs);
                newTask.isDone = Boolean.parseBoolean(taskDesc[1]);
                tasksList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(CANNOT_READ_SAVE_FILE);
        }

        printLineSeparator();
        print("Successfully loaded Data!");
        printLineSeparator();
    }

    public static void saveTasksList() throws DukeException {
        File saveFile = new File(TASKS_LIST_SAVE_PATH);
        createDirectories(saveFile);
        createSaveFile();
    }

    public static void createDirectories(File saveFile) throws DukeException {
        boolean fileExists = saveFile.exists();

        if (!fileExists) {
            try {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            } catch (IOException exception) {
                throw new DukeException(CANNOT_CREATE_DIRECTORIES_AND_FILES);
            }
        }
    }

    public static void createSaveFile() throws DukeException {
        try {
            FileWriter saveFileWriter = new FileWriter(TASKS_LIST_SAVE_PATH);

            for (Task task : tasksList) {
                if (task instanceof  Todo){
                    storeLine(saveFileWriter, "todo", Boolean.toString(task.isDone), task.description);
                }else if (task instanceof Deadline) {
                    storeLine(saveFileWriter, "deadline", Boolean.toString(task.isDone), task.description, ((Deadline) task).dueDate);
                } else if (task instanceof Event) {
                    storeLine(saveFileWriter, "event", Boolean.toString(task.isDone), task.description, ((Event) task).timeFrame);
                }
            }

            saveFileWriter.close();
        } catch (IOException exception) {
            throw new DukeException(CANNOT_WRITE_TO_TASK_LIST_SAVE_FILE);
        }
    }

    public static void storeLine(FileWriter saveFileWriter, String ... elements) throws IOException {
        StringBuilder lineToStore = new StringBuilder();
        for (String element : elements) {
            lineToStore.append(element).append("|");
        }
        lineToStore.append("\n");
        saveFileWriter.write(lineToStore.toString());
    }

    // COMMAND HANDLERS //////////////////////////////////////////////////////////////////////////
    public static void handleByeCommand(){
        programIsRunning = false;
    }

    public static void handleListCommand() {
        printList();
    }

    public static void handleDoneCommand(String description) throws DukeException{
        if(description == null){
            throw new DukeException(DONE_COMMAND_FORMAT);
        }

        // Looks for a task with matching the description task and marks it as done
        for(Task task : tasksList) {
           if(task.description.equals(description) && !task.isDone ) {
                task.isDone = true;

                print("Nice! I've marked this task as done:" + System.lineSeparator() +
                        "\t" + task);
                printLineSeparator();

                saveTasksList();
                return;
            }
        }

        throw new DukeException(TASK_NOT_FOUND);
    }

    public static void handleAddCommand(String command, String description) throws DukeException {
        Task newTask = createTask(command, description);

        if(newTask == null){
            throw new DukeException(CANNOT_CREATE_TASK);
        }

        tasksList.add(newTask);
        printSuccessfulAddEntry(newTask);

        saveTasksList();
    }

    public static void handleDeleteCommand(String description) throws DukeException{
        for (Task task: tasksList){
            if (task.description.equals(description)){
                tasksList.remove(task);
                printSuccessfulRemoveEntry(task);

                saveTasksList();
                return;
            }
        }

        throw new DukeException(TASK_NOT_FOUND);
    }

    public static void handleException(DukeException exception){
        print("Exception!" + System.lineSeparator() +
                "\t" + exception.description);
    }

    // TASK CREATION /////////////////////////////////////////////////////////////////////////////
    public static Task createTask(String command, String description) throws DukeException {
        Task newTask = null;

        switch (command) {
        case "todo":
            newTask = createToDo(description);
            break;
        case "deadline":
            newTask = createDeadline(description);
            break;
        case "event":
            newTask = createEvent(description);
            break;
        }

        return newTask;
    }

    public static Todo createToDo(String description) throws DukeException{
        if (description == null){
            throw new DukeException(TODO_FORMAT);
        }

        return new Todo(description);
    }

    public static Deadline createDeadline(String description) throws DukeException{
        String[] taskDetails = description.split(" /by ");
        if(taskDetails.length < NUM_ARGS_DEADLINE){
            throw new DukeException(DEADLINE_FORMAT);
        }

        return new Deadline(taskDetails[0], taskDetails[1]);
    }

    public static Event createEvent(String description) throws DukeException{
        String[] taskDetails = description.split(" /at ");
        if(taskDetails.length < NUM_ARGS_EVENT){
            throw new DukeException(EVENT_FORMAT);
        }

        return new Event(taskDetails[0], taskDetails[1]);
    }
}