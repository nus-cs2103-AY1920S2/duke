import java.util.Scanner;

public class Ui {
    Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommandString() {
        return sc.nextLine();
    }
    
    public void showLine() {
        PrintUtil.printHeaderLine();
    }
    
    public void showNumberedEntry(int index, Task task) {
        PrintUtil.indentedPrintf("%d.%s\n", index, task);
    }
    
    public void showError(DukeException e) {
        PrintUtil.indentedPrintf("Error: %s\n",e.getMessage());
    }
    
    public void showSaveNotFoundMessage(String savePath) {
        PrintUtil.printHeaderLine();
        PrintUtil.indentedPrintln("Error: Task list not found");
        PrintUtil.indentedPrintf("       Duke will create a new task list file at %s\n", savePath);
        PrintUtil.printHeaderLine();
    }
    
    public void greet() {
        PrintUtil.printHeaderLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        PrintUtil.indentedPrintln("Hello from\n" + logo);
        PrintUtil.printHeaderLine();
    }
    
    public void bye() {
        PrintUtil.indentedPrintln("Goodbye");
    }
    
    public void showAddTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Added task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    public void showRemoveTaskMessage(Task task, int remainingCount) {
        PrintUtil.indentedPrintf("Removed task:\n  %s\n", task);
        PrintUtil.indentedPrintf("Now you have %d task(s).\n", remainingCount);
    }
    
    public void showDoneTaskMessage(Task task) {
        PrintUtil.indentedPrintf("Marked task as done:\n  %s\n", task);
    }
    
    public void showUnknownCommandMessage(String command) {
        PrintUtil.indentedPrintf("Error: Unknown command: %s\n", command);
    }
}
