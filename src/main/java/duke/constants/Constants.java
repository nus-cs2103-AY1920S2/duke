package duke.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ITALIC = "\033[3m";
    public static final String ITALIC_RESET = "\033[0m";

    public static final String DUKE_FILE_PATH = ".\\data\\duke.txt";

    public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_2 = "MMM d yyyy";

    public static final List<String> COMMAND_LIST = Arrays.asList(
            new String[]{
                "deadline", "todo", "find", "event", "done", "delete", "bye", "list", "help", "snooze"
            });
}

