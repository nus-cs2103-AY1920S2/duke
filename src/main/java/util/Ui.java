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
        return DukeUIString.DIVIDER
            + dl.printList()
            + "\n" + DukeUIString.DIVIDER;
    }

    static String printBye() {
        return DukeUIString.UI_BYE;
    }

    static String readTaskIndex(String input) {
        assert input != null;
        return input.trim();
    }

    static String printDone(TaskList dl, int index) {
        assert dl != null;

        return DukeUIString.DIVIDER
                + DukeUIString.DONE
                + "      " + dl.printTask(index)
                + DukeUIString.DIVIDER;
    }

    static String printErrorMessage(Exception e) {
        return DukeUIString.DIVIDER
                + e.getMessage() + "\n"
                + DukeUIString.DIVIDER;
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

        return DukeUIString.DIVIDER
                + DukeUIString.ADDED
                + "      " + task.toString()
                + "\n\n   Now you have " + dl.listSize()
                + (dl.listSize() == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeUIString.DIVIDER;
    }

    static String printDateTimeErrorMessage() {
        return DukeUIString.DATE_TIME_ERROR_MESSAGE;
    }

    static String printDelete(TaskList dl, int index) {
        assert dl != null;

        return DukeUIString.DIVIDER
                + DukeUIString.DELETED + "      " + dl.printTask(index)
                + "\n   Now you have " + dl.listSize()
                + (dl.listSize() - 1 == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeUIString.DIVIDER;
    }

    static String printWriteErrorMessage() {
        return DukeUIString.WRITE_ERROR_MESSAGE;
    }

    static String printFind(String keywords, TaskList found) {
        assert keywords != null;
        assert found != null;

        return DukeUIString.DIVIDER
                + "   Here are the tasks with " + keywords + " in them:\n\n"
                + found.printList()
                + "\n" + DukeUIString.DIVIDER;
    }

    static String printClear() {
        return DukeUIString.UI_CLEAR;
    }
}