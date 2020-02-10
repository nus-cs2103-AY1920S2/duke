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
    public String printList() {
        StringBuilder output = new StringBuilder();
        if (!tasks.isEmpty()) {
            output.append("Here are the tasks in your list" + "\n");
        } else {
            return "There are no tasks in your list" + "\n";
        }
        for (int i = 0; i < tasks.size(); i++) {
            String currTask = i+1 + "." + tasks.get(i);
            output.append(System.lineSeparator());
            output.append(currTask);
        }
        return output.toString();
    }


    /**
     * Prints the basic goodbye message when user has no more input
     */
    public void printGoodbye() {
        System.out.println("GOODBYE!! MUAHAHHAHAHAHHAAHAHHAHAHA");
    }




}
