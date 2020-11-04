import java.util.Scanner;

public class Ui {

    private Scanner sc;

    /**
     * Class constructor method.
     */
    public Ui () {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads one line of user input.
     */
    public String scanInput() {
        String user_input = sc.nextLine();
        return user_input;
    }

    // PRINT STATEMENTS: ===========================================================================

    /**
     * Prints the greeting statement for Duke.
     */
    public String printGreeting() {
        return "Hello! I am a Pyramid 480 Medical class Operator. Nice to see you!";
    }

    /**
     * Prints the goodbye statement for Duke.
     */
    public String printGoodbye() {
        return "Don't forget to fill out a patient questionnaire. Your feedback will be invaluable!";
    }

    /**
     * Prints the message for when the list is empty.
     */
    public String printEmptyListMessage() {
        return "Whoops! You have 0 neuromods. Neuromods can be obtained from defeating Typhon or by fabrication using the Neuromod blueprint.\n";
    }

    /**
     * Prints the error message for when the file cannot be found.
     */
    public void showLoadingError() {
        System.out.println("Whoops! You're not on my onboard memory!");
    }

    /**
     * Prints out all the Tasks currently maintained in your TaskList.
     * @param tasks The TaskList of Tasks which you want to print.
     */
    public String printList(TaskList tasks) {
        String lst = "Neuromods available: \n";
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            String counter = Integer.toString(i + 1);
            String completion_symbol = tasks.get(i).getCompletionStatusAsString();
            String task_type = tasks.get(i).getTaskType();
            String task_description = tasks.get(i).getDescription();
            String line = counter + ". " + completion_symbol + " | [" + task_type + "] | " + task_description + "\n";
            if (task_type.equals("E") || task_type.equals("D")) {
                String task_date = tasks.get(i).getDate();
                line = counter + ". " + completion_symbol + " | [" + task_type + "] | " + task_description + " | " + task_date + "\n";
            }

            lst = lst + line;
        }
        return lst;
    }

    // These have got to do with the adding/deleting of tasks from the list ========================================

    /**
     * Prints when a Task has been added to the TaskList.
     */
    public String printAdd() {
        return "Scan complete, new psionic aptitude available.\n";
    }

    /**
     * Prints when a Task is deleted from the TaskList.
     */
    public String printDelete() {
        return "Neuromod installed. You have depleted 1 neuromod from your inventory:\n";
    }

    /**
     * Prints the number of Tasks in your current TaskList.
     * @param tasks Tasks in your TaskList.
     */
    public String printNumTasksInList(TaskList tasks) {
        return "You currently have " + tasks.getTaskListSize() + " neuromods in your inventory.\n";
    }

    /**
     * Prints the exception message.
     * @param exception Exception message.
     */
    public String printExceptionMessage(DukeException exception) {
        return exception.getMessage() + "\n";
    }

    /**
     * Prints the message when a specified task has been marked as completed.
     * @param task The Task object which has been completed.
     */
    public String printMarkedAsDone(Task task) {
        String lst = "Mission complete: \n";

        String completion_symbol = task.getCompletionStatusAsString();
        String task_type = task.getTaskType();
        String task_description = task.getDescription();
        String line = completion_symbol + " | [" + task_type + "] | " + task_description + "\n";
        if (task_type.equals("E") || task_type.equals("D")) {
            String task_date = task.getDate();
            line = completion_symbol + " | [" + task_type + "] | " + task_description + " | " + task_date + "\n";
        }
        return lst + line;
    }

}
