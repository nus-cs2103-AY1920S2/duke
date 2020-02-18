package util;

/**
 * A class to abstract the frequently used Strings in main.Duke.
 */
public class DukeUiString {
    private static final String LOGO =
            "                 .-\"\"\"\"-.        .-\"\"\"\"-.\n"
            + "                /        \\      /        \\\n"
            + "               /_        _\\    /_        _\\\n"
            + "              // \\      / \\\\  // \\      / \\\\\n"
            + "              |\\__\\    /__/|  |\\__\\    /__/|\n"
            + "               \\    ||    /    \\    ||    /\n"
            + "                \\        /      \\        /\n"
            + "                 \\ \\__/ /        \\ \\__/ /\n"
            + "                  '.__.'          '.__.'\n"
            + "                   |  |            |  |\n"
            + "                   |  |            |  |\n\n";

    public static final String HELLO = DukeUiString.DIVIDER + DukeUiString.LOGO
            + "   Hello!"
            + "\n   What's up today?\n"
            + "\n   Here are some commands that I can help you with:\n"
            + "      list, clear, todo, deadline, event, bye, find...!\n"
            + "      Enter \"help\" to find out more!\n"
            + DukeUiString.DIVIDER;

    public static final String ADDED = "   Got it! I've added this task:\n\n";
    public static final String DIVIDER = "____________________________________________________________\n\n";
    public static final String DONE = "   Nice! I've marked this task as done:\n";
    public static final String DELETED = "   Alright, I've removed this task:\n";
    public static final String BYE = "   Your current task list has been saved!\n   Bye, hope to see you again soon!\n";
    public static final String SAMPLE = "   For example:\n      deadline Assignment /by 01/01/2000 1234\n";
    public static final String UI_BYE = DukeUiString.DIVIDER + DukeUiString.BYE + DukeUiString.DIVIDER;
    public static final String UI_SAVE = DukeUiString.DIVIDER + "   Your list has been saved!\n" + DukeUiString.DIVIDER;

    public static final String INVALID_DATE_TIME_FORMAT = "   The date and/or time format is invalid.\n";
    public static final String DATE_TIME_FORMAT_EXAMPLE = "   Please format your input as: DD/MM/YYYY HHmm.\n";

    public static final String WRITE_ERROR_MESSAGE = DukeUiString.DIVIDER
            + "   Something went wrong with saving the file!\n" + DukeUiString.DIVIDER;

    public static final String DATE_TIME_ERROR_MESSAGE = DukeUiString.DIVIDER + DukeUiString.INVALID_DATE_TIME_FORMAT
            + DukeUiString.DATE_TIME_FORMAT_EXAMPLE + DukeUiString.SAMPLE + DukeUiString.DIVIDER;

    public static final String UI_CLEAR = DukeUiString.DIVIDER + "   Your tasks have been cleared!\n"
            + "   The list is now empty.\n\n" + DukeUiString.DIVIDER;

    public static final String UI_HELP = DukeUiString.DIVIDER
            + "   ~ list                  Obtains a list of your tasks\n\n"
            + "   ~ clear                 Empties your list of tasks\n\n"
            + "   ~ todo description      Adds a ToDo task\n\n"
            + "   ~ deadline description  Adds a Deadline task\n"
            + "      /by date\n\n"
            + "   ~ event description     Adds an Event Task\n"
            + "      /at date\n\n"
            + "   ~ done number           Marks a task as done, where\n"
            + "                           number is greater than 0\n\n"
            + "   ~ find keywords         Finds a list of tasks matching\n"
            + "                           the string of words\n\n"
            + "   ~ help                  Brings up this help section\n\n"
            + "   ~ bye                   Saves your list of tasks\n"
            + "                           and quit Duke\n"
            + DukeUiString.DIVIDER;
}