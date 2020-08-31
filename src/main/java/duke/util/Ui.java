package duke.util;

import duke.task.Task;

/**
 * Text Ui of Duke.
 */
public class Ui {

    /**
     * Shows Duke logo.
     *
     * @return A string of logo.
     */
    public String showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Shows greeting message.
     *
     * @return A string of greeting sentences.
     */
    public String showGreet() {
        return "Hello! I'm Duke! What can I do for you?\n"
                + "You may type \'help\' to see more functions.\n";
    }

    /**
     * Shows all tasks in the list.
     *
     * @param tasks Task list.
     * @return A string that list our all tasks.
     */
    public String showList(TaskList tasks) {
        if (tasks.getTaskNumber() == 0) {
            return "OOPs! You don't have any task now. Try to add some!";
        }
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.getTaskNumber(); i++) {
            Task currTask = tasks.getTask(i - 1);
            str = showNewTask(str, i, currTask);
        }
        return str;
    }

    /**
     * Add string representation of a task.
     *
     * @param str Original sentence.
     * @param i Index of the task.
     * @param currTask Current task.
     * @return A string contains representation of current task.
     */
    private String showNewTask(String str, int i, Task currTask) {
        str = str.concat("  " + i + "." + currTask + "\n");
        return str;
    }

    /**
     * Shows adding response.
     *
     * @param t New Task to be added.
     * @param tasks Task list.
     * @return A string shows successful addition of tasks.
     */
    public String showAdd(Task t, TaskList tasks) {
        tasks.addTask(t);
        return "Got it. I've added this task:\n  " + t + "\n" + showTaskNumber(tasks);
    }

    /**
     * Shows number of tasks in current task list.
     *
     * @param tasks Task list.
     * @return A string indicates number of tasks.
     */
    private String showTaskNumber(TaskList tasks) {
        return "Now you have "
                + tasks.getTaskNumber()
                + (tasks.getTaskNumber() == 1 ? " task" : " tasks")
                +" in the list.\n";
    }

    /**
     * Shows marking as done message.
     *
     * @param currTask Task that be marked.
     * @return A string shows successful marking done.
     */
    public String showMarkDone(Task currTask) {
        currTask.markAsDone();
        return "Nice! I've marked this task as done:\n" + "  " + currTask + "\n";
    }

    /**
     * Shows deleting message.
     *
     * @param currTask Target Task to delete.
     * @param tasks Task list.
     * @return A string shows successful deletion.
     */
    public String showDelete(Task currTask, TaskList tasks) {
        String str = "Noted. I've removed this task:\n  " + currTask + "\n";
        tasks.removeTask(currTask);
        str = str.concat(showTaskNumber(tasks));
        return str;
    }

    /**
     * Shows exiting message.
     *
     * @return A string shows exiting sentences.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows user guidance.
     *
     * @return A string of all functions in Duke.
     */
    public String showHelp() {
        return "Here are my functions that you can try: \n"
                + "- help: show my functions. \n"
                + "- list: list your tasks.\n"
                + "- todo [task description]: add a todo task.\n"
                + "- deadline [task description] /by [yyyy-mm-dd]: add a task with deadline.\n"
                + "- event [task description] /at [yyyy-mm-dd]: add an event with time.\n"
                + "- done [task number]: mark this task as done.\n"
                + "- tag [task number] [tag]: add one tag to this task.\n"
                + "- delete [task number]: delete this task from your list.\n"
                + "- find [keywords]: find all tasks containing the keywords.\n"
                + "- bye: say goodbye!\n";
    }

    /**
     * Shows Exception message.
     *
     * @param message Exception message.
     * @return A string description of the exception.
     */
    public String showException(String message) {
        return message;
    }

    /**
     * Shows all Tasks that matches the key word.
     *
     * @param keyWord String of key word.
     * @param tasks Task list.
     * @return A string of all found tasks.
     */
    public String showFind(String keyWord, TaskList tasks) {
        String str = "Here are the matching tasks in your list:\n";
        boolean failed = true;
        for (int i = 1; i <= tasks.getTaskNumber(); i++) {
            Task currTask = tasks.getTask(i - 1);
            if (currTask.getDescription().contains(keyWord)) {
                str = showNewTask(str, i, currTask);
                failed = false;
            }
        }
        if (failed) {
            str = str.concat("  None.\n");
        }
        return str;
    }

    /**
     * Add one tag to a task and show this step.
     *
     * @param currTask The task.
     * @param tag The new tag.
     * @return A string representation of the task with new tags.
     */
    public String showTag(Task currTask, String tag) {
        currTask.addTags(tag);
        return "Nice! I've added tag to this task:\n" + "  " + currTask + "\n";
    }
}
