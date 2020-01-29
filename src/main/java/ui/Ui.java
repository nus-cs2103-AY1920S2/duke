package ui;

import tasks.Task;

import java.util.ArrayList;

/**
 * Provides functions for easier formatted outputs.
 */
public class Ui {
    private String bar = "    **************************************************************\n";

    public Ui (){
    }

    /**
     * Formats any output between two bars.
     *
     * @param output Formatted output.
     */
    public void printFormattedOutput(String output) {
        System.out.println(bar + "    " + output + "\n" + bar);
    }

    /**
     * Prints task list with indexing.
     * @param list Tasklist to be printed.
     */
    public void printList(ArrayList<Task> list) {
        System.out.print(bar);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i));
        }
        System.out.println(bar);
    }

    /**
     * Prints success of adding a new task and the number of tasks in the task list.
     *
     * @param task New task that is added.
     * @param sizeOfList Size of task list.
     */
    public void printNewTask(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

    /**
     * Prints success of marking a task as done.
     *
     * @param task Task to be marked as done.
     */
    public void printDone(Task task) {
        System.out.print(bar);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println(bar);
    }

    /**
     * Prints success of deleting a task, and the number of tasks left in the task list.
     *
     * @param task Task to be deleted.
     * @param sizeOfList Size of task list.
     */
    public void printDelete(Task task, int sizeOfList) {
        System.out.print(bar);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + sizeOfList + " tasks on the list.");
        System.out.println(bar);
    }

}
