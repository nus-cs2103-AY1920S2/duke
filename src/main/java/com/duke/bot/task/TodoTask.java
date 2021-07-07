package com.duke.bot.task;

/**
 * Represents tasks that neither has a deadline nor event date.
 */
public class TodoTask extends Task {

    private TodoTask(String taskName) {
        super(taskName, false);
    }

    /**
     * Creates a TodoTask.
     *
     * @param taskName name of the TodoTask.
     * @return TodoTask object.
     */
    public static TodoTask createTodoTask(String taskName) {
        return new TodoTask(taskName);
    }

    /**
     * Returns the icon for TodoTask.
     *
     * @return A letter 'T'.
     */
    @Override
    public String getTaskIcon() {
        return "T";
    }
}
