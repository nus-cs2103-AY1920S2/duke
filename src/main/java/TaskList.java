import java.util.ArrayList;

/**
 * Stores all existing tasks and manage them.
 */
public class TaskList {
    public ArrayList<Task> list = new ArrayList<>();

    /**
     * Add a new task to the end of the ArrayList list.
     *
     * @param task The task to be added to list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Delete a task from the list.
     *
     * @param idx The index of the (to be deleted) task in the list.
     * @return The deleted task.
     */
    public Task delete(int idx) {
        return list.remove(idx);
    }

    /**
     * Mark one of the tasks as done.
     *
     * @param idx The index of the task in the list.
     */
    public void done(int idx) {
        list.get(idx).status = "[" + "\u2713" + "]";
    }

    /**
     * Print out all existing tasks according to their order in the list.
     */
    public String print() {
        String toPrint;
        if(list.size() == 0) {
            return null;
        }
        toPrint = String.valueOf(1) + "." + list.get(0).readyToPrint() + "\n";
        for (int i = 1; i < list.size(); i++) {
            toPrint += String.valueOf(i + 1) + "." + list.get(i).readyToPrint() + "\n";
        }
        return toPrint;
    }

    /**
     * Search the tasks that match the keyword.
     *
     * @param keyword Keyword entered by user.
     */
    public String find(String keyword) {
        int number = 0;
        String find = null;
        for (Task task : list) {
            if (task.name.contains(keyword)) {
                number++;
                find += String.valueOf(number) + "." + task.readyToPrint() + "\n";
            }
        }
        return find;
    }
}