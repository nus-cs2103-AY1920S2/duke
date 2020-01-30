package ip;

import java.util.Scanner;
import ip.task.Task;
import ip.task.TaskList;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private final String LINE = "\t__________________________________________________________";
    private void printResponse(String... strs){
        System.out.println(LINE);
        for(String s: strs){
            System.out.println("\t" + s);
        }
        System.out.println(LINE);
    }
    public String getInput(){
        return sc.nextLine().trim();
    }
    public void displayError(String msg) {
        printResponse("☹ OOPS!!! ", msg);
    }
    public void initialGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    public void displayTaskAdded(Task t, int numTasks){
        printResponse("Got it, I've added this task:", t.toString(), "Now you have " + numTasks + " task(s) in the list.");
    }
    public void displayAllTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            printResponse("There are no tasks in the list! Please add some :)");
            return;
        }
        String[] strList = new String[tasks.size()];
        for (int i=0; i<tasks.size(); i++){
            strList[i] = "\t" + (i+1) + ": " + tasks.get(i).toString();
        }
        printResponse(strList);
    }
    public void displayGoodbye(){
        printResponse("Goodbye! Hope to hear from you soon :)");
    }
    public void displayTaskMarkedDone(Task t) {
        printResponse("Awesome! I've marked this task as done: ", t.toString());
    }
    public void displayTaskDeleted(Task t, int numTasks) {
        printResponse("Noted, I've removed this task: ", t.toString(), "Now you have " + numTasks + " task(s) in the list.");
    }
}