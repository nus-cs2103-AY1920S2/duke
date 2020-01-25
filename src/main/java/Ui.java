import java.util.Scanner;

public class Ui {
    public Ui(){}

    public void showWelcome(){
        String line = "    ____________________________________________________________" + "\n";
        String fiveSpaces = "      ";
        System.out.println(line + fiveSpaces + "Hello! I'm Duke\n" + fiveSpaces + "Whatcha wanna do?\n" + line);
    }

    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine(){
        System.out.println("    ____________________________________________________________" );
    }

    public void showLoadingError(){
        System.out.println("File not found. Created a new data directory and created a duke.txt inside. Data will be stored here.");
    }

    public void showSavingError() {System.out.println("File not saved");}

    public void showError(String error){
        System.out.println(error);
    }

    public void taskAdded(Task task, TaskList taskList){
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + Integer.toString(taskList.tasks.size()) + " tasks in the list.");
    }

    public void taskDeleted(Task task, TaskList taskList){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + Integer.toString(taskList.tasks.size()) + " tasks in the list.");
    }

    public void taskDone(Task task){
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
    }

    public void printList(TaskList taskList){
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.tasks.size(); i++) {
            System.out.println("      " + Integer.toString(i + 1) + "." + taskList.getTask(i));
        }
    }

    public void showByeMsg(){
        System.out.print("     See ya later alligator!\n");
    }
}
