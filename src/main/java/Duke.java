import java.util.Scanner;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    private static boolean doneCommand(String command) {
        Pattern donePattern = Pattern.compile("^done( (.*))?");
        Matcher doneMatcher = donePattern.matcher(command);
        if (doneMatcher.find()) {
            try {
                String taskString = doneMatcher.group(2);
                if (taskString == null || taskString.isEmpty()) {
                    throw new IllegalArgumentException("Task string is empty");
                } else {
                    int taskIndex = Integer.parseInt(taskString);
                    Task task = tasks.get(taskIndex - 1);
                    task.markAsDone();
                    
                    PrintUtil.indentedPrintf("Marked task as done:\n  %s\n", task);
                }
            } catch (NumberFormatException e) {
                PrintUtil.indentedPrintln("Error: Task number must be an integer.");
            } catch (IllegalArgumentException e) {
                PrintUtil.indentedPrintln("Error: Task number cannot be empty.");
            } catch(IndexOutOfBoundsException e) {
                PrintUtil.indentedPrintln("Error: Task number must be within the range of current tasks.");
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
        } else if (command.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                PrintUtil.indentedPrintf("%d: %s\n", i+1, tasks.get(i));
            }
            return true;
        }
        
        if (doneCommand(command)) {
            return true;
        }
        
        Task newTask = new Task(command);
        tasks.add(newTask);
        PrintUtil.indentedPrintf("Added task:\n  %s\n", newTask);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", tasks.size());
        return true;
    }
    
    public static void main(String[] args) {
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
    }
}
