import tasks.Task;

import java.util.ArrayList;

public class Ui {
    private String bar = "    **************************************************************\n";

    public Ui (){
    }

    public void printFormattedOutput(String output) {
        System.out.println(bar + "    " + output + "\n" + bar);
    }

    public void printList(ArrayList<Task> list) {
        System.out.print(bar);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        System.out.println(bar);
    }

    public void printNewTask(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

    public void printDone(Task task) {
        System.out.print(bar);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println(bar);
    }

    public void printDelete(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

}
