/**
 * Ui class.
 * Responsible for interacting with the user by printing messages.
 *
 * @author Amos Cheong
 */
public class Ui {

    /**
     * A welcome message to greet user
     * @return String Welcome Message
     */
    public String welcomeMessage() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public String exitMessage() {
        return "Bye! Hope to see you soon.";
    }

    public void showErrorMessage(String error) {
        System.out.println(error);
    }
    /**
     * Informs user that the task has been added.
     *
     * @param listsize size of the tasklist.
     * @param mytask Task object. Able to take in objects of its subclasses.
     */
    public String addedMessage(int listsize, Task mytask) {
        return "Got it. I've added this task:" + "\n" +
                mytask + "\n" +
                "Now you have " + listsize + " tasks in the list.";
    }

    /**
     * Takes in a list of tasks and prints out all the tasks.
     *
     * @param list TaskList object.
     */
    public String listCommand(TaskList list) {
        String printMessage = "";
        // Print all the tasks added
        int counter = 1;
        for (Task currtask : list.getListOfTask()) {
            printMessage = printMessage + counter++ + "." + currtask + "\n";
        }
        return printMessage;
    }

    /**
     * Tell the user that the task that they inputted is done.
     *
     * @param doneTask The Task object that is set as done.
     */
    public String finishMessage(Task doneTask) {
        return "Nice! I've marked this as done:" + "\n" +
                doneTask;
    }


    /**
     * Tell the user that the task that they want to delete is already deleted.
     *
     * @param num remaining number of tasks in the list.
     * @param deletedTask The Task object to be deleted.
     */
    public String deletedMessage(int num, Task deletedTask) {
        return "Noted. I've removed this task:" + "\n" +
                deletedTask + "\n" +
                "Now you have " + num + " tasks in the list.";
    }

    /**
     * Returns a message after a find Command.
     *
     * @return String Message to user, showing them a list of tasks
     */
    public String findMessage() {
        return "Here are the matching tasks in your list:";
    }
}
