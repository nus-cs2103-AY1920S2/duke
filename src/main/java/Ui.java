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
        return DukeStringFormat.DIVIDER
            + dl.printList()


            + "\n" + DukeStringFormat.DIVIDER;
    }

    static String printBye() {
        return (DukeStringFormat.DIVIDER + DukeStringFormat.BYE
                + DukeStringFormat.DIVIDER);
    }

    static String readTaskIndex(String input) {
        assert input != null;
        return input.trim();
    }

    static String printDone(TaskList dl, int index) {
        assert dl != null;

        return DukeStringFormat.DIVIDER
                + DukeStringFormat.DONE
                + "      " + dl.printTask(index)
                + DukeStringFormat.DIVIDER;
    }

    static String printErrorMessage(Exception e) {
        return DukeStringFormat.DIVIDER
                + e.getMessage() + "\n"
                + DukeStringFormat.DIVIDER;
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

        return DukeStringFormat.DIVIDER
                + DukeStringFormat.ADDED
                + "      " + task.toString()
                + "\n\n   Now you have " + dl.listSize()
                + (dl.listSize() == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeStringFormat.DIVIDER;
    }

    static String printDateTimeErrorMessage() {
        return DukeStringFormat.DIVIDER
                + "   The date and/or time format is invalid.\n"
                + "   Please format your input as: DD/MM/YYYY HHmm.\n"
                + DukeStringFormat.SAMPLE
                + DukeStringFormat.DIVIDER;
    }

    static String printDelete(TaskList dl, int index) {
        assert dl != null;

        return DukeStringFormat.DIVIDER
                + DukeStringFormat.DELETED + "      " + dl.printTask(index)
                + "\n   Now you have " + dl.listSize()
                + (dl.listSize() - 1 == 1 ? " task" : " tasks")
                + " in the list.\n"
                + DukeStringFormat.DIVIDER;
    }

    static String printWriteErrorMessage() {
        return DukeStringFormat.DIVIDER
                + "   Something went wrong with saving the file!\n"
                + DukeStringFormat.DIVIDER;
    }

    static String printFind(String keywords, TaskList found) {
        assert keywords != null;
        assert found != null;

        return DukeStringFormat.DIVIDER
                + "   Here are the tasks with " + keywords + " in them:\n\n"
                + found.printList()
                + "\n" + DukeStringFormat.DIVIDER;
    }

    static String printClear() {
        return DukeStringFormat.DIVIDER
                + "   Your tasks have been cleared!\n"
                + "   The list is now empty.\n\n"
                + DukeStringFormat.DIVIDER;
    }
}