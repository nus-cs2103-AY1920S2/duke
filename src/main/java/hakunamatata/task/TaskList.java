package hakunamatata.task;

import java.util.ArrayList;

import hakunamatata.Parser;

/**
 * Represents the task list.
 *
 * @author Wang Yuting
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an <code>TaskList</code>.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Creates an <code>TaskList</code> with given <code>tasks</code>.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds new task into the <code>tasks</code>.
     *
     * @param task The task will be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets <code>tasks</code>.
     *
     * @return The current list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes the task at <code>index</code>.
     *
     * @return The task at <code>index</code>.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Build a human readable String of the tasks list.
     *
     * @return The human readable String of the tasks list.
     */
    public String getTaskListString() {
        String listOfTasks = "";

        for (Task t : this.tasks) {
            int counter = this.tasks.indexOf(t);
            if (counter != 0) {
                listOfTasks += "";
            }
            listOfTasks += counter + 1 + "." + t.toString() + "\n";
        }
        return this.tasks.size() != 0 ? listOfTasks : "- No task was entered -\n";
    }

    /** Parses current task list to a string with valid saving format.*/
    public String getSavedString() {
        String tasks = "";
        for (Task t : this.tasks) {
            String temp = "";
            int status = t.isDone ? 1 : 0;
            switch (t.type) {
            case TODO:
                temp += "T | " + status + " | " + t.getDescription() + "\n";
                break;
            case DEADLINE:
                String deadlineDateTime = Parser.getDateTimeString(((Deadline) t).getBy());
                temp += "D | " + status + " | " + t.getDescription() + " | "
                        + deadlineDateTime + "\n";
                break;
            case EVENT:
                String eventDuration = Parser.getDateTimeString(((Event) t).getStart())
                        + "-" + Parser.getDateTimeString(((Event) t).getEnd());
                temp += "E | " + status + " | " + t.getDescription() + " | " + eventDuration + "\n";
                break;
            default:
                break;
            }
            tasks += temp;
        }
        return tasks;
    }

    /**
     * Gets the <code>tasks</code> list size.
     *
     * @return The <code>tasks</code> list size.
     */
    public int getSize() {
        return this.tasks.size();
    }
}
