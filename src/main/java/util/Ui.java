package util;

import util.task.Task;
import util.task.TaskList;

/**
 * A class to handle interactions with the user.
 * It prints responses to a user's command or input.
 */
class Ui {
    static String readCommand(String input) {
        assert input != null;
        return input.split(" ")[0].toLowerCase();
    }

    static String printList(TaskList dl) {
        assert dl != null;
        return DukeUiString.DIVIDER
            + dl.printList()
            + "\n" + DukeUiString.DIVIDER;
    }

    static String printBye() {
        return DukeUiString.UI_BYE;
    }

    static String readTaskIndex(String input) {
        assert input != null;
        return input.trim();
    }

    static String printDone(TaskList dl, int index) {
        assert dl != null;

        return DukeUiString.DIVIDER
                + DukeUiString.DONE
                + "      " + dl.printTask(index)
                + DukeUiString.DIVIDER;
    }

    static String printErrorMessage(Exception e) {
        return DukeUiString.DIVIDER
                + e.getMessage() + "\n"
                + DukeUiString.DIVIDER;
    }

    static String readTaskDescription(String input) {
        assert input != null;
        return input.trim();
    }

    static String readKeyword(String input) {
        assert input != null;
        return input.trim().toLowerCase();
    }

    static String printTaskAdded(TaskList dl, Task task) {
        assert dl != null;
        assert task != null;

        return DukeUiString.DIVIDER
                + DukeUiString.ADDED
                + "      " + task.toString()
                + "\n\n   Now you have " + dl.listSize()
                + (dl.listSize() == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeUiString.DIVIDER;
    }

    static String printDateTimeErrorMessage() {
        return DukeUiString.DATE_TIME_ERROR_MESSAGE;
    }

    static String printDelete(TaskList dl, int index) {
        assert dl != null;

        return DukeUiString.DIVIDER
                + DukeUiString.DELETED + "      " + dl.printTask(index)
                + "\n   Now you have " + dl.listSize()
                + (dl.listSize() - 1 == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeUiString.DIVIDER;
    }

    static String printWriteErrorMessage() {
        return DukeUiString.WRITE_ERROR_MESSAGE;
    }

    static String printFind(String keywords, TaskList found) {
        assert keywords != null;
        assert found != null;

        return DukeUiString.DIVIDER
                + "   Here are the tasks with " + keywords + " in them:\n\n"
                + found.printList()
                + "\n" + DukeUiString.DIVIDER;
    }

    static String printClear() {
        return DukeUiString.UI_CLEAR;
    }

    public static String printHelp() {
        return DukeUiString.UI_HELP;
    }

    public static String readScheduleDate(String input) {
        assert input != null;
        return input.trim();
    }

    public static String printSave() {
        return DukeUiString.UI_SAVE;
    }
}