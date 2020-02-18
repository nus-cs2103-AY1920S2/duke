package duke.common.message;

public class Message {

    public static final String DELETE_MESSAGE = 
            "I wish to remove you from my life too.";

    public static final String DIVIDER = "----------";

    public static final String EXIT_MESSAGE = "Bye, see you never!";

    public static final String EXPENSE_ADDED = "Spend as much as you want. "
            + "Your expenses don't add up to much anyway.";

    public static final String EXPENSE_MESSAGE = "Tracking expenses are for nerds.";

    public static final String FIND_MESSAGE = "Who am I? Your secretary?";

    public static final String GREET = "I'm busy. What do you want?";

    public static final String MARK_DONE = 
            "Tsk! I've marked this as done, you owe me.";

    public static final String TASK_ADDED = "I have increased your workload.";

    public static final String TASK_MESSAGE = 
            "You have lot of work to do! Chop Chop!";

    /**
     * Generates a message to display the number of tasks given the number.
     * @param number The number of tasks in the list.
     * @return A message to tell you the number of tasks in the list.
     */
    public static String showNumberOfTasks(int number) {
        return "You have " + number + " tasks in your list. :(";
    }

    /**
     * Obtain the message for number of tasks found given the number.
     * @param number The number of tasks that was found.
     * @return The messsage for number of tasks found.
     */
    public static String showNumberOfTasksFound(int number) {
        return "Found " + number + " tasks that matches.";
    }
}