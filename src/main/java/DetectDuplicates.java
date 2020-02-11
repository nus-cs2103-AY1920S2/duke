import java.util.ArrayList;

public class DetectDuplicates {
    private String str;
    private String type;

    DetectDuplicates(String str, String type) {
        this.str = str;
        this.type = type;
    }

    /**
     * Removes all duplicate tasks from the list.
     *
     * @param tasks List of tasks
     * @param storage Storage object
     */
    public void removeDuplicates(TaskList tasks, Storage storage) {
        if (tasks.getList().size() > 0) {
            for (int i = 0; i < tasks.getList().size(); i++) {
                if (tasks.getList().get(i).getTaskName().equalsIgnoreCase(str)
                        && tasks.getList().get(i).getType().equalsIgnoreCase(type)) {
                    Delete d = new Delete(i + 1);
                    d.execute(tasks, storage);
                }
            }
        }
    }

}
