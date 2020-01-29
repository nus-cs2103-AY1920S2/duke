import java.util.Scanner;

public class Ui {

    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

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

    public void printIntro() {
        System.out.println("Hello I am from North Korea\n" + "What can I do for you?");
        System.out.println("____________________________________\n");
    }

    public void printGoodbye() {
        System.out.println("GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA");
    }

    public void printUpdateMessage(Task task) {
        System.out.println("Got it, I've added the following task:\n" + "  " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }



}
