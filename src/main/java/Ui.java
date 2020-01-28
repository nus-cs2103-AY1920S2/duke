import java.util.Scanner;

public class Ui {
//    private Scanner input;
    
    public Ui() {
//        this.input = new Scanner(System.in);
    }
    
    public void showLoadingError() {
        System.out.println("Error: Unable to load saved tasks from file.");
        System.out.println("New data file will be created.\n");
    }
    
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi, I'm Duke!");
        System.out.println("What can I do for you?\n");
    }
    
    public String getInput() {
        ////
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        return sc.nextLine();
    }
    
    public void showFarewell() {
        System.out.println("\tHave a nice day!\n");
        System.out.println("Exiting...");
    }    
    
    public void showSetAsDone(TaskList taskList, Task task) {
        System.out.println("\tNoted. I have marked this task as done:");
        System.out.format("\t\t%s%n%n", task);
    }
    
    public void showAddTask(TaskList taskList, Task task) {
        System.out.println("\tAlright! The following task has been added:");
        System.out.format("\t\t%s%n", task);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks");
    }
    
    public void showDeleteTask(TaskList taskList, Task task) {
        System.out.println("\tNoted. I have removed this task:");
        System.out.format("\t\t%s%n", task);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks");
    }
}
