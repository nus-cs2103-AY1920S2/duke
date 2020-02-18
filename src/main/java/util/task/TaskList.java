package util.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Creates an empty util.task.TaskList with no tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a copy of the util.task.TaskList with the same list of Tasks.
     *
     * @param dl A util.task.TaskList.
     */
    private TaskList(TaskList dl) {
        this.list = dl.list;
    }

    /**
     * Adds a Task to a util.task.TaskList.
     *
     * @param task The Task to be added.
     * @return A new util.task.TaskList with the added Task.
     */
    public TaskList addToList(Task task) {
        TaskList dl = new TaskList(this);
        dl.list.add(task);
        return dl;
    }

    /**
     * Checks whether a Task is complete.
     *
     * @param index The index of the Task to be checked.
     * @return A boolean of whether the Task has been completed.
     */
    public boolean isTaskDone(int index) {
        return this.list.get(index).getCompletionStatus();
    }

    /**
     * Prints all the Tasks in the util.task.TaskList.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();

        if (this.list.size() == 0) {
            sb.append("   The list is empty.");
        }
        for (int i = 1; i <= list.size(); i++) {
            sb.append("      ").append(i).append(". ").append(list.get(i - 1));
            if (i != list.size()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Sets a Task to be completed.
     *
     * @param index The index of the Task to be completed.
     * @return A new util.task.TaskList with the updated Task.
     */
    public TaskList setDone(int index) {
        TaskList dl = new TaskList(this);
        dl.list.set(index, this.list.get(index).completeTask());
        return dl;
    }

    public String printTask(int index) {
        return this.list.get(index).toString() + "\n";
    }

    public int listSize() {
        return this.list.size();
    }

    /**
     * Deletes a Task.
     *
     * @param index The index of the Task to be deleted.
     * @return A new util.task.TaskList without the deleted Task.
     */
    public TaskList deleteTask(int index) {
        TaskList dl = new TaskList(this);
        dl.list.remove(index);
        return dl;
    }

    /**
     * Finds all the Tasks that contain the keyword(s).
     *
     * @param keywords A String of keywords.
     * @return A util.task.TaskList containing all the matching Tasks.
     */
    public TaskList find(String keywords) {
        assert keywords != null;

        TaskList found = new TaskList();

        for (Task task : this.list) {
            if (task.match(keywords)) {
                found = found.addToList(task);
            }
        }

        return found;
    }

     public TaskList findByDay(Date date) {
        TaskList temp = new TaskList();

        for (Task t : this.list) {
            if (t instanceof DeadlineTask || t instanceof EventTask) {
                Date tDate = t instanceof DeadlineTask ? ((DeadlineTask) t).deadline : ((EventTask) t).deadline;

                if (date.getYear() == tDate.getYear() && date.getMonth() == tDate.getMonth()
                        && date.getDay() == tDate.getDay()) {
                    temp = temp.addToList(t);
                }
            }
        }

        return temp;
     }
}