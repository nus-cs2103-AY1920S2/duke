/**
 * A class to abstract the frequently used Strings in Duke.
 */
class DukeStringFormat {
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

    static final String HELLO = DukeStringFormat.DIVIDER + DukeStringFormat.LOGO
            + "   Hello!"
            + "\n   The oNUS is on me to help you be productive!\n"
            + DukeStringFormat.DIVIDER;

    static final String ADDED = "   Got it! I've added this task:\n\n";
    static final String DIVIDER = "____________________________________________________________\n\n";
    static final String DONE = "   Nice! I've marked this task as done:\n";
    static final String DELETED = "   Alright, I've removed this task:\n";
    static final String BYE = "   Your current task list has been saved!\n   Bye, hope to see you again soon!\n";
    static final String SAMPLE = "   For example:\n      deadline Assignment /by 01/01/2000 1234\n";
}