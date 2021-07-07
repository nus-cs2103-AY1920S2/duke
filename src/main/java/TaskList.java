import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Tasks.
 */
class TaskList {
    private List<Task> list;

    /**
     * Creates an empty TaskList with no tasks.
     */
    TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a copy of the TaskList with the same list of Tasks.
     *
     * @param dl A TaskList.
     */
    private TaskList(TaskList dl) {
        this.list = dl.list;
    }

    /**
     * Adds a Task to a TaskList.
     *
     * @param task The Task to be added.
     * @return A new TaskList with the added Task.
     */
    TaskList addToList(Task task) {
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
    boolean isTaskDone(int index) {
        return this.list.get(index).getCompletionStatus();
    }

    /**
     * Prints all the Tasks in the TaskList.
     */
    String printList() {
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
     * @return A new TaskList with the updated Task.
     */
    TaskList setDone(int index) {
        TaskList dl = new TaskList(this);
        dl.list.set(index, this.list.get(index).completeTask());
        return dl;
    }

    String printTask(int index) {
        return this.list.get(index).toString() + "\n";
    }

    int listSize() {
        return this.list.size();
    }

    /**
     * Deletes a Task.
     *
     * @param index The index of the Task to be deleted.
     * @return A new TaskList without the deleted Task.
     */
    TaskList deleteTask(int index) {
        TaskList dl = new TaskList(this);
        dl.list.remove(index);
        return dl;
    }

    /**
     * Finds all the Tasks that contain the keyword(s).
     *
     * @param keywords A String of keywords.
     * @return A TaskList containing all the matching Tasks.
     */
    TaskList find(String keywords) {
        assert keywords != null;

        TaskList found = new TaskList();

        for (Task task : this.list) {
            if (task.match(keywords)) {
                found = found.addToList(task);
            }
        }

        return found;
    }

}