import java.util.Scanner;

public class Ui {

    private TaskList tasks;

    /**
     * Constructor for Ui class to run UI events
      * @param tasks is the tasks that this current instance of Duke has
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }


    /**
     * Prints all the tasks in the current list
     */
    public void printList() {
        if (!tasks.isEmpty()) {
            System.out.println("Here are the tasks in your list");
        } else {
            System.out.println("There are no tasks in your list");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + "." + tasks.get(i));
        }
    }

    /**
     * Prints the basic intro message when user opens up program
     */
    public void printIntro() {
        System.out.println("Hello I am from North Korea\n" + "What can I do for you?");
        System.out.println("____________________________________\n");
    }

    /**
     * Prints the basic goodbye message when user has no more input
     */
    public void printGoodbye() {
        System.out.println("GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA");
    }




}
