/**
 * This class is used to display help information
 */

public class Helper {

    public Helper(){}

    private static final String HELPTEXT =
            "Hi! Welcome to Duke! " +
                    "Duke is a task managing assistant that helps" +
                    "manage your tasks, events and deadlines! \n" +
                    "To start, here are some basic commands:\n" +
                    "list: prints the current list in the database\n" +
                    "help: prints help information\n" +
                    "done: use 'done <index of item in list>' to mark the task as done.\n\n" +
                    "Now for input entry wise, here are some helpful instructions:\n" +
                    "To add a TODO, use the following format:\n" +
                    "todo <Description>\n\n" +
                    "To add a DEADLINE, use the following format:\n" +
                    "deadline <Description> /by <Date*>\n\n" +
                    "To add an EVENT, use the following format:" +
                    "event <Description> /at <Date*>\n\n" +
                    "*Do note that for Date, please use the following format:\n" +
                    "yyyy-mm-dd hh:mm (eg. 2020-02-14 18:00)";

    public static String printHelp() {
        return HELPTEXT;
    }
}
