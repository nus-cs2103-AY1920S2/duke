package seedu.java.util;

public enum Text {
    HELPLIST, LOGO, TODO, DEADLINE, EVENT, DONE, LIST, DELETE, BYE, FIND;

    static String shortenedHelplist = "Here are the list of commands you can use."
            + "For more information, type 'help X', which X represents the command you need more information about :)."
            + "\n TODO -- Adds a to-do task."
            + "\n" + "DEADLINE -- Adds a task with a deadline. "
            + "\n" + "EVENT -- Adds an event with a spanning timeline. "
            + "\n" + "DONE -- Completes an event/task. "
            + "\n" + "DELETE -- Deletes a task. "
            + "\n" + "LIST -- Gives a full view of your tasklist."
            + "\n" + "FIND -- Search for a task with that specific keyword."
            + "\n" + "BYE -- Terminates.";

    static String todo = "\nAdds a to-do task. Key in 'todo' and then whatever ridiculous task you desire.";
    static String deadline = "\nKey in 'deadline', the task, and then the date in the format YYYY-MM-DD.";
    static String event = "\nKey in 'event', the event description and the date in whatever format. ";
    static String done = "\nType 'done' & a task number to complete that specific task.";
    static String delete = "\nType 'delete' & a task number to delete that task.";
    static String list = "\nIf you want to view what's on your tasklist, key in 'list'.";
    static String find = "\nKey in 'find' & a keyword of what you wish to find."
                            + "A list of task based on the keyword will be printed.";
    static String bye = "\nTerminates Program.";

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String printGeneralHelp() {
        return shortenedHelplist;
    }

    /**
     * For printing lengthy stuff.
     * @param text enum.
     * @return a string that shows the text.
     */
    public static String printHelpForCommands(Command text) {
        if (text.equals(Command.TODO)) {
            return todo;
        } else if (text.equals(Command.DEADLINE)) {
            return deadline;
        } else if (text.equals(Command.EVENT)) {
            return event;
        } else if (text.equals(Command.DONE)) {
            return done;
        } else if (text.equals(Command.DELETE)) {
            return delete;
        } else if (text.equals(Command.LIST)) {
            return list;
        } else if (text.equals(Command.FIND)) {
            return find;
        } else if (text.equals(Command.BYE)) {
            return bye;
        } else {
            return logo;
        }
    }
}
