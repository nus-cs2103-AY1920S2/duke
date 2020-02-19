package command;

/**
 * Stores magic strings as constants to be returned as output.
 */
public class UI {
    public static final String LOGO = " ___           _\n"
            + "|  _  \\ _    _| |  _  __\n"
            + "| |  |  | |  | | |/ /   _ \\\n"
            + "| |_|  | |_| |   <    __/\n"
            + "|____/ \\__,|_|\\_\\___|\n";
    public static final String ADD = "Got it. I've added this task:\n";
    public static final String ALIAS = "Alias added.";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String CLEAR = "List cleared.";
    public static final String DONE = "Nice! I've marked this task as done:\n\t\t";
    public static final String EMPTY_LIST = "List is empty.";
    public static final String HELLO = "Hello! I'm Duke\n\tWhat can I do for you?";
    public static final String LIST = "Here are the tasks in your list:";
    public static final String NO_TAGS = "No tasks with this tag found :o";
    public static final String REMOVE = "Noted. I've removed this task:\n\t\t";
    public static final String START = "Hello from\n" + LOGO;
    public static final String HELP = "alias [alias] [command]\n"
            + "bye: exits the programme"
            + "clear: clears list\n"
            + "deadline [description] [date] [tags]\n"
            + "delete [task number]: deletes task with task number\n"
            + "done [task number]: marks task with task number as done\n"
            + "event [description] [date] [tags]\n"
            + "find [keyword]: returns list of tasks with keyword in name\n"
            + "tag [tag]: returns list of tasks containing tag/n"
            + "todo [description] [tags]\n";


}
