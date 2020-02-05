package duke.common;

public class Message {

    public static final String DELETE_MESSAGE = 
            "Removed and never to be seen again.";

    public static final String DIVIDER = "----------";

    public static final String EXIT_MESSAGE = "Bye, see you never!";

    public static final String GREET = "I'm busy. What do you want?";

    public static final String LIST_MESSAGE = "This is your useless list.";

    public static final String MARK_DONE = 
            "Tsk! I've marked this as done, you owe me.";

    public static final String TASK_ADDED = "I have increased your workload.";

    public static String showNumberOfTasks(int number) {
        return "You have " + number + " tasks in your list. :(";
    }
}