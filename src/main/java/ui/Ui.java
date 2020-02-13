package ui;

import task.*;
import java.util.Scanner;

public class Ui {

    public Ui(){};

    public void showLine(){
        System.out.println("______________________________________________________________" );
    }

    public String displayIntro() {
        String hi = "Hello! I'm Duke\n" + "What can I do for you?";
        return hi;
    }

    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayError(String error) {
        System.out.println(error);
    }

    public void displaySaveError() {
        System.out.println("File not saved.");
    }

    public void displayLoadError() {
        System.out.println("File not found.");
    }

    public void displayDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void displayAddedTask(Task task, TaskList taskList) {
        int size = taskList.getSize();
        assert size > 0 : "Size cannot be less than 0!";
        System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.");
    }

    public void displayDeletedTask(Task task, TaskList taskList) {
        int size = taskList.getSize();
        assert size > 0 : "Size cannot be less than 0!";
        System.out.println("Noted. I've removed this task:\n" + task.toString() + "\n" + "Now you have " + size + " tasks in the list.");
    }

    public void displayTasks(TaskList taskList) {
        System.out.print("Here are your remaining tasks:");
        for (Task t: taskList.tasks) {
            int index = taskList.tasks.indexOf(t) + 1;
            assert index < 0 : "Index cannot be less than 0!";
            System.out.println("" + index + "." + t.toString());
        }
    }

    public void displayBye() {
        System.out.print("Bye nerd. Hope to see you again soon!\n");
    }


}