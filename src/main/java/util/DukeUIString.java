package util;

/**
 * A class to abstract the frequently used Strings in main.Duke.
 */
public class DukeUIString {
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
            + "                   |  |    oNUS    |  |\n\n";

    public static final String HELLO = DukeUIString.DIVIDER + DukeUIString.LOGO
            + "   Hello!"
            + "\n   The oNUS is on me to help you be productive!\n"
            + DukeUIString.DIVIDER;

    public static final String ADDED = "   Got it! I've added this task:\n\n";
    public static final String DIVIDER = "____________________________________________________________\n\n";
    public static final String DONE = "   Nice! I've marked this task as done:\n";
    public static final String DELETED = "   Alright, I've removed this task:\n";
    public static final String BYE = "   Your current task list has been saved!\n   Bye, hope to see you again soon!\n";
    public static final String SAMPLE = "   For example:\n      deadline Assignment /by 01/01/2000 1234\n";
    public static final String UI_BYE = DukeUIString.DIVIDER + DukeUIString.BYE + DukeUIString.DIVIDER;

    public static final String INVALID_DATE_TIME_FORMAT = "   The date and/or time format is invalid.\n";
    public static final String DATE_TIME_FORMAT_EXAMPLE = "   Please format your input as: DD/MM/YYYY HHmm.\n";

    public static final String WRITE_ERROR_MESSAGE = DukeUIString.DIVIDER
            + "   Something went wrong with saving the file!\n" + DukeUIString.DIVIDER;

    public static final String DATE_TIME_ERROR_MESSAGE = DukeUIString.DIVIDER + DukeUIString.INVALID_DATE_TIME_FORMAT
            + DukeUIString.DATE_TIME_FORMAT_EXAMPLE + DukeUIString.SAMPLE + DukeUIString.DIVIDER;

    public static final String UI_CLEAR = DukeUIString.DIVIDER + "   Your tasks have been cleared!\n"
            + "   The list is now empty.\n\n" + DukeUIString.DIVIDER;
}