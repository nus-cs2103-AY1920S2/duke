/**
 * Ui class.
 * Responsible for interacting with the user by printing messages.
 *
 * @author Amos Cheong
 */
public class Ui {

    /**
     * A horizontal line
     */
    public void HorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets user.
     */
    public void greetUser(){
        HorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        HorizontalLine();
    }

    /**
     * Exits the code.
     */
    public void exit(){
        HorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        HorizontalLine();
    }

    /**
     * Print error message to the user.
     * @param errormessage Message to inform the user of the error.
     */
    public void showErrorMessage(String errormessage) {
        HorizontalLine();
        System.out.println(errormessage);
        HorizontalLine();
    }

    /**
     * Informs user that the task has been added.
     * @param listsize size of the tasklist.
     * @param mytask Task object. Able to take in objects of its subclasses.
     */
    public void addMessage(int listsize, Task mytask) {
        HorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(mytask);
        System.out.println("Now you have " + listsize + " tasks in the list.");
        HorizontalLine();
    }

    /**
     * Takes in a list of tasks and prints out all the tasks.
     * @param list TaskList object.
     */
    public void listAllTasks(TaskList list) {
        // Print all the tasks added
        int counter = 1;
        for (Task currtask : list.getListOfTask()) {
            System.out.println(counter++ + "." + currtask);
        }

        HorizontalLine();
    }

    /**
     * Tell the user that the task that they inputted is done.
     * @param doneTask The Task object that is set as done.
     */
    public void doneMessage(Task doneTask) {
        HorizontalLine();
        System.out.println("Nice! I've marked this as done:");
        System.out.println(doneTask);
        HorizontalLine();
    }

    /**
     * Tell the user that the task that they want to delete is already deleted.
     * @param num remaining number of tasks in the list.
     * @param deletedTask The Task object to be deleted.
     */
    public void deletedTaskMessage(int num, Task deletedTask) {
        HorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + num + " tasks in the list.");
        HorizontalLine();
    }

    /**
     * Show user the list that matches to the input keyword
     */
    public void foundMessage() {
        HorizontalLine();
        System.out.println("Here are the matching tasks in your list:");
    }
}
