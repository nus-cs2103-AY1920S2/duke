public class Ui {

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
     * Print error message to the user
     * @param errormessage Message to inform the user of the error
     */
    public void showErrorMessage(String errormessage) {
        HorizontalLine();
        System.out.println(errormessage);
        HorizontalLine();
    }

    public void addMessage(int listsize, Task mytask) {
        HorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(mytask);
        System.out.println("Now you have " + listsize + " tasks in the list.");
        HorizontalLine();
    }

    public void listAllTasks(TaskList list) {
        HorizontalLine();

        // Print all the tasks added
        int counter = 1;
        for (Task currtask : list.getListOfTask()) {
            System.out.println(counter++ + "." + currtask);
        }

        HorizontalLine();
    }

    public void doneMessage(Task doneTask) {
        HorizontalLine();
        System.out.println("Nice! I've marked this as done:");
        System.out.println(doneTask);
        HorizontalLine();
    }

    public void deletedTaskMessage(int num, Task deletedTask) {
        HorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + num + " tasks in the list.");
        HorizontalLine();
    }
}
