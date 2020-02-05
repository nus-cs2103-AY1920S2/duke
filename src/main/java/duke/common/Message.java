package duke.common;

public class Message {

    public static final String DELETE_MESSAGE = 
            "Removed and never to be seen again.";

    public static final String DIVIDER = "----------";

    public static final String EXIT_MESSAGE = "Bye, see you never!";

    public static final String FIND_MESSAGE = "Who am I? Your secretary?";

    public static final String GREET = "I'm busy. What do you want?";

    public static final String LIST_MESSAGE = "This is your useless list.";

    public static final String MARK_DONE = 
            "Tsk! I've marked this as done, you owe me.";

    public static final String TASK_ADDED = "I have increased your workload.";

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