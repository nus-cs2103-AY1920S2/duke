import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents an object that handles user input and interaction through prompts such as welcome and farewell messages,
 * as well as indicating success of task addition, deletion and flagging to the user
 * when an input of the wrong format is entered.
 */
public class Ui {
    private Scanner sc;
    private final String BREAKLINE = "____________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() {
        // Read user input
        return this.sc.nextLine();
    }

    /**
     * Prints and returns a greeting message to the user.
     * @return String greeting message to the user.
     */
    public String sayHello() {
        // Get welcome message
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm Duke!\n");
        sb.append("What can I do for you?\n");
        return sb.toString();
    }

    /**
     * Prints a farewell message to the user.
     */
    public String sayBye() {
        // Print goodbye message
        StringBuilder sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!\n");
        return sb.toString();
    }


    /**
     * Alerts user of a successful task addition to their task list.
     * @param task Task object that was added.
     * @param taskListSize Size of the user's task list after addition.
     */
    public String taskAddSuccess(Task task, int taskListSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task + "\n");
        sb.append("Now you have " + taskListSize + " tasks in the list.");
        return sb.toString();
    }

    /**
     * Alerts the user of a successful task deletion from their task list.
     * @param task Task object that was deleted.
     * @param taskListSize Size of the user's task list after deletion.
     */
    public String taskDeleteSuccess(Task task, int taskListSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("Understood. I've deleted this task:\n");
        sb.append(" " + task + "\n");
        sb.append("Now you have " + taskListSize + " tasks in the list.");
        return sb.toString();
    }

    /**
     * Alerts the user of successfully marking a task as done,
     * and showing the user the task that was marked.
     * @param task Task object that was marked as done.
     */
    public String markDoneSuccess(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! I've marked this task as done:\n");
        sb.append(" " + task + "\n");
        return sb.toString();
    }

    /**
     * Alerts the user of an incorrect command input.
     */
    public String flagWrongCommand() {
        StringBuilder sb = new StringBuilder();
        String sorryStr = "Sorry! You've entered an invalid command, please try again!\n";
        String helpStr = "List of commands: \n" + " todo\n" + " event\n"
                + " deadline\n" + " list\n";
        sb.append(sorryStr);
        sb.append(helpStr);
        return sb.toString();
    }

    public String findTaskSuccess(ArrayList<Task> foundList) {
        StringBuilder sb = new StringBuilder();
        // Check if the foundList is empty
        if (foundList.size() > 0) {
            // If not 0, then print all the tasks that are found
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundList.size(); i++) {
                sb.append("\t " + (i + 1) + ". " + foundList.get(i).toString());
            }
        } else {
            // Found list is empty, tell the user that cannot find the matching task
            sb.append("Sorry! No matching tasks found!");
        }
        return sb.toString();
    }

    public String flagWrongDateFormat() {
        StringBuilder sb = new StringBuilder();
        String sorryStr = "Sorry! You have entered an invalid date format!\n";
        String correctDateFormatStr = "Please enter your date in the yyyy-MM-d format!";
        sb.append(sorryStr);
        sb.append(correctDateFormatStr);
        return sb.toString();
    }

    public String printTaskList(ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() == 0) {
            sb.append("Your task list is empty!\n");
        } else {
            sb.append("Here are the tasks in your list!\n");
            for(int i = 0; i < taskList.size(); i++) {
                String taskNum = (i + 1) + ".";
                sb.append(taskNum);
                Task currTask = taskList.get(i);
                sb.append(currTask.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getBreakLine() {
        return this.BREAKLINE;
    }
}
