import java.util.Scanner;
import java.io.PrintStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        PrintUtil.indentedPrintln("Hello from\n" + logo);
    }
    
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String savePath = "data/duke.txt";

    @SuppressWarnings("unchecked")
    private static void loadTaskState() throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream f = new FileInputStream(savePath);
        ObjectInputStream o = new ObjectInputStream(f);
        tasks = (ArrayList<Task>)o.readObject();
        o.close();
    }

    private static void saveTaskState() throws IOException {
        //recursively create directories to save path if they don't exist
        Files.createDirectories(Paths.get(savePath).getParent());
        
        //save to file
        FileOutputStream f = new FileOutputStream(savePath);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(tasks);
        o.close();
    }
    
    private static boolean doneCommand(String command) throws DukeException {
        Pattern donePattern = Pattern.compile("^done( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            try {
                String taskString = doneMatcher.group(2);
                if (taskString == null || taskString.isEmpty()) {
                    throw new DukeException("Task number cannot be empty");
                } else {
                    int taskIndex = Integer.parseInt(taskString);
                    Task task = tasks.get(taskIndex - 1);
                    task.markAsDone();
                    
                    PrintUtil.indentedPrintf("Marked task as done:\n  %s\n", task);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Task number must be an integer");
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("Task number must be within the range of current tasks");
            }
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean deleteCommand(String command) throws DukeException {
        Pattern donePattern = Pattern.compile("^delete( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            try {
                String taskString = doneMatcher.group(2);
                if (taskString == null || taskString.isEmpty()) {
                    throw new DukeException("Task number cannot be empty");
                } else {
                    int taskIndex = Integer.parseInt(taskString);
                    removeTask(taskIndex - 1);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Task number must be an integer");
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("Task number must be within the range of current tasks");
            }
            return true;
        } else {
            return false;
        }
    }
    
    private static void addTask(Task newTask) {
        tasks.add(newTask);
        PrintUtil.indentedPrintf("Added task:\n  %s\n", newTask);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", tasks.size());
    }
    
    private static void removeTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        
        PrintUtil.indentedPrintf("Removed task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", tasks.size());
    }
    
    private static boolean todoCommand(String command) throws DukeException {
        Pattern donePattern = Pattern.compile("^todo( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            String taskString = doneMatcher.group(2);
            if (taskString == null || taskString.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            } else {
                addTask(new Todo(taskString));
            }
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean deadlineCommand(String command) throws DukeException {
        Pattern deadlinePattern = Pattern.compile("^deadline ?((.*?)( /by ?(.*))?)$");
        Matcher deadlineMatcher = deadlinePattern.matcher(command);
        if (deadlineMatcher.find()) {
            String taskDescription = deadlineMatcher.group(2);
            String deadline = deadlineMatcher.group(4);
            if (taskDescription == null || taskDescription.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            } else if (deadline == null || deadline.isEmpty()) {
                throw new DukeException("Deadline cannot be empty");
            } else {
                addTask(new Deadline(taskDescription, deadline));
            }
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean eventCommand(String command) throws DukeException {
        Pattern eventPattern = Pattern.compile("^event ?((.*?)( /at ?(.*))?)$");
        Matcher eventMatcher = eventPattern.matcher(command);
        if (eventMatcher.find()) {
            String taskDescription = eventMatcher.group(2);
            String eventTime = eventMatcher.group(4);
            if (taskDescription == null || taskDescription.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            } else if (eventTime == null || eventTime.isEmpty()) {
                throw new DukeException("Event time cannot be empty");
            } else {
                addTask(new Event(taskDescription, eventTime));
            }
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean runCommand(String command) {
        if (command.equals("bye")) {
            PrintUtil.indentedPrintln("Goodbye");
            return false;
        }
        
        try {
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    PrintUtil.indentedPrintf("%d.%s\n", i+1, tasks.get(i));
                }
            } else if ( doneCommand(command)
                     || deleteCommand(command)
                     || todoCommand(command)
                     || deadlineCommand(command)
                     || eventCommand(command)) {
            } else {
                PrintUtil.indentedPrintf("Error: Unknown command: %s\n", command);
            }
        } catch (DukeException e) {
            PrintUtil.indentedPrintf("Error: %s\n",e.getMessage());
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
        try {
            loadTaskState();
        } catch (FileNotFoundException e) {
            PrintUtil.printHeaderLine();
            PrintUtil.indentedPrintln("Error: Failed to load task list");
            PrintUtil.indentedPrintf("       Duke will create a new task list file at %s\n", savePath);
            PrintUtil.printHeaderLine();
        } catch(ClassNotFoundException e) {
            PrintUtil.printHeaderLine();
            PrintUtil.indentedPrintln("Error: Malformed task list file");
            PrintUtil.printHeaderLine();
        }
        PrintUtil.printHeaderLine();
        greet();
        PrintUtil.printHeaderLine();
        
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String command = sc.nextLine();
            
            PrintUtil.printHeaderLine();
            running = runCommand(command);
            PrintUtil.printHeaderLine();
        }
        try {
            saveTaskState();
        } catch (FileNotFoundException e) {
            PrintUtil.printHeaderLine();
            PrintUtil.indentedPrintln("Error: Failed to save task list.");
            PrintUtil.printHeaderLine();
        }
    }
}
