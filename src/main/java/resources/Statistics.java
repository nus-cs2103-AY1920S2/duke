package resources;

import duke.UI;

/**
 * Handles everything to do with statistics
 */
public class Statistics {

    /**
     * Adds a task that is finish into the list
     * Updates the current week
     *
     * @return String the task to add
     */
    public static String taskDoneWeek() {
        return TaskTracker.doneThisWeek();
    }

    /**
     * Adds a task that is finish into the list
     * Updates the current week
     *
     * @return String the task of things completed
     */
    public static String numDone() {
        return "You have completed" + TaskTracker.done() + " this week...";
    }

    /**
     * Returns the specific list for a specific
     * type of tasks
     * @return String the type searched
     */
    public static String getTypes(String type) {
        if (type.contains("event")) {
            return TaskTracker.getEventsString();
        }

        if (type.contains("todo") || type.contains("to do")) {
            return TaskTracker.getToDoString();
        }

        if (type.contains("deadline")) {
            return TaskTracker.getDeadlineString();
        }

        return UI.getReply("wrongType");
    }

    /**
     * Returns the tasks completed today
     * @return String the things done
     */
    public static String taskDone(){
        return TaskTracker.getDoneToday();
    }

    /**
     * Count of things deleted in session
     *
     * @return String of things deleted
     */
    public static String getNumDeleteTask(){
        return TaskTracker.showDeleted();
    }

    /**
     * Show the expired tasks
     *
     * @return String of things that are expired
     */
    public static String getExpired(){
        return TaskTracker.showExpired();
    }

}
