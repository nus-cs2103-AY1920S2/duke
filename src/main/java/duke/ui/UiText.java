package duke.ui;

import java.util.List;

public interface UiText {
    static String stupidLogo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    static String resSpace = "    ";
    static String line = "____________________________________________________________";
    public static String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
    public static String taskDoneNote = "Nice! I've marked this task as done:";
    public static String bye = "Bye. Hope to see you again soon!";
    public static String dunno = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static String loadingErrorMsg = "File not exist";
    public static String revertSuccess = "Yay! I have nullified your previous command!";
    public static String revertError = "No history available";

    public void startRespond(String... initials);
    public void respondLine(List<String> respondStr);
    public void respondLine(String... respondStr);
    public void over(String... remarks);
    public void respond(String... respondStr);
    public void respond(List<String> respondStr);

    public boolean hasNextLine();
    public String nextLine();
}
