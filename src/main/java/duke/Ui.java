package duke;

import java.util.Scanner;

public class Ui {
    private String output;

    Ui() {this.output = "";}

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public String showLine() {
        output = "_______";
        return output;
    }

    public String showLoadingError() {
        output = "Unable to load";
        return output;
    }

    public String showError(String err) {
        return err;
    }
     public String greet() {
        output = "Hey! I'm Duke! + \n" + "What can I help you with?";
        return output;
    }

    public String taskAdd(Task task, int tasks) {
        output =  "Got it. I've added this task: \n" + task + "\n" + "Now you have "
                + tasks  + "\n"+ " tasks in the list.\n";
        return output;
    }

    public String taskDone(Task task, int tasks) {
        output = "Nice! I've marked this task as done: \n" + task + "\n" + "Now you have "
                + tasks + "\n" + " tasks in the list.\n";
        return output;
    }

    public String taskDelete(Task task, int tasks) {
        output = "Noted, I've removed this task: \n" + task + "\n" + "Now you have "
                + tasks + "\n" + " tasks in the list.\n";
        return output;
    }

    public String saybye() {
        output = "Bye! Hope to see you again soon! \n";
        return output;
    }

    public String errormsg() {
        output = "Oops but that is not a valid command :-(";
        return output;
    }

}