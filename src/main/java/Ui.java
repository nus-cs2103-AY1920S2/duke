/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    TaskList taskList = new TaskList();

    /**
     * Prints a welcome message.
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a 'delete' message after a task is deleted.
     * @param task The task to be deleted.
     */
    public void deleteMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Noted. I've removed this task:");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("        Now you have " + (taskList.numOfTasks() - 1) + " "
                + (taskList.numOfTasks() == 1? "task" : "tasks") + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints an 'add' message after a new task is added.
     * @param task The new task that is added.
     */
    public void addMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Got it. I've added this task:");
        System.out.print("            ");
        task.taskSummary();
        System.out.println("        Now you have " + taskList.numOfTasks() + " "
                + (taskList.numOfTasks() == 1? "task" : "tasks") + " in the list.");
        System.out.println("    ____________________________________________________________");

    }

    /**
     * Prints a 'done' message when a task is marked done.
     * @param task The task that is done.
     */
    public void doneMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Nice! I've marked this task as done: ");
        System.out.print("        ");
        task.taskSummary();
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message with nice frames.
     * @param string The message.
     */
    public void reply(String string) {
        System.out.println("    ____________________________________________________________");
        System.out.println("        " + string);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("        Here are the tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            System.out.print("        " + count + ".");
            (taskList.getTask(i)).taskSummary();
            count++;
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the error message.
     * @param msg The error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }
}
