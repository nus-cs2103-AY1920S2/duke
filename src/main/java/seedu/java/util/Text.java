package seedu.java.util;

public enum Text {
    HELPLIST,LOGO;
    static String helplist = "Type a command and some other text, then press ENTER :) Here's what you can do"
            + "\n" + "TODO -- Adds a to-do task. Key in 'todo' and then whatever ridiculous task you desire."
            + "\n" + "DEADLINE -- Adds a task with a deadline. "
            + "Key in 'deadline', the task, and then the date in the format YYYY-MM-DD."
            + "\n" + "EVENT -- Adds an event with a spanning timeline. "
            + "Key in 'event', the event description and the date in whatever format. "
            + "\n" + "DONE -- Completes an event/task. "
            + "Type 'done' & a task number to complete that specific task."
            + "\n" + "DELETE -- Deletes a task. "
            + "Type 'delete' & a task number to delete that task."
            + "\n" + "LIST -- If you want to view what's on your tasklist, key in 'list'."
            + "\n" + "FIND -- Too many Task that you're not sure which is which? "
            + "Key in 'find' & the key word. A list of task based on the keyword will be printed."
            + "\n" + "BYE -- When you're sick of using this useless software and want to quit, "
            + "type 'bye' & I'll terminate operation."
            + "\n" + "Have fun! :)";
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String print(Text text){
        if (text.equals(Text.HELPLIST)) {
            return helplist;
        } else {
            return logo;
        }
    }
}
